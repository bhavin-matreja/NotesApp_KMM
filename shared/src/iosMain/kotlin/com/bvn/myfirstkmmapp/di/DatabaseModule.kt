package com.bvn.myfirstkmmapp.di

import com.bvn.myfirstkmmapp.data.local.DatabaseDriverFactory
import com.bvn.myfirstkmmapp.data.note.SqlDelightNoteDataSource
import com.bvn.myfirstkmmapp.database.Notedatabase
import com.bvn.myfirstkmmapp.domain.note.NoteDataSource

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy { SqlDelightNoteDataSource(Notedatabase(factory.createDriver())) }
}