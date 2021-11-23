package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HapusDataTransaksi : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hapus_data_transaksi)

        val inputBulanHapusLaporan = findViewById<EditText>(R.id.inputBulanHapusLaporan)
        val inputTahunHapusLaporan = findViewById<EditText>(R.id.inputTahunHapusLaporan)
        val btnHapusDataTransaksi = findViewById<Button>(R.id.buttonHapusDataTransaksi)

        btnHapusDataTransaksi.setOnClickListener {
            if(inputTahunHapusLaporan.text.toString().isNotEmpty() && inputBulanHapusLaporan.text.toString().isNotEmpty()){
                hapusDataTransaksi(inputTahunHapusLaporan.text.toString(), inputBulanHapusLaporan.text.toString())
            }else{
                Toast.makeText(this, "Bulan dan tahun laporan harus ada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hapusDataTransaksi(tahun : String, bulan : String) {
        database = FirebaseDatabase.getInstance().getReference("TRANSAKSI")
        database.get().addOnSuccessListener {
            if(it.exists()){
                if(it.hasChild(tahun)){
                    val databaseTahunRef = database.child(tahun)
                    databaseTahunRef.child(bulan).removeValue().addOnSuccessListener {
                        findViewById<EditText>(R.id.inputBulanHapusLaporan).text.clear()
                        findViewById<EditText>(R.id.inputTahunHapusLaporan).text.clear()
                        Toast.makeText(this, "Data transaksi berhasil dihapus", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}