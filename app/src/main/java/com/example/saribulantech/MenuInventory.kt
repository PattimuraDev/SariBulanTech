package com.example.saribulantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MenuInventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_inventory)

        val tambahDataInventory = findViewById<TextView>(R.id.TambahDataInventory)
        val updateDataInventory  = findViewById<TextView>(R.id.UpdateDataInventory)
        val lihatDataInventory = findViewById<TextView>(R.id.LihatDataInventory)
        val hapusDataInventory = findViewById<TextView>(R.id.HapusDataInventory)

        tambahDataInventory.setOnClickListener {
            startActivity(Intent(this, TambahDataInventory::class.java))
        }
        updateDataInventory.setOnClickListener {
            startActivity(Intent(this, UpdateDataInventory::class.java))
        }
        hapusDataInventory.setOnClickListener {
            startActivity(Intent(this, HapusDataInventory::class.java))
        }
        lihatDataInventory.setOnClickListener {
            startActivity(Intent(this, LihatDataInventory::class.java))
        }
    }
}