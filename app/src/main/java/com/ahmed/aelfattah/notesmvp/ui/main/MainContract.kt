package com.ahmed.aelfattah.notesmvp.ui.main

import com.ahmed.aelfattah.notesmvp.data.domain.Note

interface MainContract {

    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun emptyState()
        fun contentState(noteList: ArrayList<Note>)
        fun errorState(error: String)
        fun openAddNoteBottomSheet()
    }

    interface Presenter {
        suspend fun loadAllNotes()
        fun onAddNoteBtn()
    }
}