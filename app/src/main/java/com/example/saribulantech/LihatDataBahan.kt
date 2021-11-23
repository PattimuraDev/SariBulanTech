package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class LihatDataBahan : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var bahanRecyclerView: RecyclerView
    private lateinit var bahanArrayList: ArrayList<Bahan>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_bahan)
        // actionbar
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = "Lihat Data Bahan"
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        bahanRecyclerView = findViewById(R.id.bahan_recyclerview)
        bahanRecyclerView.layoutManager = LinearLayoutManager(this)
        bahanRecyclerView.setHasFixedSize(true)

        bahanArrayList = arrayListOf<Bahan>()
        getBahanData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getBahanData() {
        dbref = FirebaseDatabase.getInstance().getReference("BAHAN")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (bahanSnapshot in snapshot.children) {
                        val bahan = bahanSnapshot.getValue(Bahan::class.java)
                        bahanArrayList.add(bahan!!)
                    }

                    bahanRecyclerView.adapter = BahanAdapter(bahanArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print("Ada error di data bahan")
            }

        })
    }
}