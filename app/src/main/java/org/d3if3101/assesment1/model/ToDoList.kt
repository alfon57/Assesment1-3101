package org.d3if3101.assesment1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDoList")
data class ToDoList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val isi: String,
    val prioritas: Int,
    val status: String,
    val tanggal: String
)
