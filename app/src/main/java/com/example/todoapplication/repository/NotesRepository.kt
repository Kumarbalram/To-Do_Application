package com.example.todoapplication.repository

import androidx.lifecycle.LiveData
import com.example.todoapplication.dao.NotesDao
import com.example.todoapplication.model.Notes

class NotesRepository(private val dao: NotesDao) {

    fun getAllData(): LiveData<List<Notes>> {
        return dao.getNotes()
    }


    fun insertNote(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNote(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNote(notes: Notes) {
        dao.updateNotes(notes)
    }

}