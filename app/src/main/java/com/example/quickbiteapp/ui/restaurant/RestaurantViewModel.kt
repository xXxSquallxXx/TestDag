package com.example.quickbiteapp.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickbiteapp.data.api.RestaurantApi
import com.example.quickbiteapp.data.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(
    private val restaurantApi: RestaurantApi
) : ViewModel() {

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> get() = _restaurants

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        viewModelScope.launch {
            try {
                val restaurantList = restaurantApi.getRestaurants()
                _restaurants.value = restaurantList
            } catch (_: Exception) {
                _restaurants.value = emptyList()
            }
        }
    }
}