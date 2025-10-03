# Hangman Android

A classic **Hangman** game implemented for Android — guess the word by selecting letters before running out of guesses.

## Table of Contents
- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation & Running](#installation--running)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contact](#contact)

## About
This project is an Android app version of the Hangman game. Users guess letters to uncover a hidden word, with limited incorrect attempts allowed. The app shows which letters have been guessed, how many guesses remain, and gives feedback on win/loss.

## Features
- Guess letters one at a time  
- Show placeholders for unguessed letters  
- Track and display letters already guessed  
- Limit of incorrect guesses before losing  
- Win / lose feedback  
- Option to start a new game  

## Tech Stack
| Layer | Technology / Tool |
|---|---|
| Language | Java / Kotlin (depending on the project’s implementation) |
| Platform | Android SDK |
| UI | Android XML layouts, Android Views or Jetpack UI components |
| Build System | Gradle |
| IDE | Android Studio recommended |

## Getting Started

### Prerequisites
- Android Studio  
- Android SDK with appropriate API levels  
- A device or emulator to run the app  

### Installation & Running

1. **Clone the repository**
    ```bash
    git clone https://github.com/RutayisireLucien/Hangman-Android.git
    cd Hangman-Android
    ```

2. **Open in Android Studio**
   - Use “Open an existing project” and choose the cloned directory  
   - Wait for Gradle sync / build

3. **Run the App**
   - Select an emulator or connected device  
   - Press Run / Play in Android Studio  

## Usage
- Launch the app on your Android device or emulator  
- A hidden word is shown as blank underscores  
- Tap letters to guess them  
- The app reveals correct letters in the word  
- Incorrect guesses reduce your remaining tries  
- Win if you guess the word before running out of tries  
- Optionally start a new game  

## Project Structure
```text
Hangman-Android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/           # Game logic, activities, controllers
│   │   │   └── res/            # XML layouts, drawables, strings
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
└── .gitignore
```

## Contact
Author: Rutayisire Lucien

GitHub: [RutayisireLucien](https://github.com/RutayisireLucien)
