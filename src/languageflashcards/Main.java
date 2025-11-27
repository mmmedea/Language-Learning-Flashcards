package languageflashcards;

public class Main {

    public static void main(String[] args) {
        try {

            LanguageManager manager = new DefaultLanguageManager();


            LevelFactory factory = new LevelFactory(manager);


            LanguageLearningFlashcardsUI ui = new LanguageLearningFlashcardsUI(manager, factory);
            ui.start();

        } catch (RuntimeException e) {
            System.err.println("==========================================");
            System.err.println("❌ CRITICAL STARTUP ERROR");
            System.err.println("==========================================");
            System.err.println("The application could not start.");
            System.err.println("Error Details: " + e.getMessage());
            System.err.println("\nTroubleshooting:");
            System.out.println("1. Make sure the 'data' folder exists in your project root.");
            System.out.println("2. Check that 'vocab.csv' and 'phrases.csv' are inside 'data'.");
            System.err.println("==========================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}