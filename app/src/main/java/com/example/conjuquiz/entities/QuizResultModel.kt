package com.example.conjuquiz.entities

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey (autoGenerate = true)// no nned to be assigned
    val id: Long = 0, val level  : Int , val score: Int , val totalVerbs :Int, val timestamp: Long = System.currentTimeMillis()//to sort n show past quizzes

)