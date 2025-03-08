package com.bvn.myfirstkmmapp.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bvn.myfirstkmmapp.domain.note.Note
import com.bvn.myfirstkmmapp.domain.note.NoteDataSource
import com.bvn.myfirstkmmapp.domain.note.SearchNotes
import com.bvn.myfirstkmmapp.domain.time.DateTimeUtil
import com.bvn.myfirstkmmapp.presentation.RedOrangeHex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val searchNotes = SearchNotes()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
        // without below it is just a flow not a state flow - add stateIn to convert to state flow
        // WhileSubscribed - means above block will only execute if there are active subscribers
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    /*init {
        viewModelScope.launch {
            noteDataSource.insertNote(
                Note(
                    id = 1,
                    title = "Sample Note 1",
                    content = "This is a sample note",
                    colorHex = RedOrangeHex,
                    created = DateTimeUtil.now()
                )
            )
            noteDataSource.insertNote(
                Note(
                    id = 2,
                    title = "Sample Note 2",
                    content = "This is a sample note",
                    colorHex = RedOrangeHex,
                    created = DateTimeUtil.now()
                )
            )
        }
    }*/
    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }

    fun onSearchTextChanged(text: String) {
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if (!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
            loadNotes() // Since we have not used flow, we need to refresh here
        }
    }
}