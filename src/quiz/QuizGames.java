package quiz;

import java.util.Scanner;

public class QuizGame {
    private final Scanner input;
    private final int[] scores = new int[2];

    public QuizGame(Scanner input) {
        this.input = input;
    }

    public void start() {
        scores[0] = 0;
        scores[1] = 0;

        runMultipleChoice();
        runBlankQuestions();
        displayFinalScores();
    }

    private void runMultipleChoice() {
        for (int i = 0; i < QuestionBank.MC_QUESTIONS.length; i++) {
            for (int player = 0; player < 2; player++) {
                System.out.println(Colors.CYAN
                    + "\n[Player " + (player + 1) + "] Question " + (i + 1) + ": "
                    + QuestionBank.MC_QUESTIONS[i]
                    + Colors.RESET);

                for (String opt : QuestionBank.MC_OPTIONS[i]) {
                    System.out.println(opt);
                }

                int answer = readIntInRange("Enter your answer (1-4): ", 1, 4);

                if (answer == QuestionBank.MC_ANSWERS[i]) {
                    System.out.println(Colors.GREEN + "Correct!" + Colors.RESET);
                    scores[player]++;
                } else {
                    System.out.println(Colors.RED + "Wrong!" + Colors.RESET);
                }
            }
        }
    }

    private void runBlankQuestions() {
        for (int i = 0; i < QuestionBank.BLANK_QUESTIONS.length; i++) {
            for (int player = 0; player < 2; player++) {
                System.out.println(Colors.CYAN
                    + "\n[Player " + (player + 1) + "] Fill in the blank: "
                    + QuestionBank.BLANK_QUESTIONS[i]
                    + Colors.RESET);

                String answer = readWordLowerTrimmed("Your answer: ");

                if (answer.equals(QuestionBank.BLANK_ANSWERS[i])) {
                    System.out.println(Colors.GREEN + "Correct!" + Colors.RESET);
                    scores[player]++;
                } else {
                    System.out.println(Colors.RED + "Wrong!" + Colors.RESET);
                }
            }
        }
    }

    private int readIntInRange(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);

            while (!input.hasNextInt()) {
                System.out.println(Colors.RED + "Invalid input. Please enter a number." + Colors.RESET);
                input.next();
                System.out.print(prompt);
            }

            value = input.nextInt();
            if (value >= min && value <= max) return value;

            System.out.println(Colors.RED + "Invalid option. Please choose between " + min + " and " + max + "." + Colors.RESET);
        }
    }

    private String readWordLowerTrimmed(String prompt) {
        System.out.print(prompt);
        input.nextLine(); // consume leftover newline
        return input.next().trim().toLowerCase();
    }

    private void displayFinalScores() {
        int total = QuestionBank.MC_QUESTIONS.length + QuestionBank.BLANK_QUESTIONS.length;

        System.out.println(Colors.YELLOW + "\n--- Final Scores ---" + Colors.RESET);
        System.out.println("Player 1: " + scores[0] + " / " + total);
        System.out.println("Player 2: " + scores[1] + " / " + total);

        if (scores[0] > scores[1]) {
            System.out.println(Colors.GREEN + "Player 1 Wins! Congratulations." + Colors.RESET);
        } else if (scores[1] > scores[0]) {
            System.out.println(Colors.GREEN + "Player 2 Wins! Congratulations." + Colors.RESET);
        } else {
            System.out.println(Colors.CYAN + "It's a Draw!" + Colors.RESET);
        }
    }
}

