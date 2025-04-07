package com.example.testdag.di

import dagger.Component
import com.example.testdag.ui.cart.CartFragment
import com.example.testdag.ui.store.StoreFragment

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(storeFragment: StoreFragment)
    fun inject(cartFragment: CartFragment)
}