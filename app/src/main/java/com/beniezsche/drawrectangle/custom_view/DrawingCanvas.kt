package com.beniezsche.drawrectangle.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class DrawingCanvas(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {


    private val rects = ArrayList<RectItem>()

    private var downX = 0f
    private var downY = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y

                Log.d("DrawRect", "downX: $downX downY: $downY")

                val rect = RectF(downX, downY, 0f,0f)
                val color = Color.rgb((0..255).random(), (0..255).random(), (0..255).random())

                rects.add(RectItem(rect, color))
            }
            MotionEvent.ACTION_MOVE -> {

                val moveY = event.y
                val moveX = event.x

                Log.d("DrawRect", "moveX: $moveX moveY: $moveY")

                rects[rects.size - 1].rectF.bottom = moveY
                rects[rects.size - 1].rectF.right = moveX
            }
            MotionEvent.ACTION_UP -> {
                downX = 0f
                downY = 0f
            }

        }

        invalidate()

        return true
    }

    fun clear() {
        rects.clear()
        invalidate()
    }

    val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (rect:RectItem in rects) {
            Log.d("DrawRect", rect.rectF.toShortString())
            paint.color = rect.color
            canvas.drawRect(rect.rectF, paint)
        }
    }

    class RectItem(val rectF: RectF, val color: Int)
}