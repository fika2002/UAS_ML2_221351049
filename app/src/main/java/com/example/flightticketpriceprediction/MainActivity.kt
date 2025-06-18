package com.example.flightticketpriceprediction
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnTentang).setOnClickListener {
            startActivity(Intent(this, TentangActivity::class.java))
        }
        findViewById<Button>(R.id.btnFitur).setOnClickListener {
            startActivity(Intent(this, FiturActivity::class.java))
        }
        findViewById<Button>(R.id.btnArsitektur).setOnClickListener {
            startActivity(Intent(this, ArsitekturActivity::class.java))
        }
        findViewById<Button>(R.id.btnSimulasi).setOnClickListener {
            startActivity(Intent(this, SimulasiActivity::class.java))
        }
    }
}