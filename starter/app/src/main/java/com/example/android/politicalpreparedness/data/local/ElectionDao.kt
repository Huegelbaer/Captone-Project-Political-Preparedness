package com.example.android.politicalpreparedness.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.politicalpreparedness.data.local.entities.ElectionEntity

@Dao
interface ElectionDao {

    @Insert
    fun insert(election: ElectionEntity)

    @Query("SELECT * FROM election_table")
    fun getAll(): List<ElectionEntity>

    @Query("SELECT * FROM election_table WHERE id = :id")
    fun getById(id: Int): ElectionEntity?

    @Query("DELETE FROM election_table WHERE id = :id")
    fun deleteById(id: Int)

    @Query("DELETE FROM election_table")
    fun clear()
}