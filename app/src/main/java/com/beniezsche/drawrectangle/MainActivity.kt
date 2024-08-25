package com.beniezsche.drawrectangle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beniezsche.drawrectangle.custom_view.DrawingCanvas
import com.beniezsche.drawrectangle.db.AppDatabase
import com.beniezsche.drawrectangle.models.RectItem
import com.beniezsche.drawrectangle.viewmodels.RectangleViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rectViewModel : RectangleViewModel = ViewModelProvider(this)[RectangleViewModel::class.java]
        rectViewModel.db = AppDatabase.getDatabase(this)

        val clearButton = findViewById<Button>(R.id.clear_button)
        val drawingCanvas = findViewById<DrawingCanvas>(R.id.drawing_canvas)

        clearButton.setOnClickListener {
            rectViewModel.clearAllRects().observe(this, Observer {
                if (it == true) drawingCanvas.clear()
            })
        }

        rectViewModel.getAllRects().observe(this, Observer {
            drawingCanvas.rects.addAll(it)
            drawingCanvas.invalidate()
        })

        drawingCanvas.drawingListener = object : DrawingCanvas.DrawingListener {
            override fun onDrawingRectangleComplete(rectItem: RectItem) {
                rectViewModel.addRect(rectItem)
            }
        }

    }
}