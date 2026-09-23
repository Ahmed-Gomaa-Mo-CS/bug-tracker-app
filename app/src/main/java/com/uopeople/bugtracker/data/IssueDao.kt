package com.uopeople.bugtracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface IssueDao {
    @Insert
    suspend fun insert(issue: Issue)

    @Update
    suspend fun update(issue: Issue)

    @Delete
    suspend fun delete(issue: Issue)

    @Query("SELECT * FROM issues ORDER BY createdAt DESC")
    suspend fun getAll(): List<Issue>

    @Query("SELECT * FROM issues WHERE isSynced = 0")
    suspend fun getPendingSync(): List<Issue>

    @Query("DELETE FROM issues WHERE id = :id")
    suspend fun deleteById(id: Int)
}
