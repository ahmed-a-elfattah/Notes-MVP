package com.ahmed.aelfattah.notesmvp.data.repository

import com.ahmed.aelfattah.notesmvp.data.domain.Note

interface NoteRepository {

    suspend fun insertNote(noteModel: Note): Long
}