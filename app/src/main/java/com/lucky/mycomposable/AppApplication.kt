package com.lucky.mycomposable

import android.app.Application
import com.lucky.mycomposable.koin.appModule
import com.lucky.mycomposable.koin.networkModule
import com.lucky.mycomposable.koin.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(appModule,networkModule,viewModelsModule)
        }
    }
}