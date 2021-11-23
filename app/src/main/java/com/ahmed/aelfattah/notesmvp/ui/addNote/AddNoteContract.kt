package com.ahmed.aelfattah.notesmvp.ui.addNote

import com.ahmed.aelfattah.notesmvp.data.domain.Note

interface AddNoteContract {
    interface View {
        fun getNote():Note
        fun invalidNote(message: String)
        fun noteAdded()
    }

    interface Presenter{
        suspend fun onSaveNoteBtn()
    }
}