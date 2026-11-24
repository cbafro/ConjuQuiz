package com.example.conjuquiz.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conjuquiz.ui.components.ConfirmDialog
import com.example.conjuquiz.ui.components.ErrorView
import com.example.conjuquiz.ui.components.LoadingView
import com.example.conjuquiz.ui.components.ScreenStatus
import com.example.conjuquiz.ui.theme.ConjuQuizTheme

private data class Question(val sentence: String, val verb: String, val answer: String)

private val sampleQuestions = listOf(
    Question("Je ____ des allergies au pollen.", "avoir", "ai"),
    Question("Tu ____ un chat.", "avoir", "as"),
    Question("Nous ____ faim.", "avoir", "avons"),
    Question("Ils ____ deux frères.", "avoir", "ont"),
)

@Composable
fun QuizScreen(
    level: Int,
    onFinished: () -> Unit,
    modifier: Modifier = Modifier,
    status: ScreenStatus = ScreenStatus.Content,
    onRetry: () -> Unit = {}
) {
    when (status) {
        ScreenStatus.Loading -> LoadingView("Loading question…", modifier)
        ScreenStatus.Error -> ErrorView("Error loading question", onRetry, modifier)
        ScreenStatus.Content, ScreenStatus.Empty -> QuizContent(level, onFinished, modifier)
    }
}

@Composable
private fun QuizContent(level: Int, onFinished: () -> Unit, modifier: Modifier = Modifier) {
    var index by rememberSaveable { mutableIntStateOf(0) }
    var answer by rememberSaveable { mutableStateOf("") }
    var checked by rememberSaveable { mutableStateOf(false) }
    var saved by rememberSaveable { mutableStateOf(false) }

    val question = sampleQuestions[index]
    val correct = answer.trim().equals(question.answer, ignoreCase = true)
    val isLast = index == sampleQuestions.lastIndex

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Level $level · Question ${index + 1} of ${sampleQuestions.size}",
            style = MaterialTheme.typography.titleMedium
        )
        Text("${question.sentence} (${question.verb})", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Type answer") },
            singleLine = true,
            enabled = !checked,
            modifier = Modifier.fillMaxWidth()
        )
        if (!checked) {
            Button(
                onClick = { checked = true },
                enabled = answer.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) { Text("Submit") }
        } else {
            FeedbackRow(correct = correct, correctAnswer = question.answer)
            Button(
                onClick = {
                    if (isLast) {
                        onFinished()
                    } else {
                        index++
                        answer = ""
                        checked = false
                        saved = false
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (isLast) "See results" else "Next question") }
            if (correct) {
                OutlinedButton(
                    onClick = { saved = true },
                    enabled = !saved,
                    modifier = Modifier.fillMaxWidth()
                ) { Text(if (saved) "Saved" else "Save question") }
            }
        }
    }
}

@Composable
private fun FeedbackRow(correct: Boolean, correctAnswer: String) {
    val color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            if (correct) Icons.Filled.Check else Icons.Filled.Close,
            contentDescription = null,
            tint = color
        )
        Text(
            if (correct) "Correct!" else "Incorrect. The answer is $correctAnswer.",
            style = MaterialTheme.typography.titleMedium,
            color = color
        )
    }
}

@Composable
fun ExitQuizDialogContent(onLeave: () -> Unit, onStay: () -> Unit) {
    ConfirmDialog(
        title = "Leave quiz?",
        message = "Your progress on this quiz will be lost.",
        confirmLabel = "Leave",
        dismissLabel = "Stay",
        onConfirm = onLeave,
        onDismiss = onStay
    )
}

@Preview(showBackground = true)
@Composable
private fun QuizContentPreview() {
    ConjuQuizTheme { QuizScreen(level = 1, onFinished = {}) }
}

@Preview(showBackground = true)
@Composable
private fun QuizLoadingPreview() {
    ConjuQuizTheme { QuizScreen(level = 1, onFinished = {}, status = ScreenStatus.Loading) }
}

@Preview(showBackground = true)
@Composable
private fun QuizErrorPreview() {
    ConjuQuizTheme { QuizScreen(level = 1, onFinished = {}, status = ScreenStatus.Error) }
}