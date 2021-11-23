package com.ahmed.aelfattah.notesmvp.ui.addNote

import com.ahmed.aelfattah.notesmvp.data.repository.NoteRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AddNotePresenter constructor(
    private val repositoryImp: NoteRepositoryImp,
    private val view: AddNoteContract.View
) : AddNoteContract.Presenter {

    override suspend fun onSaveNoteBtn() = coroutineScope {
        if (view.getNote().title.isEmpty()) {
            view.invalidNote("Please, Add note title")
            return@coroutineScope
        } else if (view.getNote().description.isEmpty()) {
            view.invalidNote("Please, Add note description")
            return@coroutineScope
        }
        launch(Dispatchers.IO) {
            repositoryImp.insertNote(view.getNote())
        }
        view.noteAdded()
    }
}