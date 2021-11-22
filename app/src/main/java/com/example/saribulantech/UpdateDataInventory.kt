package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateDataInventory : AppCompatActivity() {
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data_inventory)

        val btnUpdateDataInventory = findViewById<Button>(R.id.buttonUpdateDatainventory)
        val inputUpdateNamaInventory = findViewById<EditText>(R.id.inputUpdateNamaInventory)
        val inputUpdatejumlahInventory = findViewById<EditText>(R.id.inputUpdateJumlahInventory)

        btnUpdateDataInventory.setOnClickListener {
            if(inputUpdateNamaInventory.text.toString().isNotEmpty() && inputUpdatejumlahInventory.text.toString().isNotEmpty()){

                updateDataInventory(inputUpdateNamaInventory.text.toString(), inputUpdatejumlahInventory.text.toString())

            }else{
                Toast.makeText(this, "Nama dan jumlah inventory harus ada!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateDataInventory(nama: String, jumlah: String) {
        database = FirebaseDatabase.getInstance().getReference("INVENTORY")
        val inventoryUpdate = mapOf<String, String>(
            "nama" to nama,
            "jumlah" to jumlah
        )
        database.get().addOnSuccessListener {
            if(it.exists()){
                if(it.hasChild(nama)){
                    database.child(nama).updateChildren(inventoryUpdate).addOnSuccessListener {
                        val inputUpdateNamaInventory = findViewById<EditText>(R.id.inputUpdateNamaInventory)
                        val inputUpdatejumlahInventory = findViewById<EditText>(R.id.inputUpdateJumlahInventory)
                        inputUpdateNamaInventory.text.clear()
                        inputUpdatejumlahInventory.text.clear()
                        Toast.makeText(this, "Inventory $nama berhasil diupdate", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Update gagal", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Inventory $nama tidak ada", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }
}