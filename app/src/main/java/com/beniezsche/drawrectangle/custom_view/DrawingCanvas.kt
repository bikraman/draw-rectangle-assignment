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


    private val rects = ArrayList<RectItem>()

    private var downX = 0f
    private var downY = 0f

    private var moveX = 0f
    private var moveY = 0f

    private val paint = Paint()


    private var isRectangleBeingDrawn = false

    private var currentColor = Color.TRANSPARENT

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y

                currentColor = Color.rgb((0..255).random(),(0..255).random(), (0..255).random() )

            }
            MotionEvent.ACTION_MOVE -> {

                if (abs(moveX - downX) > 10 || abs(moveY - downY) > 10)
                    isRectangleBeingDrawn = true

                moveY = event.y
                moveX = event.x
            }
            MotionEvent.ACTION_UP -> {

                isRectangleBeingDrawn = false

                rects.add(RectItem(0, downX, downY, moveX, moveY, currentColor))

                currentColor = Color.TRANSPARENT
                downX = 0f
                downY = 0f

                moveX = 0f
                moveY = 0f
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


}