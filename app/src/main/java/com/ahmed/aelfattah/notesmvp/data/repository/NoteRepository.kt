package com.ahmed.aelfattah.notesmvp.data.repository

import com.ahmed.aelfattah.notesmvp.data.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(noteModel: Note): Long

    suspend fun getAllNotes(): Flow<List<Note>>
}