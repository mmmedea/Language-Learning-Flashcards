package languageflashcards;

import java.util.Scanner;
import java.util.InputMismatchException;

public class LanguageLearningFlashcardsUI {

    private static final String RESET = "\u001B[0m";
    private static final String PRIMARY = "\u001B[35m";       
    private static final String PRIMARY_BOLD = "\u001B[1;35m";
    private static final String TEXT = "\u001B[37m";          
    private static final String TEXT_BOLD = "\u001B[1;37m";   
    private static final String ERROR = "\u001B[31m";         
    private static final String SUCCESS = "\u001B[32m";       
    
    private static final String PROMPT = PRIMARY_BOLD + " ➤ " + RESET;
    private static final int TYPE_SPEED = 20; 

    private final Scanner scanner;
    private final LanguageManager languageManager;
    private final LevelFactory levelFactory;

    public LanguageLearningFlashcardsUI(LanguageManager languageManager, LevelFactory levelFactory) {
        this.scanner = new Scanner(System.in);
        this.languageManager = languageManager;
        this.levelFactory = levelFactory;
    }

    public void start() {
        clearScreen();
        printBanner();
        
        printAnimated(TEXT + "  System Initialization...", 30);
        showProgressBar();
        System.out.println();
        
        String playerName = askForName();
        System.out.println();
        printAnimated(TEXT_BOLD + "  Welcome, " + PRIMARY_BOLD + playerName + TEXT_BOLD + "to Language Learning Flashcards :>." + RESET, 20);
        pause(800);

        boolean running = true;
        while (running) {
            clearScreen();
            try {
                showMainMenu(playerName);
                int choice = readInt();

                switch (choice) {
                    case 1:
                        startSession();
                        break;
                    case 2:
                        System.out.println();
                        printAnimated(PRIMARY + "  TThank you for playing. See you next time!, " + playerName + "." + RESET, 30);
                        running = false;
                        break;
                    default:
                        printError("Invalid selection.");
                        pause(1000);
                }
            } catch (InputMismatchException e) {
                printError("Please input a number.");
                scanner.nextLine(); 
                pause(1000);
            } catch (Exception e) {
                printError("System Error: " + e.getMessage());
                pause(2000);
            }
        }
    }


    private void showMainMenu(String name) {
        printBoxTop("MAIN MENU");
        System.out.printf(PRIMARY + "║" + RESET + "  User: %-34s" + PRIMARY + "║%n" + RESET, TEXT_BOLD + name + RESET);
        printBoxSeparator();
        System.out.println(PRIMARY + "║" + RESET + "  [1] " + TEXT + "Start New Session" + getPadding(19) + PRIMARY + "║" + RESET);
        System.out.println(PRIMARY + "║" + RESET + "  [2] " + TEXT + "Exit Application" + getPadding(20) + PRIMARY + "║" + RESET);
        printBoxBottom();
        System.out.print(PROMPT);
    }

    private void startSession() {
        Language selectedLanguage = chooseLanguage();
        if (selectedLanguage == null) return;

        int levelChoice = chooseLevel();
        if (levelChoice == -1) return;

        clearScreen();
        printBoxTop("SESSION CONFIGURATION");
        System.out.printf(PRIMARY + "║" + RESET + "  Target: %-32s" + PRIMARY + "║%n" + RESET, TEXT_BOLD + selectedLanguage.getDisplayName() + RESET);
        System.out.printf(PRIMARY + "║" + RESET + "  Level:  %-32s" + PRIMARY + "║%n" + RESET, TEXT_BOLD + String.valueOf(levelChoice) + RESET);
        printBoxBottom();
        System.out.println();
        
        printAnimated(TEXT + "  Loading assets...", 20);
        showProgressBar();
        
        LevelHandler handler = levelFactory.createLevelHandler(levelChoice, selectedLanguage);
        
        if (handler != null) {
            clearScreen();
            handler.run(scanner);
            
            System.out.println();
            System.out.println(PRIMARY + "  [PRESS ENTER TO RETURN]" + RESET);
            scanner.nextLine();
        } else {
            printError("Module failed to load.");
        }
    }

    private Language chooseLanguage() {
        clearScreen();
        Language[] languages = languageManager.getAvailableLanguages();
        
        printBoxTop("SELECT LANGUAGE");
        for (int i = 0; i < languages.length; i++) {
            String label = "  [" + (i + 1) + "] " + languages[i].getDisplayName();
            System.out.printf(PRIMARY + "║" + RESET + "%-40s" + PRIMARY + "║%n" + RESET, TEXT + label);
        }
        printBoxSeparator();
        System.out.printf(PRIMARY + "║" + RESET + "%-40s" + PRIMARY + "║%n" + RESET, TEXT + "  [" + (languages.length + 1) + "] Back");
        printBoxBottom();
        
        System.out.print(PROMPT);

        int input = readInt();
        if (input > 0 && input <= languages.length) {
            return languages[input - 1];
        } else if (input == languages.length + 1) {
            return null;
        }
        printError("Invalid option.");
        pause(1000);
        return null;
    }

    private int chooseLevel() {
        clearScreen();
        printBoxTop("SELECT DIFFICULTY");
        System.out.printf(PRIMARY + "║" + RESET + "%-40s" + PRIMARY + "║%n" + RESET, TEXT + "  [1] Vocabulary " + PRIMARY + "(Words)");
        System.out.printf(PRIMARY + "║" + RESET + "%-40s" + PRIMARY + "║%n" + RESET, TEXT + "  [2] Phrases    " + PRIMARY + "(Sentences)");
        System.out.printf(PRIMARY + "║" + RESET + "%-40s" + PRIMARY + "║%n" + RESET, TEXT + "  [3] Grammar    " + PRIMARY + "(Mechanics)");
        printBoxSeparator();
        System.out.printf(PRIMARY + "║" + RESET + "%-40s" + PRIMARY + "║%n" + RESET, TEXT + "  [4] Back");
        printBoxBottom();
        
        System.out.print(PROMPT);

        int input = readInt();
        if (input >= 1 && input <= 3) return input;
        return -1;
    }

    private String askForName() {
        System.out.println();
        printAnimated(PRIMARY + "  Enter your name:" + RESET, 20);
        System.out.print(PROMPT);
        String name = scanner.nextLine().trim();
        return name.isEmpty() ? "Guest" : name;
    }


    private void printBoxTop(String title) {
        System.out.println(PRIMARY + "╔════════════════════════════════════════╗" + RESET);
        int width = 40;
        int paddingLeft = (width - title.length()) / 2;
        int paddingRight = width - paddingLeft - title.length();
        System.out.printf(PRIMARY + "║" + RESET + "%s" + PRIMARY_BOLD + "%s" + RESET + "%s" + PRIMARY + "║%n" + RESET, 
            " ".repeat(paddingLeft), title, " ".repeat(paddingRight));
        printBoxSeparator();
    }

    private void printBoxSeparator() {
        System.out.println(PRIMARY + "╠════════════════════════════════════════╣" + RESET);
    }

    private void printBoxBottom() {
        System.out.println(PRIMARY + "╚════════════════════════════════════════╝" + RESET);
    }

    private void printBanner() {
        System.out.println(PRIMARY + "==========================================" + RESET);
        System.out.println(PRIMARY_BOLD + "      LANGUAGE LEARNING FLASHCARDS" + RESET);
        System.out.println(TEXT + "      Interactive Grammar & Vocabulary" + RESET);
        System.out.println(PRIMARY + "==========================================" + RESET);
    }

    private String getPadding(int length) {
        return " ".repeat(length);
    }

    // --- ANIMATIONS ---

    private void printAnimated(String text, int speed) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(speed); } catch (Exception e) {}
        }
        System.out.println();
    }

    private void showProgressBar() {
        System.out.print(PRIMARY + "  [");
        for (int i = 0; i < 25; i++) {
            System.out.print("▓");
            try { Thread.sleep(30); } catch (Exception e) {}
        }
        System.out.println("] " + SUCCESS + "Done" + RESET);
        pause(300);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // Fallback for IDEs that don't support ANSI clear
        for(int i=0; i<50; i++) System.out.println(); 
    }

    private void pause(int millis) {
        try { Thread.sleep(millis); } catch (Exception e) {}
    }

    private void printError(String msg) {
        System.out.println(ERROR + "  ✖ " + msg + RESET);
    }

    private int readInt() {
        try {
            int i = scanner.nextInt();
            scanner.nextLine();
            return i;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw e;
        }
    }
}