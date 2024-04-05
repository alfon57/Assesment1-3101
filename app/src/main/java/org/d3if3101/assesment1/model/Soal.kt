package org.d3if3101.assesment1.model

data class Soal(
    val id: Long,
    val gambar: Int,
    val namaGambar: Int,
    val jawaban1: Int,
    val jawaban2: Int,
    var apakahBenar: Boolean,
    val poinSoal: Int,
    var apakahDijawab: Boolean,
    )
