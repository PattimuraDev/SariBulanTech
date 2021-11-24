package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TambahDataTransaksi : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data_transaksi)

        // actionbar
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = "Tambah Data Transaksi"
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        val inputTambahNamaPemesan = findViewById<EditText>(R.id.inputTambahNamaPemesan)
        val inputTambahTanggalPemesanan = findViewById<EditText>(R.id.inputTambahTanggalPemesanan)
        val inputTambahBulanPemesanan = findViewById<EditText>(R.id.inputTambahBulanPemesanan)
        val inputTambahTahunPemesanan = findViewById<EditText>(R.id.inputTambahTahunPemesanan)
        val inputTambahAlamat = findViewById<EditText>(R.id.inputTambahAlamat)
        val inputTambahCatatan = findViewById<EditText>(R.id.inputTambahCatatan)
        val inputTambahNominal = findViewById<EditText>(R.id.inputTambahNominalTransaksi)
        val inputTambahStatusTransaksi = findViewById<EditText>(R.id.inputTambahStatusTransaksi)
        val btnTambahDataTransaksi = findViewById<Button>(R.id.buttonTambahDataTransaksi)

        btnTambahDataTransaksi.setOnClickListener {
            if (inputTambahNamaPemesan.text.toString().isNotEmpty() &&
                inputTambahTanggalPemesanan.text.toString().isNotEmpty() &&
                inputTambahBulanPemesanan.text.toString().isNotEmpty() &&
                inputTambahTahunPemesanan.text.toString().isNotEmpty() &&
                inputTambahAlamat.text.toString().isNotEmpty() &&
                inputTambahCatatan.text.toString().isNotEmpty() &&
                inputTambahNominal.text.toString().isNotEmpty() &&
                inputTambahStatusTransaksi.text.toString().isNotEmpty()
            ) {
                val transaksi = mapOf<String, String>(
                    "Nama pemesan" to inputTambahNamaPemesan.text.toString(),
                    "Tanggal pesanan" to (inputTambahTanggalPemesanan.text.toString() + " " +
                            inputTambahBulanPemesanan.text.toString() + " " +
                            inputTambahTahunPemesanan.text.toString()),
                    "Bulan" to inputTambahBulanPemesanan.text.toString(),
                    "Tahun" to inputTambahTahunPemesanan.text.toString(),
                    "Alamat pengiriman" to inputTambahAlamat.text.toString(),
                    "Notes" to inputTambahCatatan.text.toString(),
                    "Nominal transaksi" to inputTambahNominal.text.toString(),
                    "Status transaksi" to inputTambahStatusTransaksi.text.toString()
                )
                database = FirebaseDatabase.getInstance().getReference("TRANSAKSI")
                val tahunRef = database.child(inputTambahTahunPemesanan.text.toString())

                tahunRef.child(inputTambahBulanPemesanan.text.toString()).push().setValue(transaksi)
                    .addOnSuccessListener {
                        inputTambahNamaPemesan.text.clear()
                        inputTambahTanggalPemesanan.text.clear()
                        inputTambahBulanPemesanan.text.clear()
                        inputTambahTahunPemesanan.text.clear()
                        inputTambahAlamat.text.clear()
                        inputTambahCatatan.text.clear()
                        inputTambahNominal.text.clear()
                        inputTambahStatusTransaksi.text.clear()
                        Toast.makeText(
                            this,
                            "Data transaksi berhasil ditambahkan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}