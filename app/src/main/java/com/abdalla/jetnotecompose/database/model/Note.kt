package com.abdalla.jetnotecompose.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "time") val time: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
