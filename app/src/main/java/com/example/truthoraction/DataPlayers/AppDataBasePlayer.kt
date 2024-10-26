package com.example.truthoraction.DataPlayers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Players::class], version = 1)
abstract class AppDataBasePlayer : RoomDatabase() {
    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBasePlayer? = null

        fun getDatabase(context: Context):AppDataBasePlayer{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBasePlayer::class.java,
                    "Players"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}