package org.aman.coding


import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.engine.cio.CIO
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.data.remote.createHttpClient
import java.awt.Desktop

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
        App(client = remember{
            ApiClient(createHttpClient(CIO.create()))
        } )
    }
}