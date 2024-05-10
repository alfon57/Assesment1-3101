package org.d3if3101.assesment1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3101.assesment1.model.ToDoList

class DetailViewModel : ViewModel (){

    private val toDoList_List = listOf(
        ToDoList(
            1,
            "Fretty",
            "6706223148",
            5,
            "Selesai",
            "22"
        )
    )

    fun getToDoList(id: Long): ToDoList? {
        return toDoList_List.find { it.id == id }
    }
}