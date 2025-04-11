package com.example.quickbiteapp.di

import com.example.quickbiteapp.ui.adapter.CartAdapter
import com.example.quickbiteapp.ui.cart.CartFragment
import com.example.quickbiteapp.ui.menu.MenuFragment
import com.example.quickbiteapp.ui.menu.MenuItemFragment
import com.example.quickbiteapp.ui.restaurant.RestaurantListFragment
import com.example.quickbiteapp.ui.store.StoreFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun storeFragment(): StoreFragment
    fun cartFragment(): CartFragment
    fun restaurantListFragment(): RestaurantListFragment
    fun menuFragment(): MenuFragment
    fun menuItemFragment(): MenuItemFragment
    fun cartAdapter(): CartAdapter

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}