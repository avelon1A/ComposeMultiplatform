package org.aman.coding.data

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val title: String,
    val problemsSolved: Int,
    val totalProblems: Int,
    val status: String
)



@Serializable
data class DataList(
    val data: List<Category>,
    val total: Int,
)

