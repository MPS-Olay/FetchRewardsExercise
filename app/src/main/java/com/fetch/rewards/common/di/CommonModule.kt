package com.fetch.rewards.common.di

import com.fetch.rewards.util.Logger
import com.fetch.rewards.util.SimpleLogger
import org.koin.dsl.module

val commonModule = module {
    single<Logger> { SimpleLogger("FetchRewardsExercise") }
}
