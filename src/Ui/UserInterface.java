package Ui;

import Members.Member;
import Members.CompetitionSwimmer;
import Models.Controller;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in); // Final scanner to use in the UI.
    private boolean running = true;
    Controller controller = new Controller();

    public void startProgram() {
        System.out.println("Welcome to the Dolphin swimming club!");
        while (running) {
            printMainMenu();
            String input = scanner.nextLine().toLowerCase();
            handleMainMenu(input);
        }
    }

    // Prints the main menu
    private void printMainMenu() {
        System.out.println("\nPlease choose what profile you want to enter:");
        System.out.println("1. Dolphin leader");
        System.out.println("2. Dolphin treasurer");
        System.out.println("3. Dolphin coach");
        System.out.println("Type 'exit' to quit the program.");
    }

    // Handles user input in the main menu
    private void handleMainMenu(String input) {
        switch (input) {
            case "1", "leader" -> leaderMenu();
            case "2", "treasurer" -> treasurerMenu();
            case "3", "coach" -> coachMenu();
            case "exit" -> exitProgram();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    // Menu for the leader role
    private void leaderMenu() {
        System.out.println("\nYou are logged in as the leader of the Dolphin swimming club.");
        while (true) {
            printLeaderMenu();
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("back")) break;
            handleLeaderMenu(input);
        }
    }

    // Prints the leader menu options
    private void printLeaderMenu() {
        System.out.println("\nYour options are:");
        System.out.println("1. Add member");
        System.out.println("2. Remove member");
        System.out.println("3. Edit member");
        System.out.println("4. View member list");
        System.out.println("Type 'back' to return to the main menu.");
    }

    // Handles user input in the leader menu
    private void handleLeaderMenu(String input) {
        switch (input) {
            case "1", "add" -> addMember();
            case "2", "remove" -> removeMember();
            case "3", "edit" -> editMember();
            case "4", "list" -> viewMembers();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }


    // Menu for the treasurer role
    private void treasurerMenu() {
        System.out.println("\nYou are logged in as the treasurer of the Dolphin swimming club.");
        while (true) {
            printTreasurerMenu();
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("back")) break;
            handleTreasurerMenu(input);
        }
    }

    // Prints the treasurer menu options
    private void printTreasurerMenu() {
        System.out.println("\nYour options are:");
        System.out.println("1. Calculate yearly income");
        System.out.println("2. Overview");
        System.out.println("3. View unpaid members");
        System.out.println("Type 'back' to return to the main menu.");
    }

    // Handles user input in the treasurer menu
    private void handleTreasurerMenu(String input) {
        switch (input) {
            case "1", "calculate" -> displayTotalIncome();
            case "2", "overview" -> displayMembersOverview();
            case "3", "view" -> viewUnpaidMembers();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    // Menu for the coach
    private void coachMenu() {
        System.out.println("\nYou are logged in as the coach of the Dolphin swimming club.");
        while (true) {
            printCoachMenu();
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("back")) break;
            handleCoachMenu(input);
        }
    }

    private void printCoachMenu() {
        System.out.println("\nYour options are:");
        System.out.println("1. Joakim");
        System.out.println("2. Sara");
        System.out.println("Type 'back' to return to the main menu.");
    }

    private void handleCoachMenu(String input) {
        switch (input) {
            case "1", "joakim" -> joakim();
            case "2", "sara" -> sara();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    // Specific methods for different actions
    private void addMember() {
        System.out.println("Is the swimmer competitive? (yes/no)");
        String competitiveInput = scanner.nextLine().toLowerCase();

        if (competitiveInput.equals("yes")) {
            // Add competitive swimmer
            System.out.print("Name: ");
            String name = scanner.nextLine().toLowerCase();
            ;

            String status = promptForValidStatus(scanner);

            int age = promptForValidAge(scanner);

            double breastTime = promptForValidTime(scanner, "Breaststroke");
            double crawlTime = promptForValidTime(scanner, "Crawl");
            double backCrawlTime = promptForValidTime(scanner, "Back Crawl");
            double butterflyTime = promptForValidTime(scanner, "Butterfly");

            controller.addCompetitive(name, status, age, breastTime, crawlTime, backCrawlTime, butterflyTime);
            controller.saveCompSwimmerToList();
            System.out.println("Competitive swimmer added successfully!");

        } else if (competitiveInput.equals("no")) {
            // Add workout swimmer
            System.out.print("Name: ");
            String name = scanner.nextLine();

            String status = promptForValidStatus(scanner);

            int age = promptForValidAge(scanner);

            controller.addWorkout(name, status, age);
            controller.saveWorkoutSwimmersToList();
            System.out.println("Workout swimmer added successfully!");
        } else {
            System.out.println("Invalid input. Returning to menu.");
        }
    }

    private double promptForValidTime(Scanner scanner, String discipline) {
        double time = -1; // Initialize with an invalid value
        while (time < 0) {
            System.out.print("Enter " + discipline + " time: ");
            String input = scanner.nextLine().trim();
            try {
                time = Double.parseDouble(input);
                if (time < 0) { // Time must be non-negative
                    System.out.println("Time must be a non-negative number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid time.");
            }
        }
        return time;
    }

    private String promptForValidStatus(Scanner scanner) {
        String status;
        while (true) {
            System.out.print("Status (Passive/Active): ");
            status = scanner.nextLine().toLowerCase();
            if (status.equals("passive") || status.equals("active")) {
                break; // Valid input, exit the loop
            } else {
                System.out.println("Please enter either 'Passive' or 'Active'.");
            }
        }
        return status;
    }

    private int promptForValidAge(Scanner scanner) {
        int age = -1; // Start with an invalid value
        while (age < 0) { // Keep prompting until a valid age is entered
            System.out.print("Age: ");
            String input = scanner.nextLine().trim(); // Read user input
            try {
                age = Integer.parseInt(input); // Attempt to parse input to an integer
                if (age < 0) { // Check if the age is negative
                    System.out.println("Age must be a positive number. Please try again.");
                }
            } catch (NumberFormatException e) { // Handle invalid (non-integer) input
                System.out.println("Invalid input. Please enter a valid age.");
            }
        }
        return age; // Return the valid age
    }


    private void removeMember() {
        System.out.println("Enter the name of the member to remove:");
        String name = scanner.nextLine();
        if (controller.removeMember(name)) {
            System.out.println(name + " has been successfully removed.");
        } else {
            System.out.println("Member not found.");
        }
    }

    private void editMember() {
        System.out.println("Would you like to edit a Competitive swimmer or a Workout swimmer?");
        String type = scanner.nextLine().toLowerCase();

        if (type.equals("competitive")) {
            editCompMember(); // Call the method for competitive swimmers
        } else if (type.equals("workout")) {
            editWorkMember(); // Call the method for workout swimmers
        } else {
            System.out.println("Invalid type.");
        }
    }

    private void editWorkMember() {
        System.out.println("Type the name of the member you want to edit:");
        String name = scanner.nextLine();
        Member memberEdit = controller.editWorkoutMembers(name);

        if (memberEdit == null) {
            System.out.println("Member not found. Please try again.");
            return;
        }

        System.out.println("What do you want to edit?\nOptions: all, name, status, age");
        String editChoice = scanner.nextLine().toLowerCase();

        switch (editChoice) {
            case "all" -> {
                System.out.print("Name: ");
                String nameInput = scanner.nextLine();
                memberEdit.setName(nameInput);

                String status = promptForValidStatus(scanner);
                memberEdit.setStatus(status);

                int age = promptForValidAge(scanner);
                memberEdit.setAge(age);
            }
            case "name" -> {
                System.out.print("New name: ");
                String newName = scanner.nextLine();
                memberEdit.setName(newName);
            }
            case "status" -> {
                String status = promptForValidStatus(scanner);
                memberEdit.setStatus(status);
            }
            case "age" -> {
                int age = promptForValidAge(scanner);
                memberEdit.setAge(age);
            }
            default -> System.out.println("Invalid option. Please try again.");
        }

        // Update fee and save changes
        controller.changeFee(memberEdit);
        controller.saveWorkoutSwimmersToList();
        System.out.println("Member details have been updated successfully.");
    }

    private void editCompMember() {
        System.out.println("Type the name of the member you want to edit:");
        String name = scanner.nextLine();
        Member memberEdit = controller.editCompMember(name);

        if (memberEdit == null) {
            System.out.println("Member not found. Please try again.");
            return;
        }

        System.out.println("What do you want to edit?\nOptions: all, name, status, age, breaststroke, crawl, back crawl, butterfly");
        String editChoice = scanner.nextLine().toLowerCase();

        switch (editChoice) {
            case "all" -> {
                System.out.print("Name: ");
                String nameInput = scanner.nextLine();
                memberEdit.setName(nameInput);

                String status = promptForValidStatus(scanner);
                memberEdit.setStatus(status);

                int age = promptForValidAge(scanner);
                memberEdit.setAge(age);

                if (memberEdit instanceof CompetitionSwimmer compSwimmer) {
                    compSwimmer.setBreastTime(promptForValidTime(scanner, "New breaststroke time: "));
                    compSwimmer.setCrawlTime(promptForValidTime(scanner, "New crawl time: "));
                    compSwimmer.setBackCrawlTime(promptForValidTime(scanner, "New back crawl time: "));
                    compSwimmer.setButterflyTime(promptForValidTime(scanner, "New butterfly time: "));
                }
            }
            case "name" -> {
                System.out.print("New name: ");
                String newName = scanner.nextLine();
                memberEdit.setName(newName);
            }
            case "status" -> {
                String status = promptForValidStatus(scanner);
                memberEdit.setStatus(status);
            }
            case "age" -> {
                int age = promptForValidAge(scanner);
                memberEdit.setAge(age);
            }
            case "breaststroke" -> updateSwimTime(memberEdit, "breaststroke");
            case "crawl" -> updateSwimTime(memberEdit, "crawl");
            case "back crawl" -> updateSwimTime(memberEdit, "back crawl");
            case "butterfly" -> updateSwimTime(memberEdit, "butterfly");
            default -> System.out.println("Invalid option. Please try again.");
        }

        // Update fee and save changes
        controller.changeFee(memberEdit);
        controller.saveCompSwimmerToList();
        System.out.println("Member details have been updated successfully.");
    }

    private void updateSwimTime(Member member, String discipline) {
        if (member instanceof CompetitionSwimmer compSwimmer) {
            double time = promptForValidTime(scanner, "New " + discipline + " time: ");
            switch (discipline) {
                case "breaststroke" -> compSwimmer.setBreastTime(time);
                case "crawl" -> compSwimmer.setCrawlTime(time);
                case "back crawl" -> compSwimmer.setBackCrawlTime(time);
                case "butterfly" -> compSwimmer.setButterflyTime(time);
            }
        } else {
            System.out.println("This member is not a competition swimmer.");
        }
    }


    private void viewMembers() {
        System.out.println("1. Competitive");
        System.out.println("2. Workout");
        String type = scanner.nextLine().toLowerCase();

        if (type.equals("competitive") || type.equals("1") || type.contains("comp")) {
            controller.loadCompSwimmerFromList();
            System.out.println(controller.getMembers());
        } else if (type.equals("workout") || type.equals("2") || type.contains("work")) {
            controller.loadWorkoutSwimmersFromList();
            System.out.println(controller.getMembers());
        } else {
            System.out.println("Invalid list type.");
        }
    }

    private void displayTotalIncome() {
        int totalFees = controller.calculateAllFees(); // Assuming this method returns the total fees
        System.out.println("\n--- Total income this year from fees: ---\n" + totalFees + "\n");
    }

    private void displayMembersOverview() {
        String allMembersOverview = controller.Overview(); // Assuming this method returns an overview string
        if (allMembersOverview.isEmpty()) {
            System.out.println("No members found in the system.");
        } else {
            System.out.println("\n--- Members Overview ---\n");
            System.out.println(allMembersOverview);
        }
    }

    private void viewUnpaidMembers() {
        System.out.println("Viewing unpaid members (not fully implemented).");
        // Implement logic for viewing members who have not paid their fee.
    }

    private void joakim() {
        System.out.println("Viewing Joakim's options (not fully implemented).");
        // Implement options for coach Joakim
    }

    private void sara() {
        System.out.println("Viewing Sara's options (not fully implemented).");
        // Implement options for coach Sara
    }


    private void exitProgram() {
        System.out.println("Exiting the program. Goodbye!");
        running = false; // Stops the main program loop
    }
}