package org.d3if3101.assesment1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3101.assesment1.model.ToDoList

@Dao
interface ToDoDao {
    @Insert
    suspend fun insert(toDoList: ToDoList)

    @Update
    suspend fun update(toDoList: ToDoList)

    @Query("SELECT * FROM toDoList ORDER BY prioritas DESC, tanggal DESC")
    fun getToDoList(): Flow<List<ToDoList>>

    @Query("SELECT * FROM toDoList WHERE id = :id")
    suspend fun getToDoListById(id: Long): ToDoList?

    @Query("DELETE FROM toDoList WHERE id = :id")
    suspend fun deleteById(id: Long)
}