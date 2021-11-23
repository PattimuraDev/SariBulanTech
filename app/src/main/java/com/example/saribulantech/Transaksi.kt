package com.example.saribulantech

import java.util.*

data class Transaksi(var year: String?,
                     var bulan: String?,
                     var namaPemesan: String?,
                     var tanggalPemesanan: String?,
                     var alamat: String?,
                     var nominalTransaksi: String?,
                     var notes: String?,
                     val statusTransaksi: String?,
                     )
