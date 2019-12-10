package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

object ADMS {
    const val NS = "http://www.w3.org/ns/adms#"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val status: Property = m.createProperty(NS, "status")
    @JvmField
    val identifier: Property = m.createProperty(NS, "identifier")
    @JvmField
    val sample: Property = m.createProperty(NS, "sample")
    @JvmField
    val versionNotes: Property = m.createProperty(NS, "versionNotes")
}
