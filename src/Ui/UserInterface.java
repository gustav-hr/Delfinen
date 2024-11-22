package Ui;

import Members.AllMembers;
import Members.MembersList;
import Models.Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in); // Final scanner to use in the UI.
    private final MembersList membersList = new MembersList(); // Initializing Memberslist so it won't give an error when adding it to the ArrayList.

    Controller controller = new Controller();

    public void startProgram() {
        System.out.println("Welcome to the Dolphin swimming club!");
        System.out.println("Please choose what profile you want to enter:");
        System.out.println("Dolphin leader" + "\nDolphin treasurer" + "\nDolphin coach");
        String userInput = "";
        userInput = scanner.next().toLowerCase();

        if (userInput.equalsIgnoreCase("leader")) {
            System.out.println("You have signed in as leader of the Dolphin swimming club. ");
            System.out.println("Your options as the leader of the Dolphin are:");
            System.out.println("Type: 'add' to add members to the swimming club. ");
            System.out.println("Type 'remove' to remove members from the swimming club. ");
            System.out.println("Type 'update' to update information on the members in the swimming club. ");
            System.out.println("Type 'exit' to exit the program. ");
            while (!userInput.equalsIgnoreCase("exit")) {

                userInput = scanner.next().toLowerCase();
                switch (userInput) {
                    case "add" -> {

                        System.out.println("Is the swimmer competitive? (yes/no)");

                        String compOrNot = scanner.next();

                        if(compOrNot.equalsIgnoreCase("yes")) {

                            System.out.print("\nName: ");
                            scanner.nextLine();
                            String name = scanner.nextLine().toLowerCase(); // We read the name from the user.

                            System.out.print("Status type; Passive, Active: ");
                            String status = scanner.next();

                            // This is where we add the age of the new member of the dolphin club.
                            System.out.print("Age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine(); // Removes the extra step that, for some reason, is there when trying to add age.


                            System.out.println("Type in your best time (in seconds) for the following Swimming techniques. \n" +
                                    "If you don't have a time yet, enter 0: ");
                            System.out.print("Breaststroke time: ");
                            double breastTime = scanner.nextDouble();
                            System.out.print("Crawl: ");
                            double crawlTime = scanner.nextDouble();
                            System.out.print("Back Crawl: ");
                            double backCrawlTime = scanner.nextDouble();
                            System.out.print("Butterfly: ");
                            double butterfly = scanner.nextDouble();

                            controller.addCompetitive(name, status, age, breastTime, crawlTime, backCrawlTime, butterfly);

                        } else {

                            System.out.print("\nName: ");
                            scanner.nextLine();
                            String name = scanner.nextLine().toLowerCase(); // We read the name from the user.

                            System.out.print("Status type; Passive, Active: ");
                            String status = scanner.next();

                            // This is where we add the age of the new member of the dolphin club.
                            System.out.print("Age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine(); // Removes the extra step that, for some reason, is there when trying to add age.

                            controller.addWorkout(name, status, age);
                        }

                        System.out.println("Adding new member to swimming club...");
                    }
                    case "list" -> {
                        controller.getMembers();
                    }

                    case "exit" -> {
                        System.out.println("You are now exiting the program...");
                        System.exit(0);
                    }
                    case "remove" -> {
                        System.out.println("Enter the name you want to remove from the swimming club: ");
                        scanner.nextLine(); // Scans the line of whoever the leader wants to remove.
                        String name = scanner.nextLine();

                    }
                }

            }
        } else if (userInput.equalsIgnoreCase("treasurer")) {
            System.out.println("You have now logged in as the treasurer of the Dolphin swimming club.");

        } else if (userInput.equalsIgnoreCase("coach")) {
            System.out.println("You have now logged in as the coach of the Dolphin swimming club.");


        } else System.out.println("You need to pick a profile to enter this program.");
    }
}

