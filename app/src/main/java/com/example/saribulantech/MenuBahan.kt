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
        val updateDataBahan = findViewById<TextView>(R.id.UpdateDataBahan)
        val lihatDataBahan = findViewById<TextView>(R.id.LihatDataBahan)
        val hapusdataBahan = findViewById<TextView>(R.id.HapusDataBahan)

        tambahDataBahan.setOnClickListener {
            val intent = Intent(this, TambahDataBahan::class.java)
            startActivity(intent)
        }
        updateDataBahan.setOnClickListener {
            val intent = Intent(this, UpdateDataBahan::class.java)
            startActivity(intent)
        }
    }
}