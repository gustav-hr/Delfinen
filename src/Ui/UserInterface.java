package Ui;

import Members.AllMembers;
import Members.CompetitionSwimmer;
import Members.MembersList;
import Members.WorkoutSwimmer;
import Models.Controller;

import javax.swing.*;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in); // Final scanner to use in the UI.
    private final MembersList membersList = new MembersList(); // Initializing Memberslist so it won't give an error when adding it to the ArrayList.
    private boolean validInput = false;

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
            System.out.println("Type 'list' to see the current list of members.");
            System.out.println("Type 'exit' to exit the program. ");
            while (!userInput.equalsIgnoreCase("exit")) {

                userInput = scanner.next().toLowerCase();
                switch (userInput) {
                    case "add" -> {

                        System.out.println("Is the swimmer competitive? (yes/no)");

                        String compOrNot = scanner.next();

                        if (compOrNot.equalsIgnoreCase("yes")) {

                            System.out.print("\nName: ");
                            scanner.nextLine();
                            String name = scanner.nextLine().toLowerCase(); // We read the name from the user.


                            //Makes sure the userinput either is "passive" or "active"
                            System.out.print("Status type; Passive, Active: ");
                            String status = scanner.next();
                            while (!(status.equalsIgnoreCase("passive")) && !(status.equalsIgnoreCase("active"))) {

                                System.out.println("Please enter either 'Passive' or 'Active'");
                                status = scanner.next();

                            }

                            //Makes sure the userinput is in fact a valid age.
                            System.out.print("Age: ");
                            int age = 0;
                            validInput = false;
                            while (!validInput) {
                                try {
                                    age = Integer.parseInt(scanner.next());
                                    validInput = true;
                                } catch (IllegalArgumentException iae) {
                                    System.out.println("Please enter a valid age");
                                }
                            }

                            scanner.nextLine(); // Removes the extra step that, for some reason, is there when trying to add age.

                            System.out.println("Type in your best time (in seconds) for the following Swimming techniques. \n" +
                                    "If you don't have a time yet, enter 0: ");

                            System.out.print("Breaststroke time: ");
                            double breastTime = 0;
                            validInput = false;
                            while (!validInput) {
                                try {
                                    breastTime = Double.parseDouble(scanner.next());
                                    validInput = true;
                                } catch (IllegalArgumentException iae) {
                                    System.out.println("Please enter a valid time");
                                }
                            }

                            System.out.print("Crawl: ");
                            double crawlTime = 0;
                            validInput = false;
                            while (!validInput) {
                                try {
                                    crawlTime = Double.parseDouble(scanner.next());
                                    validInput = true;
                                } catch (IllegalArgumentException iae) {
                                    System.out.println("Please enter a valid time");
                                }
                            }
                            System.out.print("Back Crawl: ");
                            double backCrawlTime = 0;
                            validInput = false;
                            while (!validInput) {
                                try {
                                    backCrawlTime = Double.parseDouble(scanner.next());
                                    validInput = true;
                                } catch (IllegalArgumentException iae) {
                                    System.out.println("Please enter a valid time");
                                }
                            }

                            System.out.print("Butterfly: ");
                            double butterfly = 0;
                            validInput = false;
                            while (!validInput) {
                                try {
                                    butterfly = Double.parseDouble(scanner.next());
                                    validInput = true;
                                } catch (IllegalArgumentException iae) {
                                    System.out.println("Please enter a valid age");
                                }
                            }

                            controller.addCompetitive(name, status, age, breastTime, crawlTime, backCrawlTime, butterfly);
                            controller.saveCompSwimmerToList();

                        } else if (compOrNot.equalsIgnoreCase("no")) {

                            System.out.print("\nName: ");
                            scanner.nextLine();
                            String name = scanner.nextLine().toLowerCase(); // We read the name from the user.

                            //Makes sure the userinput either is "passive" or "active"
                            System.out.print("Status type; Passive, Active: ");
                            String status = scanner.next();
                            while (!(status.equalsIgnoreCase("passive")) && !(status.equalsIgnoreCase("active"))) {

                                System.out.println("Please enter either 'Passive' or 'Active'");
                                status = scanner.next();

                            }
                            //Makes sure the userinput is in fact a valid age.
                            System.out.print("Age: ");
                            int age = 0;
                            validInput = false;
                            while (!validInput) {
                                try {
                                    age = Integer.parseInt(scanner.next());
                                    validInput = true;
                                } catch (IllegalArgumentException iae) {
                                    System.out.println("Please enter a valid age");
                                }
                            }
                            scanner.nextLine(); // Removes the extra step that, for some reason, is there when trying to add age.

                            controller.addWorkout(name, status, age);
                            controller.saveWorkoutSwimmersToList();
                        }

                        System.out.println("Adding new member to swimming club...");

                    }
                    case "list" -> {
                        System.out.println("What list do you want to see?");
                        System.out.println("CompetitionSwimmers: type 'comp'");
                        System.out.println("Others: type 'others'");

                        if (scanner.next().equalsIgnoreCase("comp")) {
                            controller.loadCompSwimmerFromList();
                            System.out.println(controller.getMembers());
                        } else {
                            controller.loadWorkoutSwimmersFromList();
                            System.out.println(controller.getMembers());
                        }
                    }
                    case "exit" -> {
                        System.out.println("You are now exiting the program...");
                        System.exit(0);
                    }
                    case "remove" -> {
                        removeMember();
                    }
                    case "edit" -> {

                        System.out.println("Would you like to edit the personal information of a Workout swimmer or a Competition swimmer? ");

                        String choice = scanner.next();

                        if (choice.contains("comp")) {
                            controller.loadCompSwimmerFromList();
                            System.out.println(controller.getMembers());
                            editCompMember();
                        } else {
                            controller.loadWorkoutSwimmersFromList();
                            System.out.println(controller.getMembers());
                            editWorkMember();
                        }
                    }
                }

            }
        } else if (userInput.equalsIgnoreCase("treasurer")) {
            System.out.println("You have now logged in as the treasurer of the Dolphin swimming club.");

        } else if (userInput.equalsIgnoreCase("coach")) {
            System.out.println("You have now logged in as the coach of the Dolphin swimming club.");


        } else System.out.println("You need to pick a profile to enter this program.");
    }

    private void removeMember() {
        System.out.println("Enter the name of the member you want to remove from the swimming club.");
        scanner.nextLine();
        String name = scanner.nextLine();
        if (controller.removeMember(name)) {
            System.out.println("(Former) swimming club member: " + name + " is now removed from the members list.");
        } else {
            System.out.println("Member not found, try again.");

        }
    }


    private void editWorkMember() {

        scanner.nextLine();
        System.out.println("Type the name of the member you want to edit: ");

        String name = scanner.nextLine();
        AllMembers memberEdit = controller.editWorkoutMembers(name);


        if (memberEdit == null) {
            System.out.println("Member not found. Please try again.");
            return;
        }
        System.out.println("What do you want to edit! \n" +
                "Options are as follows: all, name, status, age");

        String editChoice = scanner.nextLine().toLowerCase();

        switch (editChoice) {

            case "all" -> {
                System.out.print("Name: ");
                memberEdit.setName(scanner.nextLine());

                System.out.print("Status: ");
                memberEdit.setStatus(scanner.nextLine());

                int age = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("Age: ");
                        age = Integer.parseInt(scanner.nextLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid age.");
                    }
                }
                memberEdit.setAge(age);
            }
            case "name" -> {
                System.out.print("New name: ");
                memberEdit.setName(scanner.nextLine());
            }
            case "status" -> {
                System.out.print("New status: ");
                memberEdit.setStatus(scanner.nextLine());
            }
            case "age" -> {
                int age = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("New age: ");
                        age = Integer.parseInt(scanner.nextLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid age.");
                    }
                }
                memberEdit.setAge(age);
            }

        }
        controller.saveWorkoutSwimmersToList();
        System.out.println("Member details have been updated successfully.");

    }

    private void editCompMember() {
        scanner.nextLine(); // Clear input buffer
//        System.out.println(controller.getMembers());
        System.out.println("Type the name of the member you want to edit:");

        String name = scanner.nextLine();
        AllMembers memberEdit = controller.editMembers(name);


        if (memberEdit == null) {
            System.out.println("Member not found. Please try again.");
            return;
        }

        System.out.println("What do you want to edit? \n" +
                "Options are as follows: all, name, status, age, breaststroke, crawl, back crawl, butterfly");

        String editChoice = scanner.nextLine().toLowerCase();

        switch (editChoice) {
            case "all" -> {
                System.out.print("Name: ");
                memberEdit.setName(scanner.nextLine());

                System.out.print("Status: ");
                memberEdit.setStatus(scanner.nextLine());

                int age = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("Age: ");
                        age = Integer.parseInt(scanner.nextLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid age.");
                    }
                }
                memberEdit.setAge(age);

                CompetitionSwimmer compSwimmer = (CompetitionSwimmer) memberEdit;

                System.out.print("New breaststroke time: ");
                compSwimmer.setBreastTime(Double.parseDouble(scanner.nextLine()));

                System.out.print("New crawl time: ");
                compSwimmer.setCrawlTime(Double.parseDouble(scanner.nextLine()));

                System.out.print("New back crawl time: ");
                compSwimmer.setBackCrawlTime(Double.parseDouble(scanner.nextLine()));

                System.out.print("New butterfly time: ");
                compSwimmer.setButterflyTime(Double.parseDouble(scanner.nextLine()));
            }
            case "name" -> {
                System.out.print("New name: ");
                memberEdit.setName(scanner.nextLine());
            }
            case "status" -> {
                System.out.print("New status: ");
                memberEdit.setStatus(scanner.nextLine());
            }
            case "age" -> {
                int age = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("New age: ");
                        age = Integer.parseInt(scanner.nextLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid age.");
                    }
                }
                memberEdit.setAge(age);
            }
            case "breaststroke" -> {
                if (memberEdit instanceof CompetitionSwimmer) {
                    System.out.print("New time for breaststroke: ");
                    ((CompetitionSwimmer) memberEdit).setBreastTime(Double.parseDouble(scanner.nextLine()));
                } else {
                    System.out.println("This member is not a competition swimmer.");
                }
            }
            case "crawl" -> {
                if (memberEdit instanceof CompetitionSwimmer) {
                    System.out.print("New crawl time: ");
                    ((CompetitionSwimmer) memberEdit).setCrawlTime(Double.parseDouble(scanner.nextLine()));
                } else {
                    System.out.println("This member is not a competition swimmer.");
                }
            }
            case "back crawl" -> {
                if (memberEdit instanceof CompetitionSwimmer) {
                    System.out.print("New back crawl time: ");
                    ((CompetitionSwimmer) memberEdit).setBackCrawlTime(Double.parseDouble(scanner.nextLine()));
                } else {
                    System.out.println("This member is not a competition swimmer.");
                }
            }
            case "butterfly" -> {
                if (memberEdit instanceof CompetitionSwimmer) {
                    System.out.print("New butterfly time: ");
                    ((CompetitionSwimmer) memberEdit).setButterflyTime(Double.parseDouble(scanner.nextLine()));
                } else {
                    System.out.println("This member is not a competition swimmer.");
                }
            }
            default -> System.out.println("Invalid option. Please try again.");
        }


        // Save changes to the correct file
        if (memberEdit instanceof CompetitionSwimmer) {
            controller.saveCompSwimmerToList();
        } else if (memberEdit instanceof WorkoutSwimmer) {
            controller.saveWorkoutSwimmersToList();
        }

        System.out.println("Member details have been updated successfully.");
    }


}


















