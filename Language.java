src/languageflashcards/Language.java

package com.example.language;
package languageflashcards;

/**
 * Represents a language used in the Language Learning Flashcards application.
 *
 * This class is intentionally simple:
 *  - Holds the language code (e.g., "fil", "es", "fr")
 *  - Holds the display name shown in the UI (e.g., "Filipino", "Spanish")
 *
 * Used by the UI, LanguageManager, and LevelHandlers.
 */
public class Language {

    private final String code;
    private final String displayName;
    public Language(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName + " (" + code + ")";
    }
}
