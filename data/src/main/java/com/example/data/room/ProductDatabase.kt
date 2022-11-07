package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.room.converter.RoomConverter

@Database(entities = [ProductEntity::class,CartEntity::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract val productDao : ProductDao
}