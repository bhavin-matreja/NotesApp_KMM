package com.bvn.myfirstkmmapp.android.note_detail

import androidx.compose.ui.graphics.Color

data class NoteDetailState(
    val noteTitle: String = "",
    val noteContent: String = "",
    val isNoteTitleHintVisible: Boolean = false,
    val isNoteContentHintVisible: Boolean = false,
    val noteColor: Long = 0xFFFFFF
)
