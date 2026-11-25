package com.example.conjuquiz.entities

import androidx.room3.Embedded
import androidx.room3.Relation

data class QuizResultWithMistakes(
    @Embedded val result: QuizResult,// copies & connects cols from quizresults into the mistakes with results
    @Relation(parentColumns = ["id"], entityColumns = ["resultId"]) val mistakes: List<Mistake>
)

