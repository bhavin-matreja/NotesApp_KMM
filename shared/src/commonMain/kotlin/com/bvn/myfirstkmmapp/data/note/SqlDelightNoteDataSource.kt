package com.bvn.myfirstkmmapp.data.note

import com.bvn.myfirstkmmapp.database.Notedatabase
import com.bvn.myfirstkmmapp.domain.note.Note
import com.bvn.myfirstkmmapp.domain.note.NoteDataSource
import com.bvn.myfirstkmmapp.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(db: Notedatabase) : NoteDataSource {

    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        note.apply {
            queries.insertNote(id, title, content, colorHex, DateTimeUtil.toEpocMillis(created))
        }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}