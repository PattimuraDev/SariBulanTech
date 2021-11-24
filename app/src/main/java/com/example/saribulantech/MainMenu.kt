package com.example.saribulantech

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class MainMenu : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private val calendar = Calendar.getInstance()
    private val nameOfMonth = arrayOf(
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        supportActionBar?.hide()
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

        createLineChart()
    }

    private fun createLineChart() {
        database = FirebaseDatabase.getInstance().getReference("TRANSAKSI")
        val year = calendar.get(Calendar.YEAR).toString()
        val pendapatanSetahunList = ArrayList<Entry>()
        val naikTurunPendapatanSetahunList = ArrayList<Entry>()

        database.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    if(snapshot.hasChild(year)){
                        var pendapatanSebulan = 0F
                        var indeks = 0F
                        var pendapatanBulanSebelumnya = 0F
                        for(i in 0..11){
                            if(snapshot.child(year).hasChild(nameOfMonth[i])){
                                val monthRef = snapshot.child(year).child(nameOfMonth[i])
                                for(j in monthRef.children){
                                    if(j.exists()){
                                        pendapatanSebulan+=j.child("Nominal transaksi").value.toString().toFloat()
                                    }
                                }
                                naikTurunPendapatanSetahunList.add(Entry(indeks, pendapatanSebulan - pendapatanBulanSebelumnya))
                                pendapatanSetahunList.add(Entry(indeks, pendapatanSebulan))

                                indeks+=1F
                                pendapatanBulanSebelumnya = pendapatanSebulan
                                pendapatanSebulan = 0F
                            }
                        }
                        //
                        val pendapatanSetahunLineDataSet = LineDataSet(pendapatanSetahunList, "Pendapatan")
                        val naikTurunPendapatanSetahunLineDataset = LineDataSet(naikTurunPendapatanSetahunList, "Naik Turun")
                        pendapatanSetahunLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
                        pendapatanSetahunLineDataSet.color = Color.BLUE
                        pendapatanSetahunLineDataSet.circleRadius = 5f
                        pendapatanSetahunLineDataSet.setCircleColor(Color.BLUE)
                        pendapatanSetahunLineDataSet.setDrawFilled(true)

                        naikTurunPendapatanSetahunLineDataset.mode = LineDataSet.Mode.CUBIC_BEZIER
                        naikTurunPendapatanSetahunLineDataset.color = Color.RED
                        naikTurunPendapatanSetahunLineDataset.circleRadius = 5f
                        naikTurunPendapatanSetahunLineDataset.setCircleColor(Color.RED)
                        naikTurunPendapatanSetahunLineDataset.setDrawFilled(true)

                        val lineChart = findViewById<LineChart>(R.id.lineChart)
                        val legend = lineChart.legend
                        legend.isEnabled = true
                        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
                        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                        legend.orientation = Legend.LegendOrientation.HORIZONTAL
                        legend.setDrawInside(false)

                        lineChart.description.isEnabled = false
                        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
                        val linedata = LineData()
                        linedata.addDataSet(pendapatanSetahunLineDataSet)
                        linedata.addDataSet(naikTurunPendapatanSetahunLineDataset)
                        lineChart.data = linedata
                        lineChart.animateXY(100, 500)

                        var bulan = ArrayList<String>()
                        bulan.add("1")
                        bulan.add("2")
                        bulan.add("3")
                        bulan.add("4")
                        bulan.add("5")
                        bulan.add("6")
                        bulan.add("7")
                        bulan.add("8")
                        bulan.add("9")
                        bulan.add("10")
                        bulan.add("11")
                        bulan.add("12")
                        val bulanFormatted = AxisFormatter(bulan.toArray(arrayOfNulls<String>(bulan.size)))
                        lineChart.xAxis?.valueFormatter = bulanFormatted
                        lineChart.xAxis.textSize = 10f
                        lineChart.invalidate()
                    }
                }
            }
        })
    }
}