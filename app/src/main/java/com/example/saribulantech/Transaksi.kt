package com.example.saribulantech

data class Transaksi(
    var year: String? = "",
    var bulan: String? = "",
    var namaPemesan: String? = "",
    var tanggalPemesanan: String? = "",
    var alamat: String? = "",
    var nominalTransaksi: String? = "",
    var notes: String? = "",
    val statusTransaksi: String? = ""
)
