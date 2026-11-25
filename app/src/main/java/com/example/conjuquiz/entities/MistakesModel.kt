package com.example.conjuquiz.entities

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey


//
@Entity(tableName = "mistakes",
foreignKeys = [
    ForeignKey(entity = QuizResult::class, parentColumns = ["id"], childColumns = ["resultId"], onDelete = ForeignKey.CASCADE)],//automatic delete
indices = [Index("resultId")]
)
data class Mistake(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val resultId: Long,
    val questionText: String,
    val correctAnswer: String
)