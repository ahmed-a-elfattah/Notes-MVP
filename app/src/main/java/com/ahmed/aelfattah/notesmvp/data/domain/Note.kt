package com.ahmed.aelfattah.notesmvp.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val description: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
