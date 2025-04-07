package com.example.testdag.di

import android.app.Application

class MainApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}