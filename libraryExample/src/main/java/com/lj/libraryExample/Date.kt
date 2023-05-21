package com.lj.libraryExample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date")
data class Date(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String
)