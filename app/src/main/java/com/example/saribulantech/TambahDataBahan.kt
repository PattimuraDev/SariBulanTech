package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TambahDataBahan : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data_bahan)

        val inputTambahNamaBahan = findViewById<EditText>(R.id.inputTambahNamaBahan)
        val inputTambahJumlahBahan = findViewById<EditText>(R.id.inputTambahJumlahBahan)
        val btnTambahDataBahan = findViewById<Button>(R.id.buttonTambahDataBahan)

        btnTambahDataBahan.setOnClickListener {
            if(inputTambahNamaBahan.text.toString().isNotEmpty() && inputTambahJumlahBahan.text.toString().isNotEmpty()){
                val bahan = Bahan(inputTambahNamaBahan.text.toString(), inputTambahJumlahBahan.text.toString())
                database = FirebaseDatabase.getInstance().getReference("BAHAN")
                database.child(inputTambahNamaBahan.text.toString()).setValue(bahan).addOnSuccessListener {
                    inputTambahNamaBahan.text.clear()
                    inputTambahJumlahBahan.text.clear()
                    Toast.makeText(this, "Data bahan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Gagal disimpan", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Nama dan jumlah bahan harus ada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}