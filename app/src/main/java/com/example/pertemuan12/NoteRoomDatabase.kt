package com.example.pertemuan12

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Note::class], version = 1, exportSchema = false)

abstract class NoteRoomDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao?
    companion object{
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null
        fun getDatabase(context: Context) : NoteRoomDatabase?{
            if (INSTANCE==null){
                synchronized(NoteRoomDatabase::class.java){
                    INSTANCE = databaseBuilder(context.applicationContext,
                        NoteRoomDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}