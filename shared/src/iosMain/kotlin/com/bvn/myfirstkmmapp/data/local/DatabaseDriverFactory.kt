package com.bvn.myfirstkmmapp.data.local

import com.bvn.myfirstkmmapp.database.Notedatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Notedatabase.Schema, "note.db")
    }
}