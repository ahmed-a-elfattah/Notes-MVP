package com.ahmed.aelfattah.notesmvp.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmed.aelfattah.notesmvp.data.domain.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}