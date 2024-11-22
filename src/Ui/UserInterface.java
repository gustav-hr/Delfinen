package Ui;

import Members.AllMembers;
import Members.MembersList;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in); // Final scanner to use in the UI.
    private final MembersList membersList = new MembersList(); // Initializing Memberslist so it won't give an error when adding it to the ArrayList.


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
                        System.out.println("Adding member to swimming club...");
                        System.out.println("\nName: ");
                        scanner.nextLine();
                        String name = scanner.nextLine().toLowerCase(); // We read the name from the user.

                        // This is where we add the status of the new member of the dolphin club:
                        System.out.println("Type out the status of the new swimming member of the Dolphin swimming club:");
                        System.out.println("Your options are: ");
                        System.out.println("Junior membership.\nSenior membership.\nWorkout swimmer.\nCompetitive swimmer.");
                        String status = scanner.nextLine().toLowerCase();


                        // This is where we add the age of the new member of the dolphin club.
                        System.out.println("Type out the age of the new member:");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Removes the extra step that, for some reason, is there when trying to add age.

                        membersList.addMember(name, status, age);
                        System.out.println(name + ", " + age + ", " + status + ": is now added to the dolphin swimming club. ");
                    }

                    case "exit" -> {
                        System.out.println("You are now exiting the program...");
                        System.exit(0);
                    }
                    case "remove" -> {
                        System.out.println("Enter the name you want to remove from the swimming club: ");
                        scanner.nextLine(); // Scans the line of whoever the leader wants to remove.
                        String name = scanner.nextLine();
                        DET ER HER VI ER NÅET TIL!!!

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

