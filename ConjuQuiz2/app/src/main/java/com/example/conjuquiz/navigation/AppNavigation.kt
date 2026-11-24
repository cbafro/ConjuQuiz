package com.example.conjuquiz.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.example.conjuquiz.home.HomeScreen
import com.example.conjuquiz.quiz.ExitQuizDialogContent
import com.example.conjuquiz.quiz.QuizScreen
import com.example.conjuquiz.results.ResultsScreen
import com.example.conjuquiz.saved.RemoveSavedDialogContent
import com.example.conjuquiz.saved.SavedAnswersScreen
import com.example.conjuquiz.settings.SettingsScreen
import com.example.conjuquiz.verbs.VerbDetailScreen
import com.example.conjuquiz.verbs.VerbsScreen

private data class Tab(val key: NavKey, val label: String, val icon: ImageVector)

private val tabs = listOf(
    Tab(Home, "Home", Icons.Filled.Home),
    Tab(SavedAnswers, "Saved", Icons.Filled.Star),
    Tab(Verbs, "Verbs", Icons.AutoMirrored.Filled.List),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(Home)
    val currentTab = backStack.lastOrNull { key -> tabs.any { it.key == key } } ?: Home

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ConjuQuiz") },
                actions = {
                    IconButton(onClick = { backStack.openSettings() }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentTab == tab.key,
                        onClick = { backStack.goToTab(tab.key) },
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = { backStack.goBack() },
            sceneStrategy = remember { DialogSceneStrategy<NavKey>() },
            entryProvider = entryProvider {
                entry<Home> {
                    HomeScreen(onBeginQuiz = { level -> backStack.add(Quiz(level)) })
                }
                entry<Quiz> { key ->
                    QuizScreen(
                        level = key.level,
                        onFinished = { backStack.replaceTop(Results(1L)) }
                    )
                }
                entry<Results> { key ->
                    ResultsScreen(
                        resultId = key.resultId,
                        onTryAgain = { backStack.replaceTop(Quiz(1)) },
                        onBackToHome = { backStack.popToHome() }
                    )
                }
                entry<SavedAnswers> {
                    SavedAnswersScreen(onRemove = { id -> backStack.add(RemoveSavedDialog(id)) })
                }
                entry<Verbs> {
                    VerbsScreen(onVerbClick = { verb -> backStack.add(VerbDetail(verb)) })
                }
                entry<VerbDetail> { key ->
                    VerbDetailScreen(verb = key.verb)
                }
                entry<Settings> {
                    SettingsScreen()
                }
                entry<ExitQuizDialog>(metadata = DialogSceneStrategy.dialog()) {
                    ExitQuizDialogContent(
                        onLeave = { backStack.popToHome() },
                        onStay = { backStack.pop() }
                    )
                }
                entry<RemoveSavedDialog>(metadata = DialogSceneStrategy.dialog()) {
                    RemoveSavedDialogContent(
                        onRemove = { backStack.pop() },
                        onCancel = { backStack.pop() }
                    )
                }
            }
        )
    }
}

private fun MutableList<NavKey>.pop() {
    if (size > 1) removeAt(lastIndex)
}

private fun MutableList<NavKey>.popToHome() {
    while (size > 1) removeAt(lastIndex)
}

private fun MutableList<NavKey>.replaceTop(key: NavKey) {
    removeAt(lastIndex)
    add(key)
}

private fun MutableList<NavKey>.goBack() {
    if (lastOrNull() is Quiz) add(ExitQuizDialog) else pop()
}

private fun MutableList<NavKey>.goToTab(tab: NavKey) {
    if (lastOrNull() is Quiz) {
        add(ExitQuizDialog)
        return
    }
    popToHome()
    if (tab != Home) add(tab)
}

private fun MutableList<NavKey>.openSettings() {
    if (lastOrNull() != Settings) add(Settings)
}