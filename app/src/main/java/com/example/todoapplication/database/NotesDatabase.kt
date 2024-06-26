package com.example.todoapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapplication.dao.NotesDao
import com.example.todoapplication.model.Notes

@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object
    {
        @Volatile
         var Instances: NotesDatabase? =null

        fun getDatabaseInstance(context: Context):NotesDatabase{
            val tempInstance= Instances

            if (tempInstance!=null)
                return tempInstance
            synchronized(this)
            {
                val roomDatabaseInstance= Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                Instances=roomDatabaseInstance
                return roomDatabaseInstance
            }
        }

    }

}