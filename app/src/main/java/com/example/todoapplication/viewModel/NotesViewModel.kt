package com.example.todoapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.database.NotesDatabase
import com.example.todoapplication.model.Notes
import com.example.todoapplication.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(notes)
        }
    }

    fun getNotes(): LiveData<List<Notes>> {
        return repository.getAllData()
    }


    fun deleteNotes(id: Int) {
        repository.deleteNote(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNote(notes)
    }
}