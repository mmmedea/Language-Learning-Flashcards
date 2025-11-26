package languageflashcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LevelTwoHandler extends LevelHandler {

    private static final int QUESTIONS_PER_SESSION = 5;

    public LevelTwoHandler(LanguageManager languageManager, Language targetLanguage) {
        super(languageManager, targetLanguage);
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("\n=== LEVEL 2: Phrase Challenge ===");
        System.out.println("Translate the common phrases into " + targetLanguage.getDisplayName() + ".");
        System.out.println("Note: Precision matters!");
        
        DefaultLanguageManager mgr = (DefaultLanguageManager) languageManager;
        int totalPhrases = mgr.getPhraseCount();

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < totalPhrases; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        int limit = Math.min(QUESTIONS_PER_SESSION, totalPhrases);
        int score = 0;

        for (int i = 0; i < limit; i++) {
            int idx = indices.get(i);
            String englishPhrase = mgr.getEnglishPhrase(idx);
            String correctPhrase = mgr.getPhrase(targetLanguage.getCode(), idx);

            System.out.println("\nPhrase: \"" + englishPhrase + "\"");
            System.out.print("Your translation: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) break;

            if (input.equalsIgnoreCase(correctPhrase)) {
                System.out.println("✅ Perfect!");
                score++;
            } else {
                System.out.println("❌ Incorrect."); 
                System.out.println("Correct: " + correctPhrase);
            }
        }
        
        System.out.printf("\nLevel 2 Complete. Score: %d/%d%n", score, limit);
    }
}