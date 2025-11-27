package languageflashcards;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DefaultLanguageManager implements LanguageManager {

    private static final String VOCAB_CSV = "data/vocab.csv";
    private static final String PHRASES_CSV = "data/phrases.csv";

    private final Language[] languages = {
            new Language("fil", "Filipino"),
            new Language("es", "Spanish"),
            new Language("fr", "French"),
            new Language("jp", "Japanese"),
            new Language("kr", "Korean")
    };

    private final List<String[]> vocab = new ArrayList<>();
    private final List<String[]> phrases = new ArrayList<>();

    public DefaultLanguageManager() {
        loadCsv(VOCAB_CSV, vocab);
        loadCsv(PHRASES_CSV, phrases);
    }

    private void loadCsv(String path, List<String[]> target) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

            String line = br.readLine(); // header
            if (line == null) {
                throw new RuntimeException("CSV file empty: " + path);
            }

            // read each subsequent line
            while ((line = br.readLine()) != null) {
                // Simple split on comma — CSV content created here avoids embedded commas.
                String[] cells = line.split(",", -1);

                // Expect exactly 6 columns: english,filipino,spanish,french,japanese,korean
                if (cells.length < 6) {
                    // If some lines accidentally have fewer cells, pad them with empty strings.
                    String[] padded = new String[6];
                    for (int i = 0; i < 6; i++) {
                        padded[i] = i < cells.length ? cells[i].trim() : "";
                    }
                    target.add(padded);
                } else {
                    // Trim whitespace for each cell
                    for (int i = 0; i < cells.length; i++) {
                        cells[i] = cells[i].trim();
                    }
                    // Only take first 6 columns if there are extras
                    if (cells.length > 6) {
                        String[] firstSix = new String[6];
                        System.arraycopy(cells, 0, firstSix, 0, 6);
                        target.add(firstSix);
                    } else {
                        target.add(cells);
                    }
                }
            }

        } catch (Exception e) {

            throw new RuntimeException("Failed to load CSV '" + path + "'. Make sure the file exists and is UTF-8 encoded. Error: " + e.getMessage(), e);
        }
    }

    @Override
    public Language[] getAvailableLanguages() {
        return languages;
    }

    @Override
    public String getWord(String languageCode, int index) {
        if (index < 0 || index >= vocab.size()) {
            throw new IndexOutOfBoundsException("Vocabulary index out of range: " + index);
        }
        String[] row = vocab.get(index);
        switch (languageCode) {
            case "fil":
                return row[1];
            case "es":
                return row[2];
            case "fr":
                return row[3];
            case "jp":
                return row[4];
            case "kr":
                return row[5];
            default:
                throw new IllegalArgumentException("Unsupported language code: " + languageCode);
        }
    }

    @Override
    public int getWordCount() {
        return vocab.size();
    }

    public String getEnglishWord(int index) {
        if (index < 0 || index >= vocab.size()) {
            throw new IndexOutOfBoundsException("Vocabulary index out of range: " + index);
        }
        return vocab.get(index)[0];
    }

    // ---------------- Phrase APIs ----------------

    /**
     * Returns how many phrases are available.
     */
    public int getPhraseCount() {
        return phrases.size();
    }

    public String getPhrase(String languageCode, int index) {
        if (index < 0 || index >= phrases.size()) {
            throw new IndexOutOfBoundsException("Phrase index out of range: " + index);
        }
        String[] row = phrases.get(index);
        switch (languageCode) {
            case "fil":
                return row[1];
            case "es":
                return row[2];
            case "fr":
                return row[3];
            case "jp":
                return row[4];
            case "kr":
                return row[5];
            case "en":
                return row[0];
            default:
                throw new IllegalArgumentException("Unsupported language code: " + languageCode);
        }
    }

    public String getEnglishPhrase(int index) {
        if (index < 0 || index >= phrases.size()) {
            throw new IndexOutOfBoundsException("Phrase index out of range: " + index);
        }
        return phrases.get(index)[0];
    }

    public String[] getVocabRow(int index) {
        if (index < 0 || index >= vocab.size()) {
            throw new IndexOutOfBoundsException("Vocabulary index out of range: " + index);
        }
        return vocab.get(index).clone();
    }

    public String[] getPhraseRow(int index) {
        if (index < 0 || index >= phrases.size()) {
            throw new IndexOutOfBoundsException("Phrase index out of range: " + index);
        }
        return phrases.get(index).clone();
    }
}