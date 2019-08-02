package com.desiglo.jokenpo

import android.app.Application
import com.desiglo.jokenpo.di.networkModule
import com.desiglo.jokenpo.di.respositoryModule
import com.desiglo.jokenpo.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyAplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidLogger()
            androidContext(this@MyAplication)
            modules(
                listOf(
                    networkModule,
                    respositoryModule ,
                     viewModelModule
                )
            )
        }
    }
}