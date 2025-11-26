package languageflashcards;

public class Language {

    private final String code;
    private final String displayName;

    /**
     * Constructs a Language object.
     *
     * @param code unique identifier (lowercase, no spaces)
     * @param displayName human-readable name for the UI
     */
    public Language(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /** Returns the language code. */
    public String getCode() {
        return code;
    }

    /** Returns the display name. */
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName + " (" + code + ")";
    }
}