# User Flow: ConjuQuiz

**Legend:** rectangle = screen, hexagon = dialog, **thick arrow** = carries an argument.
**Start destination:** Home. Home, Saved Answers and View Verbs are bottom-nav tabs available from every screen. The gear icon opens Settings from any screen.

```mermaid
flowchart TD
    Launch([App launch]) --> Home

    subgraph Tabs["Bottom nav tabs (on every screen)"]
        Home["<b>Home</b> (start destination)<br/>key: Home"]
        Saved["<b>Saved Answers</b><br/>key: SavedAnswers"]
        Verbs["<b>View Verbs</b><br/>key: Verbs"]
    end

    Quiz["<b>Quiz</b><br/>key: Quiz(level)"]
    Results["<b>Quiz Results</b><br/>key: Results(resultId)"]
    VerbDetail["<b>Verb Detail</b><br/>key: VerbDetail(verb)"]
    Settings["<b>Settings</b><br/>key: Settings"]
    ExitDialog{{"<b>Leave quiz?</b> (dialog)<br/>key: ExitQuizDialog"}}
    RemoveDialog{{"<b>Remove saved answer?</b> (dialog)<br/>key: RemoveSavedDialog(questionId)"}}

    Home -- "Select unlocked level (stays on screen)" --> Home
    Home == "Begin Quiz: level" ==> Quiz

    Quiz -- "Submit / Next Question / Save Question (stays on screen)" --> Quiz
    Quiz == "Next Question after last: resultId" ==> Results
    Quiz -- "Back or any tab" --> ExitDialog
    ExitDialog -- "Leave" --> Home
    ExitDialog -- "Stay" --> Quiz

    Results == "Try Again: level" ==> Quiz
    Results -- "Back to Home / Back" --> Home
    Results -- "Any tab" --> Tabs

    Saved == "Trash icon: questionId" ==> RemoveDialog
    RemoveDialog -- "Remove / Cancel" --> Saved
    Saved -- "Back" --> Home

    Verbs == "Tap a verb: verb" ==> VerbDetail
    VerbDetail -- "Back" --> Verbs
    Verbs -- "Back" --> Home

    Tabs -- "Gear icon" --> Settings
    Settings -- "Back (returns to previous screen)" --> Tabs
```
