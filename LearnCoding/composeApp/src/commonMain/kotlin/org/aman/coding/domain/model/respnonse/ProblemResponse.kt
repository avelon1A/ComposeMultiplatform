package org.aman.coding.domain.model.respnonse

import kotlinx.serialization.Serializable

@Serializable
data class ProblemDetails(
    val id: Int,
    val description: String,
    val examples: List<Example>
)

@Serializable
data class Example(
    val input: String,
    val output: String,
    val explanation: String
)