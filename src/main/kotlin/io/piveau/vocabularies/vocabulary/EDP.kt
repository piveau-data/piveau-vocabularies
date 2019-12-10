package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Resource

object EDP {
    const val NS = "https://europeandataportal.eu/voc#"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val TransInProcess: Resource = m.createResource("${NS}TransInProcess")
    @JvmField
    val TransCompleted: Resource = m.createResource("${NS}TransCompleted")

    @JvmField
    val licensingAssistant = m.createProperty("${NS}licensingAssistant")

    @JvmField
    val translationIssued = m.createProperty("${NS}transIssued")
    @JvmField
    val translationReceived = m.createProperty("${NS}transReceived")
    @JvmField
    val translationStatus = m.createProperty("${NS}transStatus")
    @JvmField
    val originalLanguage = m.createProperty("${NS}originalLanguage")

    @JvmField
    val harvestEndpoint = m.createProperty("${NS}harvestEndpoint")
}
