package org.d3if3101.assesment1.model

data class ToDoList(
    val id: Long,
    val judul: String,
    val isi: String,
    val prioritas: Int,
    val status: String,
    val tanggal: String
)
