package org.aman.coding

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform