package com.example.listacompras.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: ItemEntity): Long

    @Delete
    suspend fun delete(item: ItemEntity): Int

    @Query("select * from itemEntity")
    suspend fun getAll():List<ItemEntity>
}