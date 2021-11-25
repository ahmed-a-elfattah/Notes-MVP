package com.ahmed.aelfattah.notesmvp.data.sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmed.aelfattah.notesmvp.data.domain.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: Note): Long

    @Query("SELECT * FROM Note ORDER BY title ASC")
    fun getAllNotes(): Flow<List<Note>>
}