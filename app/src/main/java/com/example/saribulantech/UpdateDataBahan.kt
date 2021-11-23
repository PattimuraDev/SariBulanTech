package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateDataBahan : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data_bahan)

        val btnUpdateDataBahan = findViewById<Button>(R.id.buttonUpdateDataBahan)
        val inputUpdateNamaBahan = findViewById<EditText>(R.id.inputUpdateNamaBahan)
        val inputUpdateJumlahBahan = findViewById<EditText>(R.id.inputUpdateJumlahBahan)

        btnUpdateDataBahan.setOnClickListener {
            if (inputUpdateNamaBahan.text.toString()
                    .isNotEmpty() && inputUpdateJumlahBahan.text.toString().isNotEmpty()
            ) {
                if (inputUpdateJumlahBahan.text.toString().toInt() < 0) {
                    Toast.makeText(this, "Jumlah tidak boleh negatif", Toast.LENGTH_SHORT).show()
                } else {
                    updateDataBahan(
                        inputUpdateNamaBahan.text.toString(),
                        inputUpdateJumlahBahan.text.toString()
                    )
                }
            } else {
                Toast.makeText(this, "Nama bahan dan jumlah harus ada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateDataBahan(nama: String, jumlah: String) {
        database = FirebaseDatabase.getInstance().getReference("BAHAN")
        val bahanUpdate = mapOf<String, String>(
            "nama" to nama,
            "jumlah" to jumlah
        )

        database.get().addOnSuccessListener {
            if (it.exists()) {
                if (it.hasChild(nama)) {
                    database.child(nama).updateChildren(bahanUpdate).addOnSuccessListener {
                        findViewById<EditText>(R.id.inputUpdateNamaBahan).text.clear()
                        findViewById<EditText>(R.id.inputUpdateJumlahBahan).text.clear()
                        Toast.makeText(this, "Bahan $nama berhasil diupdate", Toast.LENGTH_SHORT)
                            .show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Update gagal", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Bahan $nama tidak ada", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}