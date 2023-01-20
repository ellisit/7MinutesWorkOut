package com.eliott.training.sevenminutesworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyActivity: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
    fun fetchAllDates(): Flow<List<HistoryEntity>>
}