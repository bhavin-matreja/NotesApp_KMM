package com.bvn.myfirstkmmapp.data.local

import android.content.Context
import com.bvn.myfirstkmmapp.database.Notedatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Notedatabase.Schema, context, "note.db")
    }
}