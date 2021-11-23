package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.lang.StringBuilder

class LihatDataBahan : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var bahanRecyclerView : RecyclerView
    private lateinit var bahanArrayList : ArrayList<Bahan>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_bahan)

        bahanRecyclerView = findViewById(R.id.bahan_recyclerview)
        bahanRecyclerView.layoutManager = LinearLayoutManager(this)
        bahanRecyclerView.setHasFixedSize(true)

        bahanArrayList = arrayListOf<Bahan>()
        getBahanData()
    }

    private fun getBahanData(){
        dbref =  FirebaseDatabase.getInstance().getReference("BAHAN")

        dbref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (bahanSnapshot in snapshot.children){
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