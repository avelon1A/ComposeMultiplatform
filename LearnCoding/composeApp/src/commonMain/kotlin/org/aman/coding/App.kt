package org.aman.coding

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.di.appModule
import org.aman.coding.presentation.navigation.NavigationHost
import org.koin.compose.KoinApplication

@Composable
fun App(client: ApiClient) {
    KoinApplication(application = {
        modules(appModule(client))
    }){
        MaterialTheme {
            val navController = rememberNavController()
            NavigationHost(navController = navController, client = client)
        }
    }

}


