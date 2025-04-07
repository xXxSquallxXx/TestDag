package com.example.testdag.di

import android.content.Context
import dagger.Module
import dagger.Provides
import com.example.testdag.data.database.AppDatabase
import com.example.testdag.data.repository.CartRepository
import com.example.testdag.data.repository.ProductRepository
import com.example.testdag.ui.cart.CartViewModel
import com.example.testdag.ui.store.StoreViewModel

@Module
object AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
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

    @Provides
    fun provideStoreViewModel(productRepository: ProductRepository, cartRepository: CartRepository): StoreViewModel {
        return StoreViewModel(productRepository, cartRepository)
    }

    @Provides
    fun provideCartViewModel(cartRepository: CartRepository): CartViewModel {
        return CartViewModel(cartRepository)
    }
}