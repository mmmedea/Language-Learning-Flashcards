package languageflashcards;

import java.util.Scanner;

public class LevelThreeHandler extends LevelHandler {

    public LevelThreeHandler(LanguageManager languageManager, Language targetLanguage) {
        super(languageManager, targetLanguage);
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("\n=== LEVEL 3: Grammar Mastery ===");
        System.out.println("In this level, your punctuation and capitalization count.");
        System.out.println("Translate the sentence exactly as it should be written grammatically.\n");

        
        String[] challenges = {
            "I am reading a book.",
            "Where is the bathroom?",
            "Good morning."
        };

        DefaultLanguageManager mgr = (DefaultLanguageManager) languageManager;

        int score = 0;

        for (String challenge : challenges) {
            System.out.println("Translate: \"" + challenge + "\"");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) break;

            boolean capCheck = !input.isEmpty() && Character.isUpperCase(input.charAt(0));
            
            boolean puncCheck = !input.isEmpty() && (input.endsWith(".") || input.endsWith("?") || input.endsWith("!") || input.endsWith("。")); // Japanese period

            String correctTranslation = findTranslationFor(challenge);
            boolean transCheck = input.equalsIgnoreCase(correctTranslation);

            if (capCheck && puncCheck && transCheck) {
                System.out.println("🌟 Flawless! (Grammar + Translation correct)");
                score++;
            } else {
                System.out.println("⚠️ Needs Improvement:");
                if (!capCheck) System.out.println("   - Start your sentence with a Capital letter.");
                if (!puncCheck) System.out.println("   - End your sentence with proper punctuation (. ? !).");
                if (!transCheck) System.out.println("   - Translation mismatch. Expected: " + correctTranslation);
            }
            System.out.println(); // spacer
        }

        System.out.printf("Grammar Level Complete. Mastery Score: %d/%d%n", score, challenges.length);
    }

    private String findTranslationFor(String englishPhrase) {
        DefaultLanguageManager mgr = (DefaultLanguageManager) languageManager;
        for (int i = 0; i < mgr.getPhraseCount(); i++) {
            if (mgr.getEnglishPhrase(i).equalsIgnoreCase(englishPhrase)) {
                return mgr.getPhrase(targetLanguage.getCode(), i);
            }
        }
        return "Unknown";
    }
}