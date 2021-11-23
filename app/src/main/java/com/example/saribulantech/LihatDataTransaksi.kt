package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*

class LihatDataTransaksi : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var transaksiRecyclerView: RecyclerView
    private lateinit var transaksiArrayList: ArrayList<Transaksi>
    private val calendar = Calendar.getInstance()
    private val nameOfMonth = arrayOf(
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_transaksi)

        // actionbar
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = "Lihat Data Transaksi"
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        transaksiRecyclerView = findViewById(R.id.transaksi_recyclerview)
        transaksiRecyclerView.layoutManager = LinearLayoutManager(this)
        transaksiRecyclerView.setHasFixedSize(true)

        transaksiArrayList = arrayListOf<Transaksi>()
        getTransaksiData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getTransaksiData() {
        dbref = FirebaseDatabase.getInstance().getReference("TRANSAKSI")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var year = calendar.get(Calendar.YEAR).toString().toInt()
                if (snapshot.exists()) {
                    for (transaksiSnapshot in snapshot.children) {
                        while (year > 2000) {
                            if (snapshot.hasChild(year.toString())) {
                                val snapshotRefYear = snapshot.child(year.toString())
                                for (j in 11 downTo 0) {
                                    if (snapshotRefYear.hasChild(nameOfMonth[j])) {
                                        val bulanRef = snapshotRefYear.child(nameOfMonth[j])
                                        for (k in bulanRef.children) {
                                            if (k.exists()) {
                                                val bulan = k.child("Bulan").value.toString()
                                                val namaPemesan =
                                                    k.child("Nama pemesan").value.toString()
                                                val tanggalPemesanan =
                                                    k.child("Tanggal pesanan").value.toString()
                                                val alamat =
                                                    k.child("Alamat pengiriman").value.toString()
                                                val nominalTransaksi =
                                                    k.child("Nominal transaksi").value.toString()
                                                val notes = k.child("Notes").value.toString()
                                                val statusTransaksi =
                                                    k.child("Status transaksi").value.toString()
                                                val transaksi = Transaksi(
                                                    year.toString(),
                                                    bulan,
                                                    namaPemesan,
                                                    tanggalPemesanan,
                                                    alamat,
                                                    nominalTransaksi,
                                                    notes,
                                                    statusTransaksi
                                                )
                                                transaksiArrayList.add(transaksi)
                                            }
                                        }
                                    }
                                }
                            }
                            year--
                        }
                    }
                    transaksiRecyclerView.adapter = TransaksiAdapter(transaksiArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print("Ada error di data inventory")
            }
        })
    }
}