//package com.capstone.cookpocket.Database
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import androidx.room.Delete
//import androidx.room.OnConflictStrategy
//
//@Dao
//interface FavoriteItemDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(favoriteItem: FavoriteItem)
//
//    @Delete
//    suspend fun delete(favoriteItem: FavoriteItem)
//
//    @Query("SELECT * FROM FavoriteItem")
//    fun getAllFavorites(): LiveData<List<FavoriteItem>>
//
//    @Query("SELECT * FROM FavoriteItem WHERE storyId = :id")
//    fun getFavoriteByStoryId(id: String): LiveData<FavoriteItem?>
//
//
//}
