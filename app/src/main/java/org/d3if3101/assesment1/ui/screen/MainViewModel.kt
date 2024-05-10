package org.d3if3101.assesment1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3101.assesment1.database.ToDoDao
import org.d3if3101.assesment1.model.ToDoList

class MainViewModel(dao: ToDoDao) : ViewModel()
{
    val data: StateFlow<List<ToDoList>> = dao.getToDoList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}