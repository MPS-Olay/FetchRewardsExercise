package com.fetch.rewards.data.di

import org.koin.dsl.module

val dataModule = module {
    networkModule()
    repoModule()
}
