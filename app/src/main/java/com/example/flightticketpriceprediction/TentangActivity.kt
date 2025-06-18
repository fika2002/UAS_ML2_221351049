package com.example.flightticketpriceprediction

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TentangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang)

        findViewById<TextView>(R.id.textTentang).text = """
            Aplikasi ini dibuat untuk memprediksi harga tiket pesawat domestik di India
            berdasarkan fitur maskapai, kota asal dan tujuan, waktu keberangkatan dan kedatangan,
            durasi penerbangan, kelas penerbangan, dan sisa hari keberangkatan.
        """.trimIndent()
    }
}