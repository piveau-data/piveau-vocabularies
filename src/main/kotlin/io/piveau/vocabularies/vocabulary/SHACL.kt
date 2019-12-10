package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource

object SHACL {
    const val NS = "http://www.w3.org/ns/shacl#"
    private val m = ModelFactory.createDefaultModel()

    @JvmField
    val ValidationReport: Resource = m.createResource("${NS}ValidationReport")
    @JvmField
    val ValidationResult: Resource = m.createResource("${NS}ValidationResult")
    @JvmField
    val Violation: Resource = m.createResource("${NS}Violation")
    @JvmField
    val result: Property = m.createProperty(NS, "result")
    @JvmField
    val resultMessage: Property = m.createProperty(NS, "resultMessage")
    @JvmField
    val resultSeverity: Property = m.createProperty(NS, "resultSeverity")
    @JvmField
    val sourceConstraintComponent: Property = m.createProperty(NS, "sourceConstraintComponent")

}
