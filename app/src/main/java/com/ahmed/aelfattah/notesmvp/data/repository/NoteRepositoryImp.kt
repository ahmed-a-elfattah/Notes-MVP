package com.ahmed.aelfattah.notesmvp.data.repository

import com.ahmed.aelfattah.notesmvp.data.domain.Note
import com.ahmed.aelfattah.notesmvp.data.sources.local.NoteDao

class NoteRepositoryImp constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(noteModel: Note) = noteDao.insertNote(noteModel)
}