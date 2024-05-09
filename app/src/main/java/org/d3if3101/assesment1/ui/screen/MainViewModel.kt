package org.d3if3101.assesment1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3101.assesment1.model.ToDoList

class MainViewModel : ViewModel()
{

    val data = getDataDummy()

    private fun  getDataDummy(): List<ToDoList>
    {
        val data = mutableListOf<ToDoList>()

        data.add(
            ToDoList(1.toLong(),
            "Nyuci Baju",
            "Bawa baju kotor ke ibu laundry",
            false,
            "22"
            )
        )

        return data
    }
}