package org.aman.coding.domain.model.respnonse

import kotlinx.serialization.Serializable

@Serializable
data class Problem(
    val id:Int,
    val name: String,
    val isCompleted: Boolean,
    val isBookmarked: Boolean,
    val isSkipped: Boolean,
    val isImportant: Boolean
)
