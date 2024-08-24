package com.beniezsche.drawrectangle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.beniezsche.drawrectangle.custom_view.DrawingCanvas

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clearButton = findViewById<Button>(R.id.clear_button)
        val drawingCanvas = findViewById<DrawingCanvas>(R.id.drawing_canvas)

        clearButton.setOnClickListener {
            drawingCanvas.clear()
        }

    }
}