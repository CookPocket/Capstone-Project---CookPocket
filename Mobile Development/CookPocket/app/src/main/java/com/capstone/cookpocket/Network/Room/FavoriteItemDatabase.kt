package com.capstone.cookpocket.Network.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavoriteItem::class], version = 1, exportSchema = false)
abstract class FavoriteItemDatabase : RoomDatabase() {
    abstract fun favoriteItemDao(): FavoriteItemDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteItemDatabase? = null

        fun getDatabase(context: Context): FavoriteItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteItemDatabase::class.java,
                    "favorite_event_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}