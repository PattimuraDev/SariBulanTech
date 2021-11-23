package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder
import java.util.*

class LihatDataTransaksi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_transaksi)

        val database = FirebaseDatabase.getInstance().reference
        val calendar = Calendar.getInstance()
        val nameOfMonth = arrayOf("Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember")

        val getDataTransaksi = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val result = StringBuilder()
                var year = calendar.get(Calendar.YEAR).toString().toInt()
                val snapshotRef = snapshot.child("TRANSAKSI")
                for (i in snapshotRef.children) {
                    while(year >= 2000){
                        if(snapshotRef.hasChild(year.toString())){
                            val snapshotRefYear = snapshotRef.child(year.toString())
                            for(j in 11 downTo 0){
                                if(snapshotRefYear.hasChild(nameOfMonth[j])){
                                    val bulanRef = snapshotRefYear.child(nameOfMonth[j])
                                    val bulan = bulanRef.child("Bulan").value
                                    val namaPemesan = bulanRef.child("Nama pemesan").value
                                    val tanggalPemesanan = bulanRef.child("Tanggal pesanan").value
                                    val alamat = bulanRef.child("Alamat pengiriman").value
                                    val nominalTransaksi = bulanRef.child("Nominal transaksi").value
                                    val notes = bulanRef.child("Notes").value
                                    val statusTransaksi = bulanRef.child("Status transaksi").value
                                    result.append("Tahun : $year\n" +
                                            "Bulan : $bulan\n" +
                                            "Nama pemesan : $namaPemesan\n" +
                                            "Tanggal pemesanan : $tanggalPemesanan\n" +
                                            "Alamat : $alamat\n" +
                                            "Nominal transaksi : $nominalTransaksi\n" +
                                            "Notes : $notes\n" +
                                            "Status transaksi : $statusTransaksi\n\n")
                                }
                            }
                        }
                        year--
                    }

                }
                val tvResult = findViewById<TextView>(R.id.tvResultDataTransaksi)
                tvResult.text = result
            }
        }
        database.addValueEventListener(getDataTransaksi)
        database.addListenerForSingleValueEvent(getDataTransaksi)
    }
}