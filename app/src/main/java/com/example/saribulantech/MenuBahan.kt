package com.example.saribulantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MenuBahan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_bahan)
        val tambahDataBahan = findViewById<TextView>(R.id.TambahDataBahan)
        val UpdateDataBahan = findViewById<TextView>(R.id.UpdateDataBahan)
        val LihatDataBahan = findViewById<TextView>(R.id.LihatDataBahan)
        val HapusdataBahan = findViewById<TextView>(R.id.HapusDataBahan)

        tambahDataBahan.setOnClickListener {
            val intent = Intent(this, TambahDataBahan::class.java)
            startActivity(intent)
        }
    }
}