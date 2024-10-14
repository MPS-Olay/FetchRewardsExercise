package com.fetch.rewards.presentation.di

import com.fetch.rewards.presentation.hiringList.HiringViewModel
import org.koin.core.module.Module

internal fun Module.viewModelModule() {
    factory { HiringViewModel(hiringListFormattedUseCase = get()) }
}
