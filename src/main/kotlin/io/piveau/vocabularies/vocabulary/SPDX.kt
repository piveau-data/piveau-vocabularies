package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource

object SPDX {
    const val NS = "https://spdx.org/rdf/terms/#"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val Checksum: Resource = m.createResource("${NS}Checksum")
    @JvmField
    val checksumAlgorithm_md5: Resource = m.createResource("${NS}checksumAlgorithm_md5")

    @JvmField
    val checksum: Property = m.createProperty(NS, "checksum")
    @JvmField
    val algorithm: Property = m.createProperty(NS, "algorithm")
    @JvmField
    val sample: Property = m.createProperty(NS, "sample")
    @JvmField
    val checksumValue: Property = m.createProperty(NS, "checksumValue")
}
