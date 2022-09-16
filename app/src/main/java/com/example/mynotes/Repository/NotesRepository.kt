package com.example.mynotes.Repository

import androidx.lifecycle.LiveData
import com.example.mynotes.Dao.NotesDao
import com.example.mynotes.Model.Notes

class NotesRepository(val dao: NotesDao) {
    fun getAllNotes():LiveData<List<Notes>> = dao.getNotes()


    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }
    fun deleteNotes (id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}