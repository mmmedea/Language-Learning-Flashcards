Language Learning Flashcards

Description/Overview
      Language Learning Flashcards is an interactive command-line application designed to make learning new languages engaging and effective. The program helps users build their language skills progressively through three difficulty levels: vocabulary practice, phrase translation, and grammar mastery.

   The application supports five languages (Filipino, Spanish, French, Japanese, and Korean) and provides instant feedback with helpful tips to improve translation accuracy. Users can practice translating individual words, common phrases, and complete sentences with proper grammar, punctuation, and capitalization. The app makes language learning accessible and fun by breaking down complex language acquisition into manageable, interactive exercises.
__________________________________________________________________________________________________________________

OOP Concepts Applied
This project demonstrates several key Object-Oriented Programming principles:

1. Encapsulation
•	The Language class encapsulates language data (code and display name) with private fields and public getter methods
•	DefaultLanguageManager hides the internal storage of vocabulary and phrases, exposing only necessary methods through the LanguageManager interface
•	Data loading logic is contained within DefaultLanguageManager and not exposed to other classes

2. Abstraction
•	The LanguageManager interface defines a contract for language data access without specifying implementation details
•	LevelHandler is an abstract class that defines the structure for all level implementations while allowing each level to implement its own run() method
•	This allows the UI and factory classes to work with abstractions rather than concrete implementations

3. Inheritance
•	LevelOneHandler, LevelTwoHandler, and LevelThreeHandler all extend the abstract LevelHandler class
•	Each handler inherits the languageManager and targetLanguage fields from the parent class
•	Subclasses override the run() method to provide level-specific behavior

4. Polymorphism
•	The LevelFactory returns LevelHandler references that can point to any level handler subclass
•	The UI can call handler.run() without knowing which specific level implementation it's working with
•	Method overriding allows each level to have different behavior for the same method signature

5. Separation of Concerns
•	Each class has a single, well-defined responsibility
•	UI logic is separated from game logic and data management
•	The factory pattern separates object creation from business logic

_______________________________________________________________________________________________________________
Program Structure
The application follows a modular architecture with clear separation of concerns:

Core Classes and Their Roles

Main.java
•	Entry point of the application
•	Initializes the LanguageManager, LevelFactory, and UI
•	Handles critical startup errors

Language.java
•	Simple data class representing a language with a code and display name
•	Provides getter methods for accessing language information

LanguageManager.java (Interface)
•	Defines the contract for managing language data
•	Declares methods: getAvailableLanguages(), getWord(), getWordCount()

DefaultLanguageManager.java
•	Implements LanguageManager interface
•	Loads vocabulary and phrases from CSV files
•	Manages data for 5 languages (Filipino, Spanish, French, Japanese, Korean)
•	Provides methods to retrieve words, phrases, and their translations

LevelHandler.java (Abstract)
•	Base class for all level implementations
•	Holds references to LanguageManager and target Language
•	Declares abstract run() method that each level must implement

LevelOneHandler.java
•	Implements vocabulary practice (word translation)
•	Randomly selects 10 words for translation
•	Accepts multiple correct answers (separated by / in CSV)

LevelTwoHandler.java
•	Implements phrase translation
•	Presents 5 random common phrases
•	Requires more precise translation

LevelThreeHandler.java
•	Implements grammar mastery level
•	Checks capitalization, punctuation, and translation accuracy
•	Provides detailed feedback on grammatical errors

LevelFactory.java
•	Factory class that creates appropriate LevelHandler instances
•	Uses the Factory Method design pattern
•	Returns the correct handler based on level number (1, 2, or 3)

LanguageLearningFlashcardsUI.java
•	Manages all user interface and interaction
•	Handles menu navigation, user input, and visual presentation
•	Features animated text, loading bars, and color-coded output
•	Controls the main program loop and session flow

_______________________________________________________________________________________________________________

Class Relationships
Main
 └─→ creates DefaultLanguageManager (implements LanguageManager)
 └─→ creates LevelFactory
 └─→ creates LanguageLearningFlashcardsUI
      └─→ uses LevelFactory to create LevelHandler instances
           └─→ LevelOneHandler extends LevelHandler
           └─→ LevelTwoHandler extends LevelHandler
           └─→ LevelThreeHandler extends LevelHandler
_______________________________________________________________________________________________________________
How to Run the Program
Prerequisites
•	Java Development Kit (JDK) 8 or higher
•	A terminal or command prompt
•	The project files including the data folder with vocab.csv and phrases.csv
Step-by-Step Instructions
1.	Navigate to the project directory
2.	cd path/to/languageflashcards
3.	Compile all Java files
4.	javac languageflashcards/*.java
5.	Run the program
6.	java languageflashcards.Main
Important Notes
•	Ensure the data folder is in the same directory as your compiled classes
•	The CSV files must be UTF-8 encoded
•	If you encounter a startup error, verify that both vocab.csv and phrases.csv exist in the data folder
________________________________________
Sample Output
======================================
   LANGUAGE LEARNING FLASHCARDS
   Interactive Grammar & Vocabulary
======================================

Initializing system...
[████████████████████] Done.

Identify yourself:
➜ John

Welcome, John. System ready.

╔═══════════════════════════════════════╗
║             MAIN MENU                 ║
╠═══════════════════════════════════════╣
User: John

 [1] Start New Session
 [2] Exit

╚═══════════════════════════════════════╝
➜ 1

╔═══════════════════════════════════════╗
║          SELECT LANGUAGE              ║
╠═══════════════════════════════════════╣
 [1] Filipino
 [2] Spanish
 [3] French
 [4] Japanese
 [5] Korean
 [6] Back

╚═══════════════════════════════════════╝
➜ 2

╔═══════════════════════════════════════╗
║        SELECT DIFFICULTY              ║
╠═══════════════════════════════════════╣
 [1] Vocabulary (Words)
 [2] Phrases    (Sentences)
 [3] Grammar    (Mechanics)
 [4] Back

╚═══════════════════════════════════════╝
➜ 1

Target: Spanish
Mode:   Level 1

Loading modules...
[████████████████████] Done.

=== LEVEL 1: Vocabulary Practice ===
Translate the following words into Spanish.
Type 'EXIT' to return to the menu early.

Q1: hello -> hola
✅ Correct!
Q2: water -> agua
✅ Correct!
Q3: book -> libro
✅ Correct!

------------------------------------------------
Session Complete! Final Score: 10 / 10
------------------------------------------------
___________________________________________________________________________________________________________________
Author and Acknowledgement
Author: Macatangay, Althea Rhien N.
Martinez, Maricris M.
Marasigan, Kayla Jane D.

Course: CS 211 - Object-Oriented Programming
Institution: Batangas State University
Acknowledgements
This project extends sincere thanks to Christiana Grace Alib for her guidance on OOP principles. The CSV data structure used in this work was inspired by best practices in language learning. Additionally, the use of ANSI color formatting contributed to enhancing the overall user experience.

___________________________________________________________________________________________________________________

Future Enhancements
Potential improvements for future versions:
•	Progress Tracking: Save user scores and track improvement over time
•	Difficulty Scaling: Adaptive difficulty based on user performance
•	More Languages: Expand beyond the current 5 languages
•	Audio Support: Pronunciation guidance for words and phrases
•	Timed Challenges: Speed-based exercises for advanced learners
•	User Accounts: Multiple user profiles with individual progress
•	Custom Vocabulary: Allow users to add their own words and phrases
•	Spaced Repetition: Implement scientifically-backed review schedules
•	Mobile Version: Port to Android/iOS for on-the-go learning

__________________________________________________________________________________________________________________

References
•	Java Documentation: https://docs.oracle.com/javase/
•	OOP Design Patterns: Gang of Four Design Patterns
•	Language Data Sources: Open-source language learning datasets
