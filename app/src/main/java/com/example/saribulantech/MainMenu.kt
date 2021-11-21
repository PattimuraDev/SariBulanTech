package com.example.saribulantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnTransaksi = findViewById<LinearLayout>(R.id.transaksi)
        val btnInventory = findViewById<LinearLayout>(R.id.inventory)
        val btnBahan = findViewById<LinearLayout>(R.id.bahan)

        btnTransaksi.setOnClickListener {
            val intent = Intent(this, MenuTransaksi::class.java)
            startActivity(intent)
        }
        btnInventory.setOnClickListener {
            val intent = Intent(this, MenuInventory::class.java)
            startActivity(intent)
        }
        btnBahan.setOnClickListener {
            startActivity(Intent(this, MenuBahan::class.java))
        }


    }
}