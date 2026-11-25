package com.example.conjuquiz.data.reps

import com.example.conjuquiz.entities.Mistake
import com.example.conjuquiz.entities.QuizResult
import com.example.conjuquiz.entities.QuizResultWithMistakes
import kotlinx.coroutines.flow.Flow

interface ResultsRep {


    fun observeAllResults(): Flow<List<QuizResultWithMistakes>>
    fun observeResultById(id: Long): Flow<QuizResultWithMistakes?>
    suspend fun saveResult(result: QuizResult, mistakes: List<Mistake>): Long
}