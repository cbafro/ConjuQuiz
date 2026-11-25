package com.example.conjuquiz.data

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.conjuquiz.daos.QuizResultDAO
import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.conjuquiz.entities.Mistake
import com.example.conjuquiz.entities.QuizResult
import kotlinx.coroutines.Dispatchers


@Database(entities = [QuizResult::class, Mistake::class], version = 1, exportSchema = true)
abstract class ConjuDB : RoomDatabase() {
    abstract fun quizResultDao(): QuizResultDAO



}