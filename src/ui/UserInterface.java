package ui;

import domain.Member;
import domain.CompetitionSwimmer;
import services.Controller;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in); // Final scanner to use in the UI.
    private boolean running = true;
    Controller controller = new Controller();
    String exit = "";

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
            case "4", "list", "view" -> viewMembers();
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
        System.out.println("4. Change payment status of member");
        System.out.println("Type 'back' to return to the main menu.");
    }

    // Handles user input in the treasurer menu
    private void handleTreasurerMenu(String input) {
        switch (input) {
            case "1", "calculate" -> displayTotalIncome();
            case "2", "overview" -> displayTreasurersOverview();
            case "3", "view" -> viewUnpaidMembers();
            case "4", "change" -> changePaymentStatus();
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

    // Prints coach menu
    private void printCoachMenu() {
        System.out.println("\nYour options are:");
        System.out.println("1. Joakim (Senior)");
        System.out.println("2. Sara (Junior)");
        System.out.println("Type 'back' to return to the main menu.");
    }

    // Handles user input for coach menu
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

            String status = checkValidStatus(scanner);

            int age = checkForValidAge(scanner);

            controller.addCompetitive(name, status, age, 0.0, 0.0, 0.0, 0.0);
            controller.saveCompSwimmerToList();
            System.out.println("Competitive swimmer added successfully!");

        } else if (competitiveInput.equals("no")) {
            // Add workout swimmer
            System.out.print("Name: ");
            String name = scanner.nextLine();

            String status = checkValidStatus(scanner);

            int age = checkForValidAge(scanner);

            controller.addWorkout(name, status, age);
            controller.saveWorkoutSwimmersToList();
            System.out.println("Workout swimmer added successfully!");
        } else {
            System.out.println("Invalid input. Returning to menu.");
        }
    }

    // Checks if time is valid
    private double checkValidTime(Scanner scanner, String discipline) {
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

    // Checks if status is valid
    private String checkValidStatus(Scanner scanner) {
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

    // Checks if age is valid
    private int checkForValidAge(Scanner scanner) {
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


    // Removes member from list
    private void removeMember() {
        System.out.println("Enter the name of the member to remove:");
        String name = scanner.nextLine();
        if (controller.removeMember(name)) {
            System.out.println(name + " has been successfully removed.");
        } else {
            System.out.println("Member not found.");
        }
    }

    // Member type selection
    private void editMember() {
        System.out.println("Would you like to edit a (1)Competitive swimmer or a (2)Workout swimmer?");
        String type = scanner.nextLine().toLowerCase();

        if (type.contains("comp") || type.equals("1")) {
            editCompMember(); // Call the method for competitive swimmers
        } else if (type.contains("work") || type.equals("2")) {
            editWorkoutMember(); // Call the method for workout swimmers
        } else {
            System.out.println("Invalid type.");
        }
    }

    // Editing workout member method
    private void editWorkoutMember() {
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

                String status = checkValidStatus(scanner);
                memberEdit.setStatus(status);

                int age = checkForValidAge(scanner);
                memberEdit.setAge(age);
            }
            case "name" -> {
                System.out.print("New name: ");
                String newName = scanner.nextLine();
                memberEdit.setName(newName);
            }
            case "status" -> {
                String status = checkValidStatus(scanner);
                memberEdit.setStatus(status);
            }
            case "age" -> {
                int age = checkForValidAge(scanner);
                memberEdit.setAge(age);
            }
            default -> System.out.println("Invalid option. Please try again.");
        }

        // Update fee and save changes
        controller.changeFee(memberEdit);
        controller.saveWorkoutSwimmersToList();
        System.out.println("Member details have been updated successfully.");
    }

    // Edit competition member method
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

                String status = checkValidStatus(scanner);
                memberEdit.setStatus(status);

                int age = checkForValidAge(scanner);
                memberEdit.setAge(age);
            }
            case "name" -> {
                System.out.print("New name: ");
                String newName = scanner.nextLine();
                memberEdit.setName(newName);
            }
            case "status" -> {
                String status = checkValidStatus(scanner);
                memberEdit.setStatus(status);
            }
            case "age" -> {
                int age = checkForValidAge(scanner);
                memberEdit.setAge(age);
            }
        }

        // Update fee and save changes
        controller.changeFee(memberEdit);
        controller.saveCompSwimmerToList();
        System.out.println("Member details have been updated successfully.");
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

    // Displays sum of all fees
    private void displayTotalIncome() {
        int totalFees = controller.calculateAllFees(); // Assuming this method returns the total fees
        System.out.println("\n--- Total income this year from fees: ---\n" + totalFees + "\n");
    }

    // Shows all members and their payment status + fees.
    private void displayTreasurersOverview() {
        String allMembersOverview = controller.Overview(); // Assuming this method returns an overview string
        if (allMembersOverview.isEmpty()) {
            System.out.println("No members found in the system.");
        } else {
            System.out.println("\n--- Members Overview ---\n");
            System.out.println(allMembersOverview);
        }
    }

    // Shows all members which status are unpaid.
    private void viewUnpaidMembers() {

        System.out.println(controller.viewUNPAIDSwimmers());

    }

    // Changes from one payment status to the other
    private void changePaymentStatus() {
        System.out.println("Enter the name of the swimmer whom you want to change the payment status of: ");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        controller.changePaymentStatus(name);

        System.out.println("Status have been changed..");

    }

    // Menu for Joakim and user input
    private void joakim() {

        System.out.println("\nWelcome Joakim. " +
                "Here are your options: ");


        while (!exit.equalsIgnoreCase("back")) {

            System.out.println("1. See competition members. ");
            System.out.println("2. Edit competition members. ");
            System.out.println("3. See top 5 swimmers within your chosen discipline.");
            System.out.println("4. Register tournament: place and date. ");
            System.out.println("5. View all tournaments and results.");
            System.out.println("Type 'back' to return to the main menu.");

            //input for handling joakimMenu
            String input = scanner.nextLine();
            if (input.equals("back")) break;
            handleJoakimMenu(input);

        }

    }

    // Handles menu for Joakim
    private void handleJoakimMenu(String input) {

        switch (input) {
            case "1", "see" -> viewCompSenior();
            case "2", "edit" -> editCompSenior();
            case "3", "top" -> disciplinesJoakimMenu(input);
            case "4", "register" -> registerSeniorTournament();
            case "5", "view tournaments" -> System.out.println(controller.loadTournaments());
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    // Registers senior tournament
    private void registerSeniorTournament() {
        String tournamentName = checksForInput("Enter tournament name: ");
        String date = checksForInput("Enter date (dd/MM/yyyy): ");
        String discipline = checksForInput("Enter discipline (Breaststroke, Crawl, Back crawl, Butterfly): ");

        // Gather data for 5 swimmers.
        //2D array method found on https://www.w3schools.com/c/c_arrays_multi.php
        String[][] swimmers = new String[5][3];
        for (int i = 0; i < 5; i++) {
            System.out.println("Swimmer #" + (i + 1) + ":");
            swimmers[i][0] = checksForInput("  Enter swimmer name: ");
            swimmers[i][1] = checksForInput("  Enter swimmer time in seconds: ");
            swimmers[i][2] = checksForInput("  Enter swimmer placement: ");
        }

        // Add data to file via controller.
        controller.addSeniorTournament(tournamentName, date, discipline, swimmers);
    }

    private String checksForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // View competition members 18 years old and up
    private void viewCompSenior() {
        System.out.println(controller.viewCompSenior());
    }

    // Editing for competition members 18 years old and up
    private void editCompSenior() {

        System.out.println("Type out the name of the swimmer who's training time you want to change");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Member memberEdit = controller.editCompSenior(name);

        if (memberEdit == null) {
            System.out.println("Member not found. Please try again.");
            return;
        }

        System.out.println("What do you want to edit?\nOptions: breaststroke, crawl, back crawl, butterfly");
        String editChoice = scanner.nextLine().toLowerCase();

        switch (editChoice) {

            case "breaststroke" -> updateSwimTime(memberEdit, "breaststroke");
            case "crawl" -> updateSwimTime(memberEdit, "crawl");
            case "back crawl" -> updateSwimTime(memberEdit, "back crawl");
            case "butterfly" -> updateSwimTime(memberEdit, "butterfly");
            default -> System.out.println("Invalid option. Please try again.");

        }
    }

    // Updates swim time based on category
    private void updateSwimTime(Member member, String discipline) {
        if (member instanceof CompetitionSwimmer compSwimmer) {
            double time = checkValidTime(scanner, "new " + discipline);
            switch (discipline) {
                case "breaststroke" -> compSwimmer.setBreastTime(time);
                case "crawl" -> compSwimmer.setCrawlTime(time);
                case "back crawl" -> compSwimmer.setBackCrawlTime(time);
                case "butterfly" -> compSwimmer.setButterflyTime(time);
            }
            controller.saveCompSwimmerToList();
        } else {
            System.out.println("This member is not a competition swimmer.");
        }
    }

    // Handles top 5 for Joakim
    private void disciplinesJoakimMenu(String input) {
        System.out.println("What discipline do you want to see?\n");
        System.out.println("1. Breaststroke");
        System.out.println("2. Crawl");
        System.out.println("3. Butterfly");
        System.out.println("4. Back crawl");

        joakimTopDisciplines(scanner.nextLine());
    }

    // Shows top 5
    private void joakimTopDisciplines(String input) {
        switch (input) {
            case "1", "breaststroke" -> System.out.println(controller.sortBreastTimeSenior());
            case "2", "crawl" -> System.out.println(controller.sortCrawlTimeSenior());
            case "3", "butterfly" -> System.out.println(controller.sortButterflyTimeSenior());
            case "4", "backcrawl" -> System.out.println(controller.sortBackCrawlTimeSenior());
            default -> System.out.println("Invalid option. Please try again.");
        }
    }


    // Handles menu for Sara
    private void sara() {
        System.out.println("\nWelcome Sara. " +
                "Here are your options: ");

        while (!exit.equalsIgnoreCase("back")) {
            System.out.println("\n1. See competition members. ");
            System.out.println("2. Edit competition members. ");
            System.out.println("3. See top 5 swimmers within your chosen discipline.");
            System.out.println("4. Register tournament: place and date. ");
            System.out.println("5. View all tournaments and results.");
            System.out.println("Type 'back' to return to the main menu.");
            // Implement options for coach Sara

            String input = scanner.nextLine();
            if (input.equals("back")) break;
            handleSaraMenu(input);
        }
    }

    // Handles input
    private void handleSaraMenu(String input) {
        switch (input) {
            case "1", "see" -> viewCompJunior();
            case "2", "edit" -> editCompJunior();
            case "3", "top" -> disciplinesSaraMenu(input);
            case "4", "register" -> registerJuniorTournament();
            case "5", "view tournaments" -> System.out.println(controller.loadJuniorTournaments());
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    // Shows competition members under the age og 18
    private void viewCompJunior() {
        System.out.println(controller.viewCompJunior());
    }

    // Editing for competition members under the age og 18
    private void editCompJunior() {

        System.out.println("Type out the name of the swimmer who's training time you want to change");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Member memberEdit = controller.editCompJunior(name);

        if (memberEdit == null) {
            System.out.println("Member not found. Please try again.");
            return;
        }

        System.out.println("What do you want to edit?\nOptions: breaststroke, crawl, back crawl, butterfly");
        String editChoice = scanner.nextLine().toLowerCase();

        switch (editChoice) {

            case "breaststroke" -> updateSwimTime(memberEdit, "breaststroke");
            case "crawl" -> updateSwimTime(memberEdit, "crawl");
            case "back crawl" -> updateSwimTime(memberEdit, "back crawl");
            case "butterfly" -> updateSwimTime(memberEdit, "butterfly");
            default -> System.out.println("Invalid option. Please try again.");

        }
    }

    // Top 5 by category
    private void disciplinesSaraMenu(String input) {
        System.out.println("What discipline do you want to see?\n");
        System.out.println("1. Breaststroke");
        System.out.println("2. Crawl");
        System.out.println("3. Butterfly");
        System.out.println("4. Back crawl");

        saraTopDisciplines(scanner.nextLine());
    }

    // Shows top 5
    private void saraTopDisciplines(String input) {
        switch (input) {
            case "1", "breaststroke" -> System.out.println(controller.sortBreastTimeJunior());
            case "2", "crawl" -> System.out.println(controller.sortCrawlTimeJunior());
            case "3", "butterfly" -> System.out.println(controller.sortButterflyTimeJunior());
            case "4", "backcrawl" -> System.out.println(controller.sortBackCrawlTimeJunior());
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    // Registering junior tournament.
    private void registerJuniorTournament() {
        String tournamentName = checksForInput("Enter tournament name: ");
        String date = checksForInput("Enter date (dd/MM/yyyy): ");
        String discipline = checksForInput("Enter discipline (Breaststroke, Crawl, Back crawl, Butterfly): ");

        // Gather data for 5 swimmers.
        //2D array method found on https://www.w3schools.com/c/c_arrays_multi.php
        String[][] swimmers = new String[5][3];
        for (int i = 0; i < 5; i++) {
            System.out.println("Swimmer #" + (i + 1) + ":");
            swimmers[i][0] = checksForInput("  Enter swimmer name: ");
            swimmers[i][1] = checksForInput("  Enter swimmer time in seconds: ");
            swimmers[i][2] = checksForInput("  Enter swimmer placement: ");
        }

        // Add data to file via controller.
        controller.addJuniorTournament(tournamentName, date, discipline, swimmers);
    }

    // Exits
    private void exitProgram() {
        System.out.println("Exiting the program. Goodbye!");
        running = false; // Stops the main program loop
    }

}