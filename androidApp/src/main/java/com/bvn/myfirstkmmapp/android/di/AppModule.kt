package com.bvn.myfirstkmmapp.android.di

import android.app.Application
import com.bvn.myfirstkmmapp.data.local.DatabaseDriverFactory
import com.bvn.myfirstkmmapp.data.note.SqlDelightNoteDataSource
import com.bvn.myfirstkmmapp.database.Notedatabase
import com.bvn.myfirstkmmapp.domain.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightNoteDataSource(Notedatabase(driver))
    }
}