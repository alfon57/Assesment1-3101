package org.d3if3101.assesment1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3101.assesment1.database.ToDoDao
import org.d3if3101.assesment1.model.ToDoList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: ToDoDao) : ViewModel (){
    private val formatter = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.US)

    fun insert(judul: String, isi: String, prioritas: String){
        val toDoList = ToDoList(
            tanggal = formatter.format(Date()),
            judul = judul,
            isi = isi,
            prioritas = prioritas,
            status = "Belum Selesai"
        )

        viewModelScope.launch (Dispatchers.IO){
            dao.insert(toDoList)
        }
    }

    suspend fun getToDoList(id: Long): ToDoList?{
        return dao.getToDoListById(id)
    }

    fun update(id: Long, judul: String, isi: String, prioritas: String, status: String){
        val toDoList = ToDoList(
            id = id,
            judul = judul,
            isi = isi,
            prioritas = prioritas,
            status = status,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(toDoList)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}