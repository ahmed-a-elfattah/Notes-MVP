package com.ahmed.aelfattah.notesmvp.di

import android.app.Application
import androidx.room.Room
import com.ahmed.aelfattah.notesmvp.data.repository.NoteRepositoryImp
import com.ahmed.aelfattah.notesmvp.data.sources.local.NoteDao
import com.ahmed.aelfattah.notesmvp.data.sources.local.NoteDatabase
import com.ahmed.aelfattah.notesmvp.ui.addNote.AddNoteContract
import com.ahmed.aelfattah.notesmvp.ui.addNote.AddNotePresenter
import org.koin.dsl.module


val noteDatabaseModule = module {

    fun providesDatabase(application: Application): NoteDatabase =
        Room.databaseBuilder(application, NoteDatabase::class.java, "my_notes")
            .fallbackToDestructiveMigration()
            .build()

    fun providesDao(db: NoteDatabase): NoteDao = db.getNoteDao()

    single { providesDatabase(application = get()) }

    single { providesDao(db = get()) }
}

val repositoryImpModule = module {
    fun provideAppRepository(noteDao: NoteDao): NoteRepositoryImp {
        return NoteRepositoryImp(noteDao)
    }

    single {
        provideAppRepository(noteDao = get())
    }
}

val pagesModules = module {
    factory { (view: AddNoteContract.View) -> AddNotePresenter(repositoryImp = get(), view = view) }
}

val appModules = listOf(noteDatabaseModule, repositoryImpModule, pagesModules)