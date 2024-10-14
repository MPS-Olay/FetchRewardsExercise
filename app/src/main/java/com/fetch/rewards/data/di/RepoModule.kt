package com.fetch.rewards.data.di

import com.fetch.rewards.data.repo.HiringRepoImpl
import com.fetch.rewards.data.repo.HiringRepository
import org.koin.core.module.Module

internal fun Module.repoModule()  {
    factory<HiringRepository> { HiringRepoImpl(client = get()) }
}
