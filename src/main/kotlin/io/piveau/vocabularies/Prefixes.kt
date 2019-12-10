@file:JvmName(name = "Prefixes")
package io.piveau.vocabularies

import io.piveau.vocabularies.vocabulary.ADMS
import io.piveau.vocabularies.vocabulary.HYDRA
import io.piveau.vocabularies.vocabulary.SPDX
import org.apache.jena.sparql.vocabulary.FOAF
import org.apache.jena.vocabulary.*

@JvmField
val DCATAP_PREFIXES = mapOf(
    "dcat" to DCAT.NS,
    "skos" to SKOS.uri,
    "foaf" to FOAF.NS,
    "dct" to DCTerms.NS,
    "gmd" to "http://www.isotc211.org/2005/gmd#",
    "v" to VCARD4.NS,
    "adms" to ADMS.NS,
    "spdx" to SPDX.NS,
    "schema" to "http://schema.org/",
    "locn" to "http://www.w3.org/ns/locn#",
    "org" to ORG.NS,
    "time" to "http://www.w3.org/2006/time#",
    "hydra" to HYDRA.NS
)
