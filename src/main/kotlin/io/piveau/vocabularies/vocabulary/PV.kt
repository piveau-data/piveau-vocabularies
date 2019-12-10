package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource

object PV {
    const val NS = "https://piveau.eu/ns/voc#"
    private val m = ModelFactory.createDefaultModel()

    // Dimensions
    @JvmField
    val accessibility: Resource = m.createResource("${NS}accessibility")
    @JvmField
    val contextuality: Resource = m.createResource("${NS}contextuality")
    @JvmField
    val findability: Resource = m.createResource("${NS}findability")
    @JvmField
    val interoperability: Resource = m.createResource("${NS}interoperability")
    @JvmField
    val reusability: Resource = m.createResource("${NS}reusability")

    // Metrics
    @JvmField
    val accessRightsAvailability: Resource = m.createResource("${NS}accessRightsAvailability")
    @JvmField
    val accessRightsVocabularyAlignment: Resource = m.createResource("${NS}accessRightsVocabularyAlignment")
    @JvmField
    val accessUrlStatusCode: Resource = m.createResource("${NS}accessUrlStatusCode")
    @JvmField
    val byteSizeAvailability: Resource = m.createResource("${NS}byteSizeAvailability")
    @JvmField
    val categoryAvailability: Resource = m.createResource("${NS}categoryAvailability")
    @JvmField
    val contactPointAvailability: Resource = m.createResource("${NS}contactPointAvailability")
    @JvmField
    val dateIssuedAvailability: Resource = m.createResource("${NS}dateIssuedAvailability")
    @JvmField
    val dateModifiedAvailability: Resource = m.createResource("${NS}dateModifiedAvailability")
    @JvmField
    val dcatApCompliance: Resource = m.createResource("${NS}dcatApCompliance")
    @JvmField
    val downloadUrlAvailability: Resource = m.createResource("${NS}downloadUrlAvailability")
    @JvmField
    val downloadUrlStatusCode: Resource = m.createResource("${NS}downloadUrlStatusCode")
    @JvmField
    val formatAvailability: Resource = m.createResource("${NS}formatAvailability")
    @JvmField
    val formatMediaTypeMachineInterpretable: Resource = m.createResource("${NS}formatMediaTypeMachineInterpretable")
    @JvmField
    val formatMediaTypeNonProprietary: Resource = m.createResource("${NS}formatMediaTypeNonProprietary")
    @JvmField
    val formatMediaTypeVocabularyAlignment: Resource = m.createResource("${NS}formatMediaTypeVocabularyAlignment")
    @JvmField
    val keywordAvailability: Resource = m.createResource("${NS}keywordAvailability")
    @JvmField
    val knownLicence: Resource = m.createResource("${NS}knownLicence")
    @JvmField
    val licenceAvailability: Resource = m.createResource("${NS}licenceAvailability")
    @JvmField
    val mediaTypeAvailability: Resource = m.createResource("${NS}mediaTypeAvailability")
    @JvmField
    val publisherAvailability: Resource = m.createResource("${NS}publisherAvailability")
    @JvmField
    val rightsAvailability: Resource = m.createResource("${NS}rightsAvailability")
    @JvmField
    val spatialAvailability: Resource = m.createResource("${NS}spatialAvailability")
    @JvmField
    val temporalAvailability: Resource = m.createResource("${NS}temporalAvailability")
    @JvmField
    val scoring: Resource = m.createResource("${NS}scoring")

    @JvmField
    val trueScore: Property = m.createProperty(NS, "trueScore")
    @JvmField
    val falseScore: Property = m.createProperty(NS, "falseScore")
}
