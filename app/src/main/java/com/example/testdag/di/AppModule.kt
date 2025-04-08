package com.example.testdag.di

import android.content.Context
import dagger.Module
import dagger.Provides
import com.example.testdag.data.database.AppDatabase
import com.example.testdag.data.repository.CartRepository
import com.example.testdag.data.repository.ProductRepository

@Module
class AppModule(private val application: MainApplication) {

    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideProductRepository(database: AppDatabase): ProductRepository {
        return ProductRepository(database.productDao())
    }

    @Provides
    fun provideCartRepository(database: AppDatabase): CartRepository {
        return CartRepository(database.cartDao())
    }
}