package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.lang.StringBuilder
import java.util.*

class LihatDataTransaksi : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var transaksiRecyclerView : RecyclerView
    private lateinit var transaksiArrayList : ArrayList<Transaksi>
    private val calendar = Calendar.getInstance()
    private val nameOfMonth = arrayOf("Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_transaksi)

        transaksiRecyclerView = findViewById(R.id.transaksi_recyclerview)
        transaksiRecyclerView.layoutManager = LinearLayoutManager(this)
        transaksiRecyclerView.setHasFixedSize(true)

        transaksiArrayList = arrayListOf<Transaksi>()
        getTransaksiData()
    }

    private fun getTransaksiData(){
        dbref =  FirebaseDatabase.getInstance().getReference("TRANSAKSI")

        dbref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var year = calendar.get(Calendar.YEAR).toString().toInt()
                if(snapshot.exists()){
                    for (transaksiSnapshot in snapshot.children){
                        while(year>2000){
                            if(snapshot.hasChild(year.toString())){
                                val snapshotRefYear = snapshot.child(year.toString())
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
                                        val transaksi = Transaksi(year, bulan, namaPemesan, tanggalPemesanan, alamat, nominalTransaksi, notes, statusTransaksi)
                                        transaksiArrayList.add(transaksi)
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