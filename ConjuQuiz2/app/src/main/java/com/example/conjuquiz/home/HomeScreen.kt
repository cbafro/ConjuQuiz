package com.example.conjuquiz.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conjuquiz.ui.theme.ConjuQuizTheme

private data class Level(val number: Int, val verb: String, val unlocked: Boolean)

private val levels = listOf(
    Level(1, "Avoir", true),
    Level(2, "Aller", false),
    Level(3, "Dire", false),
    Level(4, "Être", false),
)

@Composable
fun HomeScreen(onBeginQuiz: (level: Int) -> Unit, modifier: Modifier = Modifier) {
    var selected by rememberSaveable { mutableIntStateOf(1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Welcome to ConjuQuiz", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Test your French conjugations by filling in the blank with the correct conjugation.",
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider()
        Text("Levels", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            levels.forEach { level ->
                LevelCard(
                    level = level,
                    selected = level.number == selected,
                    onClick = { selected = level.number },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Text("Level $selected", style = MaterialTheme.typography.titleMedium)
        Button(onClick = { onBeginQuiz(selected) }, modifier = Modifier.fillMaxWidth()) {
            Text("Begin Quiz")
            Spacer(Modifier.width(8.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
private fun LevelCard(
    level: Level,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val description = if (level.unlocked) {
        "Level ${level.number}, ${level.verb}"
    } else {
        "Level ${level.number}, ${level.verb}, locked"
    }
    OutlinedCard(
        onClick = onClick,
        enabled = level.unlocked,
        border = if (selected) {
            BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        } else {
            CardDefaults.outlinedCardBorder(level.unlocked)
        },
        modifier = modifier
            .heightIn(min = 72.dp)
            .semantics { contentDescription = description }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (level.unlocked) {
                Text("${level.number}", style = MaterialTheme.typography.titleLarge)
            } else {
                Icon(Icons.Filled.Lock, contentDescription = null)
            }
            Text(level.verb, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ConjuQuizTheme { HomeScreen(onBeginQuiz = {}) }
}