package languageflashcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LevelOneHandler extends LevelHandler {

    private static final int QUESTIONS_PER_SESSION = 10;

    public LevelOneHandler(LanguageManager languageManager, Language targetLanguage) {
        super(languageManager, targetLanguage);
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("\n=== LEVEL 1: Vocabulary Practice ===");
        System.out.println("Translate the following words into " + targetLanguage.getDisplayName() + ".");
        System.out.println("Type 'EXIT' to return to the menu early.\n");

        int totalWords = languageManager.getWordCount();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < totalWords; i++) {
            indices.add(i);
        }
        
        Collections.shuffle(indices);

        int limit = Math.min(QUESTIONS_PER_SESSION, totalWords);
        int score = 0;

        for (int i = 0; i < limit; i++) {
            int wordIndex = indices.get(i);
            String englishWord = ((DefaultLanguageManager) languageManager).getEnglishWord(wordIndex);
            String correctTranslation = languageManager.getWord(targetLanguage.getCode(), wordIndex);

            System.out.printf("Q%d: %s -> ", (i + 1), englishWord);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting level...");
                break;
            }

            boolean isCorrect = false;
            String[] possibleAnswers = correctTranslation.split("/");
            
            for (String ans : possibleAnswers) {
                
                if (input.equalsIgnoreCase(ans.trim())) {
                    isCorrect = true;
                    break;
                }
            }

            if (isCorrect) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Incorrect. The answer was: " + correctTranslation);
            }
        }

        System.out.println("\n------------------------------------------------");
        System.out.printf("Session Complete! Final Score: %d / %d%n", score, limit);
        System.out.println("------------------------------------------------");
    }
}