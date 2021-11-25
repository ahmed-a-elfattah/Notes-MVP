package com.ahmed.aelfattah.notesmvp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.aelfattah.notesmvp.data.domain.Note
import com.ahmed.aelfattah.notesmvp.databinding.NoteItemBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var noteList: ArrayList<Note> = ArrayList()

    inner class NoteViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.note = noteList[position]
    }

    override fun getItemCount(): Int = noteList.size

    fun setNoteList(noteList: ArrayList<Note>) {
        DiffUtil.calculateDiff(NotesDiffCallback(this.noteList, noteList)).dispatchUpdatesTo(this)
        this.noteList = noteList
    }
}