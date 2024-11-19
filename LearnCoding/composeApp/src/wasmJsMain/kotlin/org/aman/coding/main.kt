package org.aman.coding

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.ktor.client.engine.js.JsClient
import kotlinx.browser.document
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.data.remote.createHttpClient

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App(client = remember{
            ApiClient(createHttpClient(JsClient().create()))
        })

    }
}