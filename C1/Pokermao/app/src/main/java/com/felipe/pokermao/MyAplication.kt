package com.felipe.pokermao

import android.app.Application
import com.facebook.stetho.Stetho
import com.felipe.pokermao.di.networkModule
import com.felipe.pokermao.di.respositoryModule
import com.felipe.pokermao.di.viewModelModule
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
                    respositoryModule,
                    viewModelModule
                )
            )
        }
    }
}