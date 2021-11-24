package com.example.saribulantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MenuTransaksi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_transaksi)

        // actionbar
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = "Menu Transaksi"
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        val tambahDataTransaksi = findViewById<TextView>(R.id.TambahDataTransaksi)
        val updateDataTransaksi = findViewById<TextView>(R.id.UpdateDataTransaksi)
        val lihatDataTransaksi = findViewById<TextView>(R.id.LihatDataTransaksi)
        val hapusDataTransaksi = findViewById<TextView>(R.id.HapusDataTransaksi)

        lihatDataTransaksi.setOnClickListener {
            startActivity(Intent(this, LihatDataTransaksi::class.java))
        }
        hapusDataTransaksi.setOnClickListener {
            startActivity(Intent(this, HapusDataTransaksi::class.java))
        }
        updateDataTransaksi.setOnClickListener {
            startActivity(Intent(this, UpdateDataTransaksi::class.java))
        }
        tambahDataTransaksi.setOnClickListener {
            startActivity(Intent(this, TambahDataTransaksi::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}