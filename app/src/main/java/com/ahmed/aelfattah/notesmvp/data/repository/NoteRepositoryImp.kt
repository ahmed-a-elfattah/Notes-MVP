package com.ahmed.aelfattah.notesmvp.data.repository

import com.ahmed.aelfattah.notesmvp.data.domain.Note
import com.ahmed.aelfattah.notesmvp.data.sources.local.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImp constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(noteModel: Note) = noteDao.insertNote(noteModel)

    override suspend fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
}