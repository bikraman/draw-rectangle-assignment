package com.beniezsche.drawrectangle.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.beniezsche.drawrectangle.models.RectItem
import kotlin.math.abs

class DrawingCanvas(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val rects = ArrayList<RectItem>()

    private var downX = 0f
    private var downY = 0f

    private var moveX = 0f
    private var moveY = 0f

    private val paint = Paint()

    lateinit var drawingListener: DrawingListener

    private var isRectangleBeingDrawn = false

    private var currentColor = Color.TRANSPARENT

    private var THRESHOLD = 30


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y

                currentColor = getRandomColor()

            }
            MotionEvent.ACTION_MOVE -> {

                if (abs(moveX - downX) > THRESHOLD || abs(moveY - downY) > THRESHOLD)
                    isRectangleBeingDrawn = true

                moveY = event.y
                moveX = event.x
            }
            MotionEvent.ACTION_UP -> {

                if (isRectangleBeingDrawn) {

                    isRectangleBeingDrawn = false

                    val rectItem = RectItem(0, downX, downY, moveX, moveY, currentColor)
                    rects.add(rectItem)

                    drawingListener.onDrawingRectangleComplete(rectItem)

                    currentColor = Color.TRANSPARENT
                    downX = 0f
                    downY = 0f

                    moveX = 0f
                    moveY = 0f
                }
            }
        }

        invalidate()

        return true
    }

    fun clear() {
        rects.clear()
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {

        for (rectItem in rects) {
            paint.color = rectItem.color
            canvas.drawRect(rectItem.left, rectItem.top, rectItem.right, rectItem.bottom, paint)
        }

        if (isRectangleBeingDrawn) {
            paint.color = currentColor
            canvas.drawRect(downX, downY, moveX, moveY, paint)
        }
    }

    private fun getRandomColor() : Int {
        return Color.rgb((0..255).random(),(0..255).random(), (0..255).random() )
    }

    interface DrawingListener {
        fun onDrawingRectangleComplete(rectItem: RectItem)
    }


}