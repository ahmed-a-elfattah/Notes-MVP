package com.ahmed.aelfattah.notesmvp.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ahmed.aelfattah.notesmvp.data.domain.Note

class NotesDiffCallback constructor(
    private val oldNotes: ArrayList<Note>,
    private val newNotes: ArrayList<Note>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldNotes.size

    override fun getNewListSize(): Int = newNotes.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotes[oldItemPosition] == newNotes[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotes[oldItemPosition] == newNotes[newItemPosition]
    }
}