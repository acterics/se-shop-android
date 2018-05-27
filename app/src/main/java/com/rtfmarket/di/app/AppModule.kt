package com.rtfmarket.di.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.executor.ThreadScheduler
import javax.inject.Singleton

/**
* Created by Oleg Lipskiy on 25.01.18.
*/
@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideExecutionScheduler(threadScheduler: ThreadScheduler): ExecutionScheduler {
       return threadScheduler
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = application.applicationContext

}