package com.example.flightticketpriceprediction

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArsitekturActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arsitektur)

        findViewById<TextView>(R.id.textArsitektur).text = """
            Model: Artificial Neural Network (ANN)
            Tools: TensorFlow + TensorFlow Lite (TFLite)
            Input: 8 fitur numerik/kategorikal
            Output: Harga Tiket dalam INR
        """.trimIndent()
    }
}
