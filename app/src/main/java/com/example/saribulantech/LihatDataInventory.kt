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
        var database = FirebaseDatabase.getInstance().reference

        var getData = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var result = StringBuilder()
                for (i in snapshot.child("INVENTORY").children) {
                    var nama = i.child("nama").getValue()
                    var jumlah = i.child("jumlah").getValue()
                    result.append("Nama inventory : $nama\n" + "Jumlah : $jumlah\n\n")
                }
                var tvResult = findViewById<TextView>(R.id.tvResult)
                tvResult.setText(result)
            }
        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)
    }
}