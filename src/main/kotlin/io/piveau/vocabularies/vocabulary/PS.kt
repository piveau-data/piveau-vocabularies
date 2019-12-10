package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Resource

object PS {
    const val NS = "https://piveau.eu/ns/scoring#"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val scoring: Resource = m.createResource("${NS}scoring")
    @JvmField
    val excellent: Resource = m.createResource("${NS}excellent")
    @JvmField
    val good: Resource = m.createResource("${NS}good")
    @JvmField
    val enough: Resource = m.createResource("${NS}enough")
    @JvmField
    val bad: Resource = m.createResource("${NS}bad")
}
