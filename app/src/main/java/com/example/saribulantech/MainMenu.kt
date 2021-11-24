package com.example.saribulantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnTransaksi = findViewById<LinearLayout>(R.id.transaksi)
        val btnInventory = findViewById<LinearLayout>(R.id.inventory)
        val btnBahan = findViewById<LinearLayout>(R.id.bahan)

        btnTransaksi.setOnClickListener {
            val intent = Intent(this, MenuTransaksi::class.java)
            startActivity(intent)
        }
        btnInventory.setOnClickListener {
            val intent = Intent(this, MenuInventory::class.java)
            startActivity(intent)
        }
        btnBahan.setOnClickListener {
            startActivity(Intent(this, MenuBahan::class.java))
        }
    }

//    private fun createBarChart() {
//        database = FirebaseDatabase.getInstance().getReference("TRANSAKSI")
//        var pJanuari = 0F
//        var pFebruari = 0F
//        var pMaret = 0F
//        var pApril = 0F
//        var pMei = 0F
//        var pJuni = 0F
//        var pJuli = 0F
//        var pAgustus = 0F
//        var pSeptember = 0F
//        var pOktober = 0F
//        var pNovember = 0F
//        var pDesember = 0F
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val year = calendar.get(Calendar.YEAR).toString().toInt()
//                if (snapshot.exists()) {
//                    if (snapshot.hasChild(year.toString())) {
//                        val snapshotRefYear = snapshot.child(year.toString())
//                        for (j in 11 downTo 0) {
//                            if (snapshotRefYear.hasChild(nameOfMonth[j])) {
//                                val snapshotRefMonth = snapshotRefYear.child(nameOfMonth[j])
//                                for (k in snapshotRefMonth.children) {
//                                    if (k.exists()) {
//                                        val nominalTransaksi =
//                                            k.child("Nominal transaksi").value.toString().toFloat()
//                                        if (snapshotRefMonth.key!! == "Januari") {
//                                            pJanuari += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Februari") {
//                                            pFebruari += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Maret") {
//                                            pMaret += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "April") {
//                                            pApril += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Mei") {
//                                            pMei += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Juni") {
//                                            pJuni += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Juli") {
//                                            pJuli += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Agustus") {
//                                            pAgustus += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "September") {
//                                            pSeptember += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Oktober") {
//                                            pOktober += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "November") {
//                                            pNovember += nominalTransaksi
//                                        } else if (snapshotRefMonth.key!! == "Desember") {
//                                            pDesember += nominalTransaksi
//                                        }
//                                    }
//                                }
//
//                            }
//                        }
//                    }
//
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//        barList = ArrayList()
//        barList.add(BarEntry(1F, pJanuari))
//        barList.add(BarEntry(2f, pFebruari))
//        barList.add(BarEntry(3f, pMaret))
//        barList.add(BarEntry(4f, pApril))
//        barList.add(BarEntry(5f, pMei))
//        barList.add(BarEntry(6f, pJuni))
//        barList.add(BarEntry(7f, pJuli))
//        barList.add(BarEntry(8f, pAgustus))
//        barList.add(BarEntry(9f, pSeptember))
//        barList.add(BarEntry(10f, pOktober))
//        barList.add(BarEntry(11f, pNovember))
//        barList.add(BarEntry(12f, pDesember))
//
//        barDataSet = BarDataSet(barList, "Pendapatan")
//        barData = BarData(barDataSet)
//        barChart.data = barData
//        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS, 250)
//        barDataSet.valueTextColor = Color.BLACK
//        barDataSet.valueTextSize = 12f
//        barChart.invalidate()
//    }
}