package com.abdalla.jetnotecompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abdalla.jetnotecompose.database.dao.NoteDao
import com.abdalla.jetnotecompose.database.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notes(): NoteDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "JetNoteComposeDataBase"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}