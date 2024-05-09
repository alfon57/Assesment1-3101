package org.d3if3101.assesment1.model

data class ToDoList(
    val id: Long,
    val judul: String,
    val isi: String,
    val status: Boolean,
    val tanggal: String
)
