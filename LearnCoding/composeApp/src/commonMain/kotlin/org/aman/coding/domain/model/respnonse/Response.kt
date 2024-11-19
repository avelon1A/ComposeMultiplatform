package org.aman.coding.domain.model.respnonse

import kotlinx.serialization.Serializable

@Serializable
data class MyResponse(val status: String ,val method:String)