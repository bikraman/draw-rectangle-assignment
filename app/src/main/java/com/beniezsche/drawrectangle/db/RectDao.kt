package com.beniezsche.drawrectangle.db

import androidx.room.*
import com.beniezsche.drawrectangle.models.RectItem

@Dao
interface RectItemDao {
    @Query("SELECT * FROM rect_items")
    suspend fun getAllRectItems(): List<RectItem>

    @Insert
    suspend fun insertRectItem(rectItem: RectItem): Long

    @Query("DELETE FROM rect_items")
    suspend fun deleteAllRectItems()
}