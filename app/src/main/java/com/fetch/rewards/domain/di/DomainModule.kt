package com.fetch.rewards.domain.di

import org.koin.dsl.module

val domainModule = module {
    useCaseModule()
}
