package tracker;

import java.util.Scanner;

public class Controller {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        StudentInfoParser parser = new StudentInfoParser();
        String input = null;
        boolean flag = true;
        int studentsAdded = 0;

        System.out.println("Learning Progress Tracker");
        while (true) {
            if (!"back".equals(input)) {
                input = scanner.nextLine();
            }

            if (input.equals("") || input.isBlank()) {
                System.out.println("No input");
                continue;
            }
            switch (input) {
                case "exit" -> {
                    CommandExecutor.executeCommand(new ExitCommand());
                    scanner.close();
                    return;
                }
                case "add students" -> {
                    System.out.println("Enter student credentials or 'back' to return:");
                    while (true) {
                        input = scanner.nextLine();
                        if (input.equals("back")) {
                            System.out.printf("Total %d students have been added.%n", studentsAdded);
                            flag = false;
                            break;
                        }
                        if (input.split("\\s+").length < 3) {
                            System.out.println("Incorrect credentials.");
                            continue;
                        }
                        if (parser.parse(input)) {
                            studentsAdded++;
                        }
                    }
                }
                case "list" -> {
                    CommandExecutor.executeCommand(new ListCommand());
                }
                case "add points" -> {
                    System.out.println("Enter an id and points or 'back' to return:");
                    while (true) {
                        input = scanner.nextLine();
                        if (input.equals("back")) {
                            flag = false;
                            break;
                        }
                        String[] pointsInput = input.split("\\s+");
                        if (pointsInput.length != 5) {
                            System.out.println("Incorrect points format.");
                            continue;
                        }
                        try {
                            if (Integer.parseInt(pointsInput[1]) < 0 || Integer.parseInt(pointsInput[2]) < 0
                                    || Integer.parseInt(pointsInput[3]) < 0 || Integer.parseInt(pointsInput[4]) < 0) {
                                System.out.println("Incorrect points format.");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Incorrect points format.");
                            continue;
                        }
                        try {
                            if (UserDatabase.containsId(Integer.parseInt(pointsInput[0]))) {
                                CommandExecutor.executeCommand(new AddPointsCommand(Integer.valueOf(pointsInput[0]), pointsInput[1], pointsInput[2], pointsInput[3], pointsInput[4]));
                            } else {
                                System.out.printf("No student is found for id=%s%n", pointsInput[0]);
                            }
                        } catch (NumberFormatException e) {
                            System.out.printf("No student is found for id=%s%n", pointsInput[0]);
                        }
                    }
                }
                case "find" -> {
                    System.out.println("Enter an id or 'back' to return:");
                    while (true) {
                        input = scanner.nextLine();
                        if (input.equals("back")) {
                            break;
                        }

                        if (UserDatabase.containsId(Integer.parseInt(input))) {
                            CommandExecutor.executeCommand(new FindCommand(Integer.valueOf(input)));
                        } else {
                            System.out.printf("No student is found for id=%s%n", input);
                        }
                    }
                }

                case "statistics" -> {
                    System.out.println("Type the name of a course to see details or 'back' to quit:");
                    UserDatabase.printStatistics();
                    while (true) {
                        input = scanner.nextLine();
                        if (input.equals("back")) {
                            break;
                        }

                        if (UserDatabase.isCourseExist(input)) {
                            CommandExecutor.executeCommand(new StatisticsCommand(input));
                        } else {
                            System.out.println("Unknown course.");
                        }
                    }

                }

                case "notify" -> {
                    CommandExecutor.executeCommand(new NotifyCommand());
                }

                case "back" -> {
                    if (flag) {
                        System.out.println("Enter 'exit' to exit the program.");
                    }
                    flag = true;
                    input = null;

                }
                default -> System.out.println("Unknown command!");
            }

        }
    }
}
