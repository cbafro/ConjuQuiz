package com.example.conjuquiz.entities

import androidx.room3.Embedded

data class QuizResultWithMistakes(
    @Embedded val result: QuizResult,// connects the mistakes with results
    @Relation(parentColumn = "id", entityColumn = "resultId") val mistakes: List<Mistake>
)

