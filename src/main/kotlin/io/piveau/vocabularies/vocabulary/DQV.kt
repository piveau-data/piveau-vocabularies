package io.piveau.vocabularies.vocabulary

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource

/**
 * Vocabulary definition for the [Data Quality vocabulary](https://www.w3.org/TR/vocab-dqv/).
 *
 * @see [Turtle specification](https://github.com/w3c/dwbp/blob/gh-pages/dqv.ttl)
 */

object DQV {
    const val NS = "http://www.w3.org/ns/dqv#"
    private val m = ModelFactory.createDefaultModel()

    //classes
    @JvmField
    val Category: Resource = m.createResource("${NS}Category")
    @JvmField
    val Dimension: Resource = m.createResource("${NS}Dimension")
    @JvmField
    val Metric: Resource = m.createResource("${NS}Metric")
    @JvmField
    val QualityAnnotation: Resource = m.createResource("${NS}QualityAnnotation")
    @JvmField
    val QualityCertificate: Resource = m.createResource("${NS}QualityCertificate")
    @JvmField
    val QualityMetadata: Resource = m.createResource("${NS}QualityMetadata")
    @JvmField
    val QualityMeasurement: Resource = m.createResource("${NS}QualityMeasurement")
    @JvmField
    val QualityMeasurementDataset: Resource = m.createResource("${NS}QualityMeasurementDataset")
    @JvmField
    val QualityPolicy: Resource = m.createResource("${NS}QualityPolicy")
    @JvmField
    val UserQualityFeedback: Resource = m.createResource("${NS}UserQualityFeedback")

    //properties
    @JvmField
    val computedOn: Property = m.createProperty(NS, "computedOn")
    @JvmField
    val expectedDataType: Property = m.createProperty(NS, "expectedDataType")
    @JvmField
    val inCategory: Property = m.createProperty(NS, "inCategory")
    @JvmField
    val inDimension: Property = m.createProperty(NS, "inDimension")
    @JvmField
    val isMeasurementOf: Property = m.createProperty(NS, "isMeasurementOf")
    @JvmField
    val hasQualityAnnotation: Property = m.createProperty(NS, "hasQualityAnnotation")
    @JvmField
    val hasQualityMeasurement: Property = m.createProperty(NS, "hasQualityMeasurement")
    @JvmField
    val hasQualityMetadata: Property = m.createProperty(NS, "hasQualityMetadata")
    @JvmField
    val value: Property = m.createProperty(NS, "value")

}
