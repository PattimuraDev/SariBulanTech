package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*
import java.lang.StringBuilder

class LihatDataInventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_inventory)
        val database = FirebaseDatabase.getInstance().reference

        val getDataInventory = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val result = StringBuilder()
                for (i in snapshot.child("INVENTORY").children) {
                    val nama = i.child("nama").value
                    val jumlah = i.child("jumlah").value
                    result.append("Nama inventory : $nama\nJumlah : $jumlah\n\n")
                }
                val tvResult = findViewById<TextView>(R.id.tvResultDataInventory)
                tvResult.text = result
            }
        }
        database.addValueEventListener(getDataInventory)
        database.addListenerForSingleValueEvent(getDataInventory)
    }
}