# Screen Inventory - ConjuQuiz App

| Screen Name | Navigation Key     | Data Displayed | ViewModel Responsibilities |
|---|--------------------|---|---|
| Homepage | home | App title, quiz options, navigation buttons for Quiz, Saved Conjugations and Settings. | Manages homepage navigation and available quiz options. |
| Quiz Page | quiz  | Spanish conjugation questions, verbs, answer choices, correct answers, feedback and score. | Loads quiz questions, checks answers, tracks quiz progress and calculates the score. |
| Saved Conjugations | saved_conjugations | List of saved Spanish verbs, their English translations and conjugated forms. | Loads, saves and deletes conjugations from the user's saved collection. |
| Settings | settings | User preferences, such as language, difficulty level and quiz settings. | Loads and saves user preferences and manages changes to settings. |