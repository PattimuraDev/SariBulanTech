package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HapusDataBahan : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hapus_data_bahan)

        val btnHapusDataBahan = findViewById<Button>(R.id.buttonHapusDataBahan)
        val inputHapusNamaBahan = findViewById<EditText>(R.id.inputHapusNamaBahan)

        btnHapusDataBahan.setOnClickListener {
            if(inputHapusNamaBahan.text.toString().isNotEmpty()){
                hapusBahan(inputHapusNamaBahan.text.toString())
            }else{
                Toast.makeText(this, "Nama bahan harus ada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hapusBahan(namaBahan: String) {
        database = FirebaseDatabase.getInstance().getReference("BAHAN")
        database.get().addOnSuccessListener {
            if(it.exists()){
                if(it.hasChild(namaBahan)){
                    database.child(namaBahan).removeValue().addOnSuccessListener {
                        findViewById<EditText>(R.id.inputHapusNamaBahan).text.clear()
                        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Bahan $namaBahan tidak ada", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Eror", Toast.LENGTH_SHORT).show()
            }
        }
    }
}