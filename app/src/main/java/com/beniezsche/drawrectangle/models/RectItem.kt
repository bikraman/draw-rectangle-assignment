package com.beniezsche.drawrectangle.models

import android.graphics.RectF
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "rect_items")
class RectItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val left: Float,
    val top: Float,
    var right: Float,
    var bottom: Float,
    val color: Int
)