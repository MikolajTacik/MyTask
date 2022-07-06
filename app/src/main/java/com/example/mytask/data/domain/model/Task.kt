package com.example.mytask.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytask.ui.theme.*

@Entity
data class Task (
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
        ) {
    companion object {
        val taskColors = listOf( RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
