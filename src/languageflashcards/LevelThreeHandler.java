package languageflashcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LevelThreeHandler extends LevelHandler {

    private static final int QUESTIONS_TO_ASK = 3;

    public LevelThreeHandler(LanguageManager languageManager, Language targetLanguage) {
        super(languageManager, targetLanguage);
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("\n=== LEVEL 3: Grammar Mastery ===");
        System.out.println("Translate the following sentences into " + targetLanguage.getDisplayName() + ".");
        System.out.println("Pay attention to Capitalization and Punctuation!\n");

        DefaultLanguageManager mgr = (DefaultLanguageManager) languageManager;
        int totalPhrases = mgr.getPhraseCount();

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < totalPhrases; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        int score = 0;
        int limit = Math.min(QUESTIONS_TO_ASK, totalPhrases);

        for (int i = 0; i < limit; i++) {

            int index = indices.get(i);
            String englishPhrase = mgr.getEnglishPhrase(index);
            String correctTranslation = mgr.getPhrase(targetLanguage.getCode(), index);

            System.out.println("Translate: \"" + englishPhrase + "\"");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) break;

            boolean capCheck = !input.isEmpty() && Character.isUpperCase(input.charAt(0));

            boolean puncCheck = endsWithPunctuation(input);

            boolean transCheck = normalize(input).equalsIgnoreCase(normalize(correctTranslation));

            if (capCheck && puncCheck && transCheck) {
                System.out.println("🌟 Flawless! (Grammar + Translation correct)");
                score++;
            } else {
                System.out.println("⚠️ Needs Improvement:");
                
                if (!transCheck) {
                    System.out.println("   - Translation mismatch."); 
                    System.out.println("     Expected: " + correctTranslation);
                }
                
                // Only complain about grammar if the translation was actually correct
                if (transCheck) {
                    if (!capCheck) System.out.println("   - Start your sentence with a Capital letter.");
                    if (!puncCheck) System.out.println("   - End your sentence with proper punctuation (. ? !).");
                }
            }
            System.out.println(); // spacer
        }

        System.out.printf("Grammar Level Complete. Mastery Score: %d/%d%n", score, limit);
    }

    private String normalize(String text) {
        return text.replaceAll("[^a-zA-Z0-9\u3040-\u30ff\u3400-\u4dbf\u4e00-\u9fff\uac00-\ud7af]", "").toLowerCase();
    }

    private boolean endsWithPunctuation(String text) {
        if (text == null || text.isEmpty()) return false;
        char last = text.charAt(text.length() - 1);
        return last == '.' || last == '?' || last == '!' || last == '。' || last == '؟';
    }
}