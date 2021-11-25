package com.ahmed.aelfattah.notesmvp.ui.main

import android.util.Log
import com.ahmed.aelfattah.notesmvp.data.domain.Note
import com.ahmed.aelfattah.notesmvp.data.repository.NoteRepositoryImp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainPresenter constructor(
    private val repositoryImp: NoteRepositoryImp,
    private val view: MainContract.View
) : MainContract.Presenter {

    override suspend fun loadAllNotes(): Unit = coroutineScope {
        try {
            view.showProgressBar()
            delay(2000)
            launch(Dispatchers.IO) {
                repositoryImp.getAllNotes().collect {
                    Log.e("TAG", "loadAllNotes: "+it.size )
                    withContext(Dispatchers.Main) {
                        if (it.isEmpty())
                            view.emptyState()
                        else
                            view.contentState(it as ArrayList<Note>)
                    }
                }
            }
            view.hideProgressBar()
        } catch (error: Throwable) {
            view.errorState(error.message!!)
        }
    }

    override fun onAddNoteBtn() {
        view.openAddNoteBottomSheet()
    }
}