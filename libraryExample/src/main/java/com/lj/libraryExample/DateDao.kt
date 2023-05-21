package com.lj.libraryExample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DateDao {
    @Insert
    suspend fun insert(date: Date): Long

    @Query("SELECT * FROM date")
    fun getAllDates(): Flow<List<Date>>
}
