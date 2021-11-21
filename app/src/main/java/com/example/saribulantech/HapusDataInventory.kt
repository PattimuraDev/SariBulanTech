package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HapusDataInventory : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hapus_data_inventory)
        val btnDeletDataInventory = findViewById<Button>(R.id.buttonDeleteDatainventory)
        val inputDeleteNamaInventory = findViewById<EditText>(R.id.inputDeleteNamaInventory)
        btnDeletDataInventory.setOnClickListener {
            if(inputDeleteNamaInventory.text.toString().isNotEmpty()){
                deleteinventory(inputDeleteNamaInventory.text.toString())
            }else{
                Toast.makeText(this, "Nama inventory harus ada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteinventory(namaInventory: String) {
        database = FirebaseDatabase.getInstance().getReference("INVENTORY")
        database.get().addOnSuccessListener {
            if(it.exists()){
                if(it.hasChild(namaInventory)){
                    database.child(namaInventory).removeValue().addOnSuccessListener {
                        val inputDeleteNamaInventory = findViewById<EditText>(R.id.inputDeleteNamaInventory)
                        inputDeleteNamaInventory.text.clear()
                        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Inventory $namaInventory tidak ada", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}