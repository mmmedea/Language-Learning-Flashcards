package languageflashcards;

import java.util.Scanner;
import java.util.InputMismatchException;

public class LanguageLearningFlashcardsUI {

    private static final String RESET = "\u001B[0m";
    private static final String VIOLET = "\u001B[35m";       
    private static final String BOLD_VIOLET = "\u001B[1;35m"; 
    private static final String WHITE = "\u001B[37m";
    private static final String BOLD_WHITE = "\u001B[1;37m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    
    private static final String PROMPT = BOLD_VIOLET + "➜ " + RESET;
    private static final int TYPING_SPEED = 30; 

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
        
        // Animated intro
        printAnimated(BOLD_WHITE + "Initializing system..." + RESET);
        showLoadingBar();
        
        String playerName = askForName();
        System.out.println();
        printAnimated(BOLD_WHITE + "Welcome, " + BOLD_VIOLET + playerName + BOLD_WHITE + ". System ready." + RESET);
        pause(1000);

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
                        printAnimated(VIOLET + "Shutting down system... Goodbye, " + playerName + "!" + RESET);
                        running = false;
                        break;
                    default:
                        printError("Invalid option. Try again.");
                        pause(1000);
                }
            } catch (InputMismatchException e) {
                printError("Please enter a number.");
                scanner.nextLine(); 
                pause(1000);
            } catch (Exception e) {
                printError("System Error: " + e.getMessage());
                pause(2000);
            }
        }
    }

    // --- MENUS ---

    private void showMainMenu(String name) {
        printHeader("MAIN MENU");
        System.out.println(VIOLET + "User: " + WHITE + name + RESET);
        System.out.println();
        System.out.println(BOLD_VIOLET + " [1] " + WHITE + "Start New Session");
        System.out.println(BOLD_VIOLET + " [2] " + WHITE + "Exit");
        System.out.println();
        printFooter();
        System.out.print(PROMPT);
    }

    private void startSession() {
        Language selectedLanguage = chooseLanguage();
        if (selectedLanguage == null) return;

        int levelChoice = chooseLevel();
        if (levelChoice == -1) return;

        clearScreen();
        System.out.println(BOLD_VIOLET + "Target: " + WHITE + selectedLanguage.getDisplayName());
        System.out.println(BOLD_VIOLET + "Mode:   " + WHITE + "Level " + levelChoice);
        System.out.println();
        
        printAnimated("Loading modules...");
        showLoadingBar();
        
        LevelHandler handler = levelFactory.createLevelHandler(levelChoice, selectedLanguage);
        
        if (handler != null) {
            clearScreen();
            handler.run(scanner);
            System.out.println(VIOLET + "\nPress Enter to return to Main Menu..." + RESET);
            scanner.nextLine();
        } else {
            printError("Level load failure.");
        }
    }

    private Language chooseLanguage() {
        clearScreen();
        printHeader("SELECT LANGUAGE");
        Language[] languages = languageManager.getAvailableLanguages();
        
        for (int i = 0; i < languages.length; i++) {
            System.out.println(BOLD_VIOLET + " [" + (i + 1) + "] " + WHITE + languages[i].getDisplayName());
        }
        System.out.println(BOLD_VIOLET + " [" + (languages.length + 1) + "] " + WHITE + "Back");
        
        System.out.println();
        printFooter();
        System.out.print(PROMPT);

        int input = readInt();
        if (input > 0 && input <= languages.length) {
            return languages[input - 1];
        } else if (input == languages.length + 1) {
            return null;
        } else {
            printError("Invalid selection.");
            pause(1000);
            return null;
        }
    }

    private int chooseLevel() {
        clearScreen();
        printHeader("SELECT DIFFICULTY");
        System.out.println(BOLD_VIOLET + " [1] " + WHITE + "Vocabulary " + VIOLET + "(Words)");
        System.out.println(BOLD_VIOLET + " [2] " + WHITE + "Phrases    " + VIOLET + "(Sentences)");
        System.out.println(BOLD_VIOLET + " [3] " + WHITE + "Grammar    " + VIOLET + "(Mechanics)");
        System.out.println(BOLD_VIOLET + " [4] " + WHITE + "Back");
        
        System.out.println();
        printFooter();
        System.out.print(PROMPT);

        int input = readInt();
        if (input >= 1 && input <= 3) return input;
        return -1;
    }

    private String askForName() {
        System.out.println();
        printAnimated(VIOLET + "Identify yourself:" + RESET);
        System.out.print(PROMPT);
        String name = scanner.nextLine().trim();
        return name.isEmpty() ? "Guest" : name;
    }


    private void printHeader(String title) {
        System.out.println(BOLD_VIOLET + "╔════════════════════════════════════════╗" + RESET);
        // Center the title manually for fixed width
        int width = 40;
        int padding = (width - title.length()) / 2;
        String fmt = "║%" + padding + "s%s%" + (width - padding - title.length()) + "s║";
        System.out.printf(VIOLET + fmt + "%n" + RESET, "", title, "");
        System.out.println(BOLD_VIOLET + "╠════════════════════════════════════════╣" + RESET);
    }

    private void printFooter() {
        System.out.println(BOLD_VIOLET + "╚════════════════════════════════════════╝" + RESET);
    }

    private void printBanner() {
        System.out.println(VIOLET + "    __    _                           ");
        System.out.println("   / /   (_)___  ____ ___  ______     ");
        System.out.println("  / /   / / __ \\/ __ `/ / / / __ \\    ");
        System.out.println(" / /___/ / / / / /_/ / /_/ / /_/ /    ");
        System.out.println("/_____/_/_/ /_/\\__, /\\__,_/\\____/     ");
        System.out.println("              /____/                  " + RESET);
        System.out.println(BOLD_WHITE + "   LANGUAGE LEARNING FLASHCARDS v1.0  " + RESET);
        System.out.println(VIOLET + "======================================" + RESET);
    }

    private void printAnimated(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(TYPING_SPEED);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    private void showLoadingBar() {
        System.out.print(VIOLET + "[");
        for (int i = 0; i < 20; i++) {
            System.out.print("█");
            try {
                Thread.sleep(40); // speed of loading
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("] Done." + RESET);
        pause(300);
    }

    private void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void printError(String msg) {
        System.out.println(RED + ">> ERROR: " + msg + RESET);
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