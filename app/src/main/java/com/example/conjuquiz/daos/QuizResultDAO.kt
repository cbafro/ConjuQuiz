package com.example.conjuquiz.daos

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.example.conjuquiz.entities.Mistake
import com.example.conjuquiz.entities.QuizResult
import com.example.conjuquiz.entities.QuizResultWithMistakes
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDAO {

    @Query("SELECT * FROM quiz_results ORDER BY timestamp DESC")
    fun observeAllResults(): Flow<List<QuizResultWithMistakes>>

    @Query("SELECT * FROM quiz_results WHERE id = :id")
    fun observeResultById(id: Long): Flow<QuizResultWithMistakes?>

    @Insert
    suspend fun insertResult(result: QuizResult): Long

    @Insert
    suspend fun insertMistakes(mistakes: List<Mistake>)


}