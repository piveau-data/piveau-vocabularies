package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

object LOCN {
    const val NS = "http://www.w3.org/ns/locn#"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val geometry: Property = m.createProperty(NS, "geometry")
}
