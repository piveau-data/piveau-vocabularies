@file:JvmName("ConceptSchemes")

package io.piveau.vocabularies

import io.piveau.vocabularies.vocabulary.AT
import io.piveau.vocabularies.vocabulary.EDP
import io.piveau.vocabularies.vocabulary.PS
import io.vertx.core.*
import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.client.HttpResponse
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.predicate.ResponsePredicate
import io.vertx.kotlin.core.setHandlerAwait
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.*
import org.apache.jena.rdf.model.*
import org.apache.jena.riot.*
import org.apache.jena.vocabulary.*
import java.io.ByteArrayInputStream
import java.io.InputStream

val vertx: Vertx = Vertx.vertx()
val client: WebClient = WebClient.create(vertx)

class Concept(val resource: Resource) {
    fun label(lang: String): String? = prefLabel(lang) ?: altLabel(lang)
    fun prefLabel(lang: String): String? = resource.getProperty(SKOS.prefLabel, lang)?.literal?.string
    fun altLabel(lang: String): String? = resource.getProperty(SKOS.altLabel, lang)?.literal?.string
    val identifier: String = resource.getProperty(DC_11.identifier)?.literal?.string ?: ""
    val exactMatch: String = resource.getProperty(SKOS.exactMatch)?.resource?.uri ?: ""
}

sealed class ConceptScheme(resource: String, uri: String? = null) {
    val model: Model by lazy {
        ModelFactory.createDefaultModel().apply {
            runBlocking {
                uri?.let { fetchVocabulary(uri, resource) } ?: readXmlResource(resource)
            }
        }
    }

    fun isConcept(resource: Resource): Boolean =
        model.containsResource(resource) && model.getResource(resource.uri).isA(SKOS.Concept)

    fun getConcept(resource: Resource): Concept? =
        if (isConcept(resource)) {
            model.getResource(resource.uri).let {
                it.getProperty(OWL.sameAs)?.let { same -> Concept(same.`object`.asResource()) } ?: Concept(it)
            }
        } else null

    fun getConcept(identifier: String): Concept? = model.listSubjectsWithProperty(
        DC_11.identifier,
        identifier
    ).toList().firstOrNull()?.let { Concept(it) }
}

var dataThemeRemote: String? = null;
var fileTypeRemote: String? = null;
var corporateBodiesRemote: String? = null;
var languagesRemote: String? = null;
var licenseRemote: String? = null;
var countriesRemote: String? = null;
var continentsRemote: String? = null;
var placesRemote: String? = null;

fun enableRemotes() {
    dataThemeRemote = "http://publications.europa.eu/resource/authority/data-theme"
    fileTypeRemote = "http://publications.europa.eu/resource/authority/file-type"
    corporateBodiesRemote = "http://publications.europa.eu/resource/authority/corporate-body"
    languagesRemote = "http://publications.europa.eu/resource/authority/language"
    licenseRemote = "http://publications.europa.eu/resource/authority/licence"
    countriesRemote = "http://publications.europa.eu/resource/authority/country"
    continentsRemote = "http://publications.europa.eu/resource/authority/continent"
    placesRemote = "http://publications.europa.eu/resource/authority/place"
}

object DataTheme : ConceptScheme("data-theme-skos.rdf", dataThemeRemote)
object FileType : ConceptScheme("filetypes-skos.rdf", fileTypeRemote)
object CorporateBodies : ConceptScheme("corporatebodies-skos-ap-act.rdf", corporateBodiesRemote)
object Countries : ConceptScheme("countries-skos-ap-act.rdf", countriesRemote) {
    fun iso31661alpha2(concept: Concept): String? {
        val xlNotationProperty =
            concept.resource.model.createProperty("http://publications.europa.eu/ontology/euvoc#", "xlNotation")
        val xlCodificationProperty =
            concept.resource.model.createProperty("http://publications.europa.eu/ontology/euvoc#", "xlCodification")
        val iso31661alpha2Resource =
            concept.resource.model.createProperty("http://publications.europa.eu/resource/authority/notation-type/ISO_3166_1_ALPHA_2")

        var isoAlpha2Code: String? = null
        concept.resource.listProperties(xlNotationProperty).forEachRemaining {
            it.resource?.getPropertyResourceValue(DCTerms.type)?.run {
                if (this == iso31661alpha2Resource) {
                    isoAlpha2Code = it.getProperty(xlCodificationProperty).string.toLowerCase()
                }
            }
        }
        return isoAlpha2Code
    }
}

object Continents : ConceptScheme("continents-skos.rdf", continentsRemote)

object Places : ConceptScheme("places-skos-ap-act.rdf", placesRemote) {
    fun countryOf(concept: Concept): Concept? =
        concept.resource.getProperty(concept.resource.model.createProperty("http://www.opengis.net/ont/geosparql#", "sfWithin"))?.let {
            Countries.getConcept(it.resource)
        }
}

object Languages : ConceptScheme("languages-skos.rdf", languagesRemote) {
    fun iso6391Code(language: Concept): String? =
        model.listObjectsOfProperty(language.resource, AT.opMappedCode).toList().find {
            it.asResource()?.getProperty(DC_11.source)?.`object`?.asLiteral()?.string == "iso-639-1"
        }?.asResource()?.getProperty(AT.legacyCode)?.`object`?.asLiteral()?.string
}

object License : ConceptScheme("licences-skos.rdf", licenseRemote) {
    init {
        model.readXmlResource("piveau-licences-skos.rdf")
    }

    fun exactMatch(value: String): Concept? =
        model.listSubjectsWithProperty(SKOS.exactMatch, model.createResource(value)).nextOptional().orElse(null)?.let {
            Concept(it)
        }

    fun licenseAssistant(concept: Concept): String? =
        concept.resource.getProperty(EDP.licensingAssistant)?.resource?.uri
}

object PiveauScoring : ConceptScheme("piveau-scoring-skos.rdf") {
    fun abstract(score: Int): Resource = when (score) {
        in 0..119 -> model.getResource(PS.bad.uri)
        in 120..220 -> model.getResource(PS.enough.uri)
        in 221..350 -> model.getResource(PS.good.uri)
        else -> model.getResource(PS.excellent.uri)
    }
}

suspend fun Model.fetchVocabulary(uri: String, resource: String) {
    val sendPromise = Promise.promise<HttpResponse<Buffer>>()
    client.getAbs(uri).expect(ResponsePredicate.SC_OK).send(sendPromise)
    sendPromise.future().await()
    if (sendPromise.future().failed()) {
        removeAll()
        readXmlResource(resource)
        return;
    } else {
        RDFParser.create()
            .source(ByteArrayInputStream(sendPromise.future().result().bodyAsString().toByteArray()))
            .lang(Lang.RDFXML)
            .parse(this@fetchVocabulary)
    }

    val futures = mutableListOf<Future<Void>>()

    val iterator = listSubjectsWithProperty(SKOS.inScheme, createResource(uri))
    while (iterator.hasNext()) {
        val promise = Promise.promise<Void>()
        client.getAbs(iterator.nextResource().uri).expect(ResponsePredicate.SC_OK).send {
            if (it.succeeded()) {
                RDFParser.create()
                    .source(ByteArrayInputStream(it.result().bodyAsString().toByteArray()))
                    .lang(Lang.RDFXML)
                    .parse(this@fetchVocabulary)
                promise.complete()
            } else {
                promise.fail(it.cause())
            }
        }
        futures.add(promise.future())
    }
    CompositeFuture.all(futures as List<Future<Any>>?).setHandler {
        if (it.failed()) {
            removeAll()
            readXmlResource(resource)
        }
    }.setHandlerAwait()
}

fun Model.readTurtleResource(resource: String): Model =
    apply { RDFDataMgr.read(this, resource.loadResource(), Lang.TURTLE) }

fun Model.readXmlResource(resource: String): Model =
    apply { RDFDataMgr.read(this, resource.loadResource(), Lang.RDFXML) }

fun String.loadResource(): InputStream? = object {}.javaClass.classLoader.getResourceAsStream(this)

fun Resource.isA(resource: Resource): Boolean = listProperties(RDF.type).toSet().any { it.`object` == resource }
