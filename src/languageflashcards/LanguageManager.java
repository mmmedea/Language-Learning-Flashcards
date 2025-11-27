package languageflashcards;

public interface LanguageManager {

    Language[] getAvailableLanguages();

    String getWord(String languageCode, int index);

    int getWordCount();

}