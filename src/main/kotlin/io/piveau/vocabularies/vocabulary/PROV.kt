package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

object PROV {
    const val NS = "http://www.w3.org/ns/prov#"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val generatedAtTime: Property = m.createProperty(NS, "generatedAtTime")

    @JvmField
    val wasDerivedFrom: Property = m.createProperty(NS, "wasDerivedFrom")

    @JvmField
    val wasAttributedTo: Property = m.createProperty(NS, "wasAttributedTo")

    @JvmField
    val wasGeneratedBy: Property = m.createProperty(NS, "wasGeneratedBy")
}
