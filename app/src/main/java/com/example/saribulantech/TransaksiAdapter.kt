package com.example.saribulantech

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
class TransaksiAdapter(private val transaksiList : ArrayList<Transaksi>) : RecyclerView.Adapter<TransaksiAdapter.ViewHolder>(){
     class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                var itemTahun : TextView
                var itemBulan : TextView
                var itemNamaPemesan : TextView
                var itemTanggalPemesanan : TextView
                var itemAlamat : TextView
                var itemNominalTransaksi : TextView
                var itemNote : TextView
                var itemStatusTransaksi : TextView

                init{
                    itemTahun = itemView.findViewById(R.id.tv_tahun)
                    itemBulan = itemView.findViewById(R.id.tv_bulan)
                    itemNamaPemesan = itemView.findViewById(R.id.tv_nama_pemesan)
                    itemTanggalPemesanan = itemView.findViewById(R.id.tv_tanggal_pemesanan)
                    itemAlamat = itemView.findViewById(R.id.tv_alamat)
                    itemNominalTransaksi = itemView.findViewById(R.id.tv_nominal_transaksi)
                    itemNote = itemView.findViewById(R.id.tv_note)
                    itemStatusTransaksi = itemView.findViewById(R.id.tv_status_transaksi)
                }
            }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_transaksi, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = transaksiList[position]

        holder.itemTahun.text = currentItem.year.toString()
        holder.itemBulan.text = currentItem.bulan.toString()
        holder.itemNamaPemesan.text = currentItem.namaPemesan.toString()
        holder.itemTanggalPemesanan.text = currentItem.tanggalPemesanan.toString()
        holder.itemAlamat.text = currentItem.alamat.toString()
        holder.itemNominalTransaksi.text = currentItem.nominalTransaksi.toString()
        holder.itemNote.text = currentItem.notes.toString()
        holder.itemStatusTransaksi.text = currentItem.statusTransaksi.toString()

    }

    override fun getItemCount(): Int {
        return transaksiList.size
    }


}