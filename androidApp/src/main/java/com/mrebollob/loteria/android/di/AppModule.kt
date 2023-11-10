package com.mrebollob.loteria.android.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.mrebollob.loteria.android.analytics.AnalyticsManager
import com.mrebollob.loteria.android.presentation.create.CreateViewModel
import com.mrebollob.loteria.android.presentation.home.HomeViewModel
import com.mrebollob.loteria.android.presentation.settings.manageticket.ManageTicketsViewModel
import com.mrebollob.loteria.android.presentation.settings.menu.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get(), get(), get()) }

    viewModel { CreateViewModel(get()) }

    viewModel { SettingsViewModel(get(), get()) }

    viewModel { ManageTicketsViewModel(get(), get()) }

    single { FirebaseAnalytics.getInstance(get()) }

    single { AnalyticsManager(get()) }
}
