package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class UpdateDataTransaksi : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data_transaksi)

        val inputUpdateNamaPemesan = findViewById<EditText>(R.id.inputUpdateNamaPemesan)
        val inputUpdateTanggalPemesanan = findViewById<EditText>(R.id.inputUpdateTanggalPemesanan)
        val inputUpdateBulanPemesanan = findViewById<EditText>(R.id.inputUpdateBulanPemesanan)
        val inputUpdateTahunPemesanan = findViewById<EditText>(R.id.inputUpdateTahunPemesanan)
        val inputUpdateAlamat = findViewById<EditText>(R.id.inputUpdateAlamat)
        val inputUpdateCatatan = findViewById<EditText>(R.id.inputUpdateCatatan)
        val inputUpdateNominal = findViewById<EditText>(R.id.inputUpdateNominalTransaksi)
        val inputUpdateStatusTransaksi = findViewById<EditText>(R.id.inputUpdateStatusTransaksi)
        val btnUpdateDataTransaksi = findViewById<Button>(R.id.buttonUpdateDataTransaksi)

        btnUpdateDataTransaksi.setOnClickListener {
            if (inputUpdateNamaPemesan.text.toString().isNotEmpty() &&
                inputUpdateTanggalPemesanan.text.toString().isNotEmpty() &&
                inputUpdateBulanPemesanan.text.toString().isNotEmpty() &&
                inputUpdateTahunPemesanan.text.toString().isNotEmpty() &&
                inputUpdateAlamat.text.toString().isNotEmpty() &&
                inputUpdateCatatan.text.toString().isNotEmpty() &&
                inputUpdateNominal.text.toString().isNotEmpty() &&
                inputUpdateStatusTransaksi.text.toString().isNotEmpty()
            ) {

                updateDataTransaksi(
                    inputUpdateNamaPemesan.text.toString(),
                    (inputUpdateTanggalPemesanan.text.toString() + " " +
                            inputUpdateBulanPemesanan.text.toString() + " " +
                            inputUpdateTahunPemesanan.text.toString()),
                    inputUpdateBulanPemesanan.text.toString(),
                    inputUpdateTahunPemesanan.text.toString(),
                    inputUpdateAlamat.text.toString(),
                    inputUpdateCatatan.text.toString(),
                    inputUpdateNominal.text.toString(),
                    inputUpdateStatusTransaksi.text.toString()
                )

            } else {
                Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateDataTransaksi(
        namaPemesan: String, tanggal: String, bulan: String, tahun: String,
        alamat: String, catatan: String, nominal: String, statusTransaksi: String
    ) {
        database = FirebaseDatabase.getInstance().getReference("TRANSAKSI")
        val transaksiUpdate = mapOf<String, String>(
            "Nama pemesan" to namaPemesan,
            "Tanggal pesanan" to tanggal,
            "Bulan" to bulan,
            "Tahun" to tahun,
            "Alamat pengiriman" to alamat,
            "Notes" to catatan,
            "Nominal transaksi" to nominal,
            "Status transaksi" to statusTransaksi
        )
        var key = ""
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    if (snapshot.hasChild(tahun)) {
                        if (snapshot.child(tahun).hasChild(bulan)) {
                            for (i in snapshot.child(tahun).child(bulan).children) {
                                if (i.exists() && i.child("Nama pemesan").value.toString() == namaPemesan) {
                                    key = i.key!!
                                }
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        database.get().addOnSuccessListener {
            if (it.exists()) {
                if (it.hasChild(tahun)) {
                    val bulanRef = database.child(tahun).child(bulan)
                    bulanRef.child(key).updateChildren(transaksiUpdate).addOnSuccessListener {
                        findViewById<EditText>(R.id.inputUpdateNamaPemesan).text.clear()
                        findViewById<EditText>(R.id.inputUpdateTanggalPemesanan).text.clear()
                        findViewById<EditText>(R.id.inputUpdateBulanPemesanan).text.clear()
                        findViewById<EditText>(R.id.inputUpdateTahunPemesanan).text.clear()
                        findViewById<EditText>(R.id.inputUpdateAlamat).text.clear()
                        findViewById<EditText>(R.id.inputUpdateCatatan).text.clear()
                        findViewById<EditText>(R.id.inputUpdateNominalTransaksi).text.clear()
                        findViewById<EditText>(R.id.inputUpdateStatusTransaksi).text.clear()
                        Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Data tidak ada", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}