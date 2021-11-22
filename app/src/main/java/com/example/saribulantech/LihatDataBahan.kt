package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder

class LihatDataBahan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_bahan)
        val database = FirebaseDatabase.getInstance().reference
        val getDataBahan = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val result = StringBuilder()
                for (i in snapshot.child("BAHAN").children) {
                    val nama = i.child("nama").value
                    val jumlah = i.child("jumlah").value
                    result.append("Nama bahan : $nama\nJumlah : $jumlah\n\n")
                }
                val tvResult = findViewById<TextView>(R.id.tvResultDataBahan)
                tvResult.text = result
            }
        }
        database.addValueEventListener(getDataBahan)
        database.addListenerForSingleValueEvent(getDataBahan)
    }
}