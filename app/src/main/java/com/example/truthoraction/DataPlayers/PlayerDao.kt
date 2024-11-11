package com.example.truthoraction.DataPlayers

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(players: Players)
    @Query("SELECT * FROM Players")
    suspend fun getAllPlayers():List<Players>
    @Delete
    suspend fun deletePlayer(players: Players)
    @Query("DELETE FROM Players")
    suspend fun deleteAllPlayers()

}