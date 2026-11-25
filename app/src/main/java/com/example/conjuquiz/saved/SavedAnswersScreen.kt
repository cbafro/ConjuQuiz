package com.example.conjuquiz.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conjuquiz.ui.components.ConfirmDialog
import com.example.conjuquiz.ui.components.EmptyView
import com.example.conjuquiz.ui.components.ErrorView
import com.example.conjuquiz.ui.components.LoadingView
import com.example.conjuquiz.ui.components.ScreenStatus
import com.example.conjuquiz.ui.theme.ConjuQuizTheme

private data class SavedAnswer(val id: Long, val sentence: String)

private val sampleSaved = listOf(
    SavedAnswer(1, "Je fais les devoirs"),
    SavedAnswer(2, "Tu as mon cœur"),
    SavedAnswer(3, "Il a un gâteau"),
)

@Composable
fun SavedAnswersScreen(
    onRemove: (questionId: Long) -> Unit,
    modifier: Modifier = Modifier,
    status: ScreenStatus = ScreenStatus.Content,
    onRetry: () -> Unit = {}
) {
    when (status) {
        ScreenStatus.Loading -> LoadingView("Loading answers…", modifier)
        ScreenStatus.Error -> ErrorView("Error loading answers", onRetry, modifier)
        ScreenStatus.Empty -> EmptyView("No saved answers yet.", modifier)
        ScreenStatus.Content -> LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text("Saved Answers", style = MaterialTheme.typography.titleLarge)
            }
            items(sampleSaved, key = { it.id }) { saved ->
                OutlinedCard(Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            saved.sentence,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { onRemove(saved.id) }) {
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Remove saved answer: ${saved.sentence}"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RemoveSavedDialogContent(onRemove: () -> Unit, onCancel: () -> Unit) {
    ConfirmDialog(
        title = "Remove saved answer?",
        message = "It will be removed from your Saved Answers.",
        confirmLabel = "Remove",
        dismissLabel = "Cancel",
        onConfirm = onRemove,
        onDismiss = onCancel
    )
}

@Preview(showBackground = true)
@Composable
private fun SavedAnswersPreview() {
    ConjuQuizTheme { SavedAnswersScreen(onRemove = {}) }
}

@Preview(showBackground = true)
@Composable
private fun SavedAnswersLoadingPreview() {
    ConjuQuizTheme { SavedAnswersScreen(onRemove = {}, status = ScreenStatus.Loading) }
}

@Preview(showBackground = true)
@Composable
private fun SavedAnswersErrorPreview() {
    ConjuQuizTheme { SavedAnswersScreen(onRemove = {}, status = ScreenStatus.Error) }
}

@Preview(showBackground = true)
@Composable
private fun SavedAnswersEmptyPreview() {
    ConjuQuizTheme { SavedAnswersScreen(onRemove = {}, status = ScreenStatus.Empty) }
}