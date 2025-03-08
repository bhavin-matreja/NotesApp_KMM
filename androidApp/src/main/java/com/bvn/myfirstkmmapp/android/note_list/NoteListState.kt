package com.bvn.myfirstkmmapp.android.note_list

import com.bvn.myfirstkmmapp.domain.note.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)