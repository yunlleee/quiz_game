package quiz;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println(Colors.CYAN + "*************************************");
            System.out.println("*           QUIZ GAME (2P)          *");
            System.out.println("*************************************" + Colors.RESET);

            int choice;
            do {
                System.out.println(Colors.YELLOW + "1. Start Quiz");
                System.out.println("2. Exit" + Colors.RESET);

                System.out.print("Choose an option: ");

                while (!input.hasNextInt()) {
                    System.out.println(Colors.RED + "Invalid input. Please enter a number." + Colors.RESET);
                    input.next();
                    System.out.print("Choose an option: ");
                }
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        new QuizGame(input).start();
                        break;
                    case 2:
                        System.out.println(Colors.GREEN + "Thanks for playing! Goodbye :)" + Colors.RESET);
                        break;
                    default:
                        System.out.println(Colors.RED + "Invalid option. Try again." + Colors.RESET);
                }
            } while (choice != 2);
        }
    }
}
