package com.fetch.rewards.domain.di

import com.fetch.rewards.domain.usecase.GetHiringListFormattedUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun Module.useCaseModule() {
    factory { GetHiringListFormattedUseCase(repo = get()) }
}