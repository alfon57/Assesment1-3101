package org.d3if3101.assesment1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3101.assesment1.model.ToDoList

@Database(entities = [ToDoList::class], version = 1, exportSchema = false)
abstract class ToDoDB : RoomDatabase() {
    abstract val dao: ToDoDao

    companion object{
        @Volatile
        private var INSTANCE: ToDoDB? = null

        fun getInstance(context: Context): ToDoDB{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDB::class.java,
                        "toDo.db"
                    ).build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}