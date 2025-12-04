Language Learning Flashcards 🌍

IT 2111 - Object-Oriented Programming

Authors:

- Macatangay, Althea Rhien N.
  
- Martinez, Maricris M.

- Marasigan, Kayla Jane D.

________________________________________________________________________________________________________

📖 Overview

Language Learning Flashcards is an interactive command-line application designed to make language learning engaging and effective. The program helps users build their language skills progressively through three difficulty levels: vocabulary practice, phrase translation, and grammar mastery.

The application supports **five languages** (Filipino, Spanish, French, Japanese, and Korean) and provides instant feedback with helpful tips to improve translation accuracy. Users can practice translating individual words, common phrases, and complete sentences with proper grammar, punctuation, and capitalization.

________________________________________________________________________________________________________

✨ Features

🎯 What Users Can Do:

- Choose from 5 Languages**: Filipino, Spanish, French, Japanese, and Korean
  
- Progressive Learning Levels:
  
  - Level 1 (Vocabulary): Translate 10 random words with support for multiple correct answers
  - Level 2 (Phrases): Practice 5 common phrases with precision checking
  - Level 3 (Grammar): Master sentence translation with capitalization and punctuation validation
    
- Instant Feedback: Receive immediate corrections and see the right answers
  
- Flexible Sessions: Exit anytime with 'EXIT' command
  
- Personalized Experience: Enter your name for a customized greeting
  
- Visual Enhancements: Enjoy animated text, loading bars, and color-coded output

________________________________________________________________________________________________________

💾 Storage & Data Management

The application uses CSV files for storing language data:

- `data/vocab.csv`: Contains vocabulary words across all supported languages
- `data/phrases.csv`: Contains common phrases and sentences

CSV Structure: Each file has 6 columns (English, Filipino, Spanish, French, Japanese, Korean) with UTF-8 encoding to support special characters and non-Latin scripts.

________________________________________________________________________________________________________

📂 Project Structure

```
languageflashcards/

│

├── src/

│   └── languageflashcards/

│       ├── Main.java                          # Application entry point

│       ├── Language.java                      # Language data model

│       ├── LanguageManager.java               # Interface for language data

│       ├── DefaultLanguageManager.java        # CSV data loader & manager

│       ├── LevelHandler.java                  # Abstract base for levels

│       ├── LevelOneHandler.java               # Vocabulary practice

│       ├── LevelTwoHandler.java               # Phrase translation

│       ├── LevelThreeHandler.java             # Grammar mastery

│       ├── LevelFactory.java                  # Factory for level creation

│       └── LanguageLearningFlashcardsUI.java  # User interface & menus

│

├── data/

│   ├── vocab.csv                              # Vocabulary database

│   └── phrases.csv                            # Phrases database

│

├── static/

│   └── [Team photos]                          # Formal photos of contributors

│

├── .gitignore                                 # Git ignore rules

└── README.md                                  # This file

```

________________________________________________________________________________________________________

🚀 How to Run the Program

 Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt
- UTF-8 encoding support in your terminal (for special characters)

Step-by-Step Instructions

1. Navigate to the project directory
   ```bash
   cd path/to/languageflashcards
   ```

2. Compile all Java files
   ```bash
   javac languageflashcards/*.java
   ```

3. Run the program
   ```bash
   java languageflashcards.Main
   ```

⚠️ Important Notes
- Ensure the `data` folder is in the same directory as your compiled classes
- The CSV files must be UTF-8 encoded
- If you encounter a startup error, verify that both `vocab.csv` and `phrases.csv` exist in the `data` folder

________________________________________________________________________________________________________

🎓 OOP Principles Applied

This project demonstrates key Object-Oriented Programming concepts:

 1. Encapsulation
- `Language` class encapsulates language data with private fields and public getters
- `DefaultLanguageManager` hides internal storage, exposing only necessary methods
- Data loading logic is contained and not exposed to other classes

2. Abstraction
- `LanguageManager` interface defines a contract without implementation details
- `LevelHandler` abstract class provides structure while allowing custom implementations
- Separates "what" from "how"

3. Inheritance
- `LevelOneHandler`, `LevelTwoHandler`, and `LevelThreeHandler` extend `LevelHandler`
- Each handler inherits common fields (`languageManager`, `targetLanguage`)
- Subclasses override `run()` for level-specific behavior

4. Polymorphism
- `LevelFactory` returns `LevelHandler` references pointing to specific implementations
- UI calls `handler.run()` without knowing the concrete type
- Method overriding enables different behaviors for the same interface

5. Design Patterns
- Factory Pattern: `LevelFactory` centralizes object creation
- Interface Segregation: `LanguageManager` provides a focused contract
- Separation of Concerns: Each class has a single, well-defined responsibility

________________________________________________________________________________________________________

📸 Example Output

```

==========================================

      LANGUAGE LEARNING FLASHCARDS

      Interactive Grammar & Vocabulary

==========================================

  System Initialization...

  [█████████████████████████] Done

  Enter your name:

 ➤ John

  Welcome, John to Language Learning Flashcards :>.

╔═══════════════════════════════════════╗

║             MAIN MENU                 ║

╠═══════════════════════════════════════╣

║  User: John                           ║

╠═══════════════════════════════════════╣

║  [1] Start New Session                ║

║  [2] Exit Application                 ║

╚═══════════════════════════════════════╝

 ➤ 1

╔═══════════════════════════════════════╗

║          SELECT LANGUAGE              ║

╠═══════════════════════════════════════╣

║  [1] Filipino                         ║

║  [2] Spanish                          ║

║  [3] French                           ║

║  [4] Japanese                         ║

║  [5] Korean                           ║

╠═══════════════════════════════════════╣

║  [6] Back                             ║

╚═══════════════════════════════════════╝

 ➤ 2

=== LEVEL 1: Vocabulary Practice ===

Translate the following words into Spanish.

Type 'EXIT' to return to the menu early.


Q1: hello -> hola

✅ Correct!

Q2: water -> agua

✅ Correct!

Q3: day -> dia

❌ Incorrect. The answer was: día

------------------------------------------------

Session Complete! Final Score: 9 / 10

------------------------------------------------

```

________________________________________________________________________________________________________

💻 Code Snippet

 CSV Data Loading Example
 
```java
private void loadCsv(String path, List<String[]> target) {

    try (BufferedReader br = new BufferedReader(

            new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

        
        String line = br.readLine(); // skip header

        while ((line = br.readLine()) != null) {

            String[] cells = line.split(",", -1);

            
            // Ensure exactly 6 columns

            if (cells.length < 6) {

                String[] padded = new String[6];

                for (int i = 0; i < 6; i++) {

                    padded[i] = i < cells.length ? cells[i].trim() : "";

                }
                target.add(padded);

            } else {

                for (int i = 0; i < cells.length; i++) {

                    cells[i] = cells[i].trim();

                }

                target.add(cells);

            }

        }

    } catch (Exception e) {

        throw new RuntimeException("Failed to load CSV: " + path, e);

    }

}

```

 Factory Pattern Implementation
```java

public LevelHandler createLevelHandler(int level, Language language) {

    switch (level) {

        case 1:

            return new LevelOneHandler(languageManager, language);

        case 2:

            return new LevelTwoHandler(languageManager, language);

        case 3:

            return new LevelThreeHandler(languageManager, language);

        default:

            return null;

    }

}

```

_______________________________________________________________________________________________________

👥 Contributors




<img width="400" height="400" alt="AvatarMaker" src="https://github.com/user-attachments/assets/8a7f272f-ac0f-45fb-99d8-525164caeb9b" />


Macatangay, Althea Rhien N.        



<img width="400" height="400" alt="AvatarMaker (1)" src="https://github.com/user-attachments/assets/b7453eaf-0e40-4f42-bd06-227ad83f80bc" />




Martinez, Maricris M.            



<img width="400" height="400" alt="AvatarMaker (2)" src="https://github.com/user-attachments/assets/4c66f26f-f172-48f2-bb05-ec6d434f52f0" />



Marasigan, Kayla Jane D.


Course: CS 211 - Object-Oriented Programming  
Institution: Batangas State University

________________________________________________________________________________________________________

🙏 Acknowledgements

This project extends sincere thanks to:

This project extends sincere thanks to Christiana Grace Alib for her guidance on OOP principles and design patterns, to open-source language learning communities for inspiring the use of CSV data structures, to the ANSI color formatting standards that helped 
enhance the overall user experience, and to the Gang of Four Design Patterns for providing valuable architectural guidance.

________________________________________________________________________________________________________

📚 References

- [Java Documentation](https://docs.oracle.com/javase/)
  
- [Gang of Four Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)
  
- Open-source language learning datasets


________________________________________________________________________________________________________

📄 License

This project is created for educational purposes as part of CS 211 coursework at Batangas State University.

________________________________________________________________________________________________________

Made with ❤️ by Team Language Learners!
