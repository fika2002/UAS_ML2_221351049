package com.example.flightticketpriceprediction

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil

class SimulasiActivity : AppCompatActivity() {

    lateinit var tflite: Interpreter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulasi)

        // Inisialisasi Model TFLite
        val model = FileUtil.loadMappedFile(this, "flight_price_model.tflite")
        tflite = Interpreter(model)

        // Dropdowns
        val maskapaiList = arrayOf("AirAsia", "IndiGo", "SpiceJet", "Vistara", "GoAir")
        val kotaList = arrayOf("Delhi", "Mumbai", "Bangalore", "Kolkata", "Hyderabad")
        val waktuList = arrayOf("Pagi", "Siang", "Sore", "Malam")
        val kelasList = arrayOf("Business", "Economy")

        val spMaskapai = findViewById<Spinner>(R.id.spMaskapai)
        val spAsal = findViewById<Spinner>(R.id.spAsal)
        val spTujuan = findViewById<Spinner>(R.id.spTujuan)
        val spBerangkat = findViewById<Spinner>(R.id.spBerangkat)
        val spTiba = findViewById<Spinner>(R.id.spTiba)
        val spKelas = findViewById<Spinner>(R.id.spKelas)

        spMaskapai.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, maskapaiList)
        spAsal.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kotaList)
        spTujuan.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kotaList)
        spBerangkat.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, waktuList)
        spTiba.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, waktuList)
        spKelas.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kelasList)

        val durasiInput = findViewById<EditText>(R.id.editDuration)
        val hariInput = findViewById<EditText>(R.id.editHari)
        val hasilText = findViewById<TextView>(R.id.txtHasil)

        val prediksiBtn = findViewById<Button>(R.id.btnPrediksi)
        prediksiBtn.setOnClickListener {
            val airline = spMaskapai.selectedItem.toString()
            val asal = spAsal.selectedItem.toString()
            val tujuan = spTujuan.selectedItem.toString()
            val berangkat = spBerangkat.selectedItem.toString()
            val tiba = spTiba.selectedItem.toString()
            val kelas = spKelas.selectedItem.toString()
            val durasi = durasiInput.text.toString().toFloatOrNull() ?: 0f
            val hari = hariInput.text.toString().toFloatOrNull() ?: 0f

            val airlineDict = mapOf("AirAsia" to 0, "IndiGo" to 1, "SpiceJet" to 2, "Vistara" to 3, "GoAir" to 4)
            val cityDict = mapOf("Delhi" to 0, "Mumbai" to 1, "Bangalore" to 2, "Kolkata" to 3, "Hyderabad" to 4)
            val timeDict = mapOf("Pagi" to 0, "Siang" to 1, "Sore" to 2, "Malam" to 3)
            val classDict = mapOf("Business" to 0, "Economy" to 1)

            val inputArray = floatArrayOf(
                airlineDict[airline]!!.toFloat(),
                cityDict[asal]!!.toFloat(),
                cityDict[tujuan]!!.toFloat(),
                timeDict[berangkat]!!.toFloat(),
                timeDict[tiba]!!.toFloat(),
                durasi,
                classDict[kelas]!!.toFloat(),
                hari
            )

            val input = Array(1) { inputArray }
            val output = Array(1) { FloatArray(1) }
            tflite.run(input, output)

            val inr = output[0][0]
            val idr = inr * 191

            hasilText.text = "Perkiraan Harga Tiket:\nINR: ₹${inr.toInt()}\nIDR: Rp ${idr.toInt()}"
        }
    }
}
