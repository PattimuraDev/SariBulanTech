package com.example.saribulantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class LihatDataInventory : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var inventoryRecyclerView: RecyclerView
    private lateinit var inventoryArrayList: ArrayList<Inventory>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_inventory)

        // actionbar
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = "Lihat Data Inventory"
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        inventoryRecyclerView = findViewById(R.id.inventory_recyclerview)
        inventoryRecyclerView.layoutManager = LinearLayoutManager(this)
        inventoryRecyclerView.setHasFixedSize(true)

        inventoryArrayList = arrayListOf<Inventory>()
        getInventoryData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getInventoryData() {
        dbref = FirebaseDatabase.getInstance().getReference("INVENTORY")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (inventorySnapshot in snapshot.children) {
                        val inventory = inventorySnapshot.getValue(Inventory::class.java)
                        inventoryArrayList.add(inventory!!)
                    }

                    inventoryRecyclerView.adapter = InventoryAdapter(inventoryArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print("Ada error di data inventory")
            }

        })
    }
}