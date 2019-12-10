package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

object AT {
    const val NS = "http://publications.europa.eu/ontology/authority/"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val opMappedCode: Property = m.createProperty(NS, "op-mapped-code")
    @JvmField
    val legacyCode: Property = m.createProperty(NS, "legacy-code")
}
