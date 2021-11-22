package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder

class LihatDataTransaksi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_transaksi)

        val database = FirebaseDatabase.getInstance().reference

        val getDataTransaksi = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val result = StringBuilder()
                for (i in snapshot.child("TRANSAKSI").children) {
                    val namaPemesan = i.child("Nama pemesan").value
                    val tanggalPemesanan = i.child("Tanggal pesanan").value
                    val alamat = i.child("Alamat pengiriman").value
                    val nominalTransaksi = i.child("Nominal transaksi").value
                    val notes = i.child("Notes").value
                    result.append("Nama pemesan : $namaPemesan\n" +
                            "Tanggal pemesanan : $tanggalPemesanan\n" +
                            "Alamat : $alamat\n" +
                            "Nominal transaksi : $nominalTransaksi\n" +
                            "Notes : $notes\n\n")
                }
                val tvResult = findViewById<TextView>(R.id.tvResultDataTransaksi)
                tvResult.text = result
            }
        }
        database.addValueEventListener(getDataTransaksi)
        database.addListenerForSingleValueEvent(getDataTransaksi)
    }
}