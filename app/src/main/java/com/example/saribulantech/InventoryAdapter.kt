package com.example.saribulantech

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
class InventoryAdapter(private val inventoryList : ArrayList<Inventory>) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>(){
     class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                var itemNama : TextView
                var itemJumlah : TextView

                init{
                    itemNama = itemView.findViewById(R.id.tv_nama)
                    itemJumlah = itemView.findViewById(R.id.tv_jumlah)
                }
            }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_bahan, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = inventoryList[position]

        holder.itemNama.text = currentItem.nama
        holder.itemJumlah.text = currentItem.jumlah
    }

    override fun getItemCount(): Int {
        return inventoryList.size
    }


}