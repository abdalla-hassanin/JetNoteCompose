package com.abdalla.jetnotecompose.database.viewModel

import android.app.Application
import com.abdalla.jetnotecompose.database.AppDatabase
import com.abdalla.jetnotecompose.database.repository.NoteRepository

class NotesApplication : Application() {
        // Using by lazy so the database and the repository are only created when they're needed
        // rather than when the application starts
        val database by lazy { AppDatabase.getDatabase(this) }
        val repository by lazy { NoteRepository(database.notes()) }
    }