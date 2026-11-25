package com.example.conjuquiz.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable data object Home : NavKey
@Serializable data object SavedAnswers : NavKey
@Serializable data object Verbs : NavKey
@Serializable data class Quiz(val level: Int) : NavKey
@Serializable data class Results(val resultId: Long) : NavKey
@Serializable data class VerbDetail(val verb: String) : NavKey
@Serializable data object Settings : NavKey
@Serializable data object ExitQuizDialog : NavKey
@Serializable data class RemoveSavedDialog(val questionId: Long) : NavKey