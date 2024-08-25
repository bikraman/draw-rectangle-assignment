package com.beniezsche.drawrectangle.db

import androidx.room.*
import com.beniezsche.drawrectangle.models.RectItem

@Dao
interface RectItemDao {
    @Query("SELECT * FROM rect_items")
    suspend fun getAllRectItems(): List<RectItem>

    @Query("SELECT * FROM rect_items WHERE id = :id")
    suspend fun getRectItemById(id: Long): RectItem?

    @Insert
    suspend fun insertRectItem(rectItem: RectItem): Long

    @Update
    suspend fun updateRectItem(rectItem: RectItem)

    @Delete
    suspend fun deleteRectItem(rectItem: RectItem)

    @Query("DELETE FROM rect_items")
    suspend fun deleteAllRectItems()
}