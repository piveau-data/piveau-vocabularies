package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource

object HYDRA {
    const val NS = "http://www.w3.org/ns/hydra/core#"
    private val m = ModelFactory.createDefaultModel()

    @JvmStatic
    val PagedCollection: Resource = m.createResource("${NS}PagedCollection")
    @JvmStatic
    val PartialCollectionView: Resource = m.createResource("${NS}PartialCollectionView")

    @JvmStatic
    val totalItems: Property = m.createProperty(NS, "totalItems")

    @JvmStatic
    val next: Property = m.createProperty(NS, "next")
    @JvmStatic
    val nextPage: Property = m.createProperty(NS, "nextPage")
}

