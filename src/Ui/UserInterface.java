package Ui;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in); // Final scanner to use in the UI.


    public void startProgram() {
        System.out.println("Welcome to the Dolphin swimming club!");
        System.out.println("Please choose what profile you want to enter:");
        System.out.println("Dolphin leader" + "\nDolphin treasurer" + "\nDolphin coach");
        String userInput = "";
        userInput = scanner.next().toLowerCase();
        while (!userInput.equalsIgnoreCase("exit")) {

            while (userInput.equalsIgnoreCase("leader")) {
                System.out.println("You have signed in as leader of the Dolphin swimming club. ");
                System.out.println("Your options as the leader of the Dolphin are:");
                System.out.println("Type: 'add' to add members to the swimming club. ");
                System.out.println("Type 'remove' to remove members from the swimming club. ");
                System.out.println("Type 'update' to update information on the members in the swimming club. ");

                //  -----------------------------------------------------------------------------------------------------------
            } // CODE FOR THE LEADER OF THE SWIMMING CLUB.
        }
    }
}

