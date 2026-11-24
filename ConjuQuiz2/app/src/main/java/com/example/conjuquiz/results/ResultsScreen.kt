package com.example.conjuquiz.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conjuquiz.ui.theme.ConjuQuizTheme

private data class Mistake(val yourAnswer: String, val correctAnswer: String)

private val sampleMistakes = listOf(
    Mistake("Je fait", "Je fais"),
    Mistake("Il ont", "Il a"),
)

@Composable
fun ResultsScreen(
    resultId: Long,
    onTryAgain: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val correct = 2
    val total = 4
    val percent = correct * 100 / total

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clearAndSetSemantics { contentDescription = "Score: $percent percent" }
        ) {
            CircularProgressIndicator(
                progress = { percent / 100f },
                strokeWidth = 12.dp,
                modifier = Modifier.size(160.dp)
            )
            Text("$percent%", style = MaterialTheme.typography.headlineLarge)
        }
        Text("Good effort", style = MaterialTheme.typography.titleLarge)
        Text("$correct correct", style = MaterialTheme.typography.bodyLarge)
        Text("${sampleMistakes.size} mistakes", style = MaterialTheme.typography.bodyLarge)
        Text(
            "Mistakes",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
        sampleMistakes.forEach { mistake ->
            OutlinedCard(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Incorrect",
                        tint = MaterialTheme.colorScheme.error
                    )
                    Column {
                        Text(mistake.yourAnswer, style = MaterialTheme.typography.bodyLarge)
                        Text("Correct: ${mistake.correctAnswer}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
        Button(onClick = onTryAgain, modifier = Modifier.fillMaxWidth()) { Text("Try again") }
        OutlinedButton(onClick = onBackToHome, modifier = Modifier.fillMaxWidth()) { Text("Back to Home") }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultsScreenPreview() {
    ConjuQuizTheme { ResultsScreen(resultId = 1L, onTryAgain = {}, onBackToHome = {}) }
}