package com.example.techmarket

import android.app.Application
import com.example.techmarket.di.appModule
import com.example.techmarket.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TechMarketApplication():Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TechMarketApplication)
            modules(appModule)
            modules(viewModelModule)
        }
    }
}