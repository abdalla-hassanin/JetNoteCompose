package com.abdalla.jetnotecompose.database.viewModel

import androidx.lifecycle.*
import com.abdalla.jetnotecompose.database.model.Note
import com.abdalla.jetnotecompose.database.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    val getAllNotes: LiveData<List<Note>> = repository.getAllNotes.asLiveData()


    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
