package com.capstone.cookpocket.Network.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favEvent: FavoriteItem)

    @Delete
    suspend fun deleteFavorite(favEvent: FavoriteItem)

    @Query("SELECT * FROM FavoriteItem WHERE id = :id")
    fun getFavoriteById(id: String): LiveData<FavoriteItem?>

    @Query("SELECT * FROM FavoriteItem")
    fun getAllFavorites(): LiveData<List<FavoriteItem>>
}