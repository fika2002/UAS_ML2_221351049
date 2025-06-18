package com.example.flightticketpriceprediction

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FiturActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitur)

        findViewById<TextView>(R.id.textFitur).text = """
            Fitur Aplikasi:
            - Prediksi harga tiket pesawat
            - Input maskapai, kota, waktu, durasi, kelas, dan sisa hari keberangkatan
            - Output dalam INR dan konversi ke Rupiah
        """.trimIndent()
    }
}