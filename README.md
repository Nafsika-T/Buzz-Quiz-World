# 🎮 Buzz-Style Quiz Game

A quiz game inspired by **Buzz!**, implemented in Java with both CLI and GUI versions.  
It supports **1 or 2 players**, multiple question categories (sports, history, cinema, etc.), and images in questions.

## 📌 Features
- **Game Rounds**
  - ✅ Correct Answer – 1000 points for the correct choice.
  - ⏱ Timer – 5 second countdown, score = remaining ms × 0.2.
  - 🎲 Betting – player chooses to bet 250 / 500 / 750 / 1000 points.
  - ⚡ Fastest Finger – first correct answer: 1000 pts, second: 500 pts.
  - 🔥 Thermometer – first to 5 correct answers earns 5000 points.
- Randomized order of questions and answers.  
- No repetition of questions during a match.  
- **Statistics**
  - High scores in solo mode.
  - Win counts in 2-player mode.

## 🎮 Player Controls
| Player | Answer 1 | Answer 2 | Answer 3 | Answer 4 |
|--------|----------|----------|----------|----------|
| **P1** | `A`      | `S`      | `D`      | `F`      |
| **P2** | `J`      | `K`      | `L`      | `Μ`      |

## 🛠️ Technologies
- Java 17+
- JavaFX for GUI
- JUnit 5 for testing
- `.txt` files for questions, answers and statistics

