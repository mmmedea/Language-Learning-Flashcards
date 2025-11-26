package languageflashcards;

public class LevelFactory {

    private final LanguageManager languageManager;

    public LevelFactory(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

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
}