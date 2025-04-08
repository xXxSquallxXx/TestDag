package com.example.testdag.di

import dagger.Component
import com.example.testdag.ui.cart.CartFragment
import com.example.testdag.ui.store.StoreFragment
import dagger.BindsInstance

@Component(modules = [AppModule::class])
interface AppComponent {
    fun storeFragment(): StoreFragment
    fun cartFragment(): CartFragment

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}