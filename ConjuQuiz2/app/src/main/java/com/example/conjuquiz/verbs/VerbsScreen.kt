package com.example.conjuquiz.verbs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conjuquiz.ui.components.EmptyView
import com.example.conjuquiz.ui.components.ErrorView
import com.example.conjuquiz.ui.components.LoadingView
import com.example.conjuquiz.ui.components.ScreenStatus
import com.example.conjuquiz.ui.theme.ConjuQuizTheme

private val allVerbs = listOf(
    "Faire", "Aller", "Avoir", "Être", "Devoir", "Pouvoir", "Vouloir",
    "Voir", "Savoir", "Comprendre", "Croire", "Parler", "Prendre",
    "Venir", "Tenir", "Demander", "Passer", "Rester", "Aimer",
)

private val sampleConjugations = mapOf(
    "Avoir" to listOf("j'ai", "tu as", "il/elle a", "nous avons", "vous avez", "ils/elles ont"),
)

@Composable
fun VerbsScreen(onVerbClick: (verb: String) -> Unit, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text("All verbs", style = MaterialTheme.typography.titleLarge)
        }
        items(allVerbs) { verb ->
            OutlinedCard(
                onClick = { onVerbClick(verb) },
                modifier = Modifier.heightIn(min = 48.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(verb, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun VerbDetailScreen(
    verb: String,
    modifier: Modifier = Modifier,
    status: ScreenStatus = ScreenStatus.Content,
    onRetry: () -> Unit = {}
) {
    val forms = sampleConjugations[verb].orEmpty()
    when {
        status == ScreenStatus.Loading -> LoadingView("Loading conjugations…", modifier)
        status == ScreenStatus.Error -> ErrorView("Error loading conjugations", onRetry, modifier)
        forms.isEmpty() -> EmptyView("No conjugations for $verb yet.", modifier)
        else -> LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Text(verb, style = MaterialTheme.typography.headlineMedium)
            }
            item {
                Text("Présent", style = MaterialTheme.typography.titleMedium)
            }
            items(forms) { form ->
                Text(form, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VerbsScreenPreview() {
    ConjuQuizTheme { VerbsScreen(onVerbClick = {}) }
}

@Preview(showBackground = true)
@Composable
private fun VerbDetailPreview() {
    ConjuQuizTheme { VerbDetailScreen(verb = "Avoir") }
}