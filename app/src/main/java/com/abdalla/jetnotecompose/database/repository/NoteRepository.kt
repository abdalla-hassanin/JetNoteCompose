package com.abdalla.jetnotecompose.database.repository

import com.abdalla.jetnotecompose.database.dao.NoteDao
import com.abdalla.jetnotecompose.database.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    val getAllNotes:Flow<List<Note>> =noteDao.getAllNotes()

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
}