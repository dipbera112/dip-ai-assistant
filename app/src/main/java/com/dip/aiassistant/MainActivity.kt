package com.dip.aiassistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this).apply {
            text = "Dip AI Assistant\nStage 1 - Clean Build\nReady for Stage 2"
            textSize = 20f
            setPadding(32, 32, 32, 32)
        }

        setContentView(textView)
        ViewCompat.setOnApplyWindowInsetsListener(textView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
