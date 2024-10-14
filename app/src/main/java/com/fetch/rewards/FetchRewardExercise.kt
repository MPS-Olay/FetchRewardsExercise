package com.fetch.rewards

import android.app.Application
import com.fetch.rewards.common.di.commonModule
import com.fetch.rewards.data.di.dataModule
import com.fetch.rewards.domain.di.domainModule
import com.fetch.rewards.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FetchRewardExercise : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@FetchRewardExercise)
            //Load Modules
            modules(commonModule, dataModule, domainModule, presentationModule)
        }
    }
}