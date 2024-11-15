package org.aman.coding.di

import org.aman.coding.data.remote.ApiClient
import org.aman.coding.presentaion.viewmodels.CodingViewModel
import org.aman.coding.presentaion.viewmodels.ListDetailsViewModel
import org.aman.coding.presentaion.viewmodels.LoginViewModel
import org.aman.coding.presentaion.viewmodels.MainScrenViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


fun provideClientModule(client: ApiClient) = module {
    single { client }
}

val provideViewModelModule = module {
    viewModel { MainScrenViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ListDetailsViewModel(get()) }
    viewModel { CodingViewModel(get()) }
}

fun appModule(client: ApiClient): List<Module> {
    return listOf(provideViewModelModule , provideClientModule(client))

}