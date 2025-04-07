package com.example.testdag.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testdag.data.model.Product
import com.example.testdag.data.model.CartItem

@Database(entities = [Product::class, CartItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "testdag_database"
                ).build().also { instance = it }
            }
        }
    }
}