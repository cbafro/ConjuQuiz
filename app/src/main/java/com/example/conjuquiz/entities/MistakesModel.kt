package com.example.conjuquiz.entities

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey


//
@Entity(tableName = "mistakes",
foreignKeys = [
    ForeignKey(entity = QuizResult::class, parentColumns = ["id"],//parent  id & table
        childColumns = ["resultId"],// the foreign key col name
        onDelete = ForeignKey.CASCADE)],//automatic delete when parent is
indices = [Index("resultId")] //basis search point  for finding
)
data class Mistake(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val resultId: Long,
    val questionText: String,
    val correctAnswer: String
)