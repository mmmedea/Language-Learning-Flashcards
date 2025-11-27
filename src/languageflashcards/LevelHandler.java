package languageflashcards;

import java.util.Scanner;

public abstract class LevelHandler {

    protected LanguageManager languageManager;
    protected Language targetLanguage;

    public LevelHandler(LanguageManager languageManager, Language targetLanguage) {
        this.languageManager = languageManager;
        this.targetLanguage = targetLanguage;
    }

    public abstract void run(Scanner scanner);
}