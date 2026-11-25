package com.example.conjuquiz.data.reps

import com.example.conjuquiz.daos.QuizResultDAO
import com.example.conjuquiz.entities.Mistake
import com.example.conjuquiz.entities.QuizResult
import com.example.conjuquiz.entities.QuizResultWithMistakes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RoomResultsRep @Inject constructor(
    private val dao: QuizResultDAO) : ResultsRep {

    override fun observeAllResults(): Flow<List<QuizResultWithMistakes>> {
        return dao.observeAllResults()
    }


    override fun observeResultById(id: Long): Flow<QuizResultWithMistakes?> {
        return dao.observeResultById(id)
    }



    //save the parent, get the new ID, and then attach that ID to all the children before saving them.


    override suspend fun saveResult(result: QuizResult, mistakes: List<Mistake>): Long {
        val resultId = dao.insertResult(result)
        val updatedMistakes = mistakes.map { it.copy(resultId = resultId) }
        dao.insertMistakes(updatedMistakes)
        return resultId
    }

}