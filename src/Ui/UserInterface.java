package Ui;

import Members.AllMembers;
import Members.CompetitionSwimmer;
import Members.MembersList;
import Models.Controller;

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
                        } else {

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
                        }

                        System.out.println("Adding new member to swimming club...");
                    }
                    case "list" -> {
                        controller.loadCompSwimmerFromList();
                        System.out.println(controller.getMembers());
                    }
                    case "exit" -> {
                        System.out.println("You are now exiting the program...");
                        System.exit(0);
                    }
                    case "remove" -> {
                        controller.loadCompSwimmerFromList();
                        removeMember();
                    }
                    case "edit" -> {
                        controller.loadCompSwimmerFromList();
                        editMember();
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

    private void editMember() {
        scanner.nextLine();
        System.out.println(controller.getMembers());
        System.out.println("Type the name of the member you want to edit.");

        String name = scanner.nextLine();
        AllMembers memberEdit = controller.editMembers(name);

        System.out.println("what do u want to edit? \n" +
                "All personal detaile, name, status, age......");

        String editName = scanner.nextLine().toLowerCase();
        switch (editName) {
            case "all" -> {

                if (memberEdit != null) {

                    if (memberEdit instanceof CompetitionSwimmer) {

                        System.out.print("Name: ");
                        memberEdit.setName(scanner.nextLine());

                        System.out.print("Status: ");
                        memberEdit.setStatus(scanner.next());

                        System.out.println("Editing age: ");
                        memberEdit.setAge(scanner.nextInt());
                        int memberAge = 0;
                        validInput = false;
                        while (!validInput) {
                            try {
                                memberAge = Integer.parseInt(scanner.next());
                                validInput = true;
                                memberEdit.setAge(memberAge);
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter age.  ");
                            }
                        }

                        System.out.println("New breaststroke time: ");
                        ((CompetitionSwimmer) memberEdit).setBreastTime(scanner.nextDouble());

                        System.out.println("New crawl time: ");
                        ((CompetitionSwimmer) memberEdit).setCrawlTime(scanner.nextDouble());

                        System.out.println("New back crawl time: ");
                        ((CompetitionSwimmer) memberEdit).setBackCrawlTime(scanner.nextDouble());

                        System.out.println("New butterfly time: ");
                        ((CompetitionSwimmer) memberEdit).setButterflyTime(scanner.nextDouble());

                    } else {

                        System.out.print("Name: ");
                        memberEdit.setName(scanner.nextLine());

                        System.out.print("Status: ");
                        memberEdit.setStatus(scanner.next());

                        System.out.println("Editing age: ");
                        memberEdit.setAge(scanner.nextInt());
                        int memberAge = 0;
                        validInput = false;
                        while (!validInput) {
                            try {
                                memberAge = Integer.parseInt(scanner.next());
                                validInput = true;
                                memberEdit.setAge(memberAge);
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter age. ");
                            }
                        }
                    }
                }
            }
            case "name" -> {
                System.out.println("Editing name: ");
                memberEdit.setName(scanner.nextLine());
            }
            case "status" -> {
                System.out.println("Editing status: ");
                memberEdit.setStatus(scanner.nextLine());
            }
            case "age" -> {
                System.out.println("Editing age: ");
                memberEdit.setAge(scanner.nextInt());
                int memberAge = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        memberAge = Integer.parseInt(scanner.next());
                        validInput = true;
                        memberEdit.setAge(memberAge);
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter age. ");
                    }
                }
            }
            case "breaststroke" -> {
                System.out.println("New time for breast stroke: ");
                ((CompetitionSwimmer) memberEdit).setBreastTime(scanner.nextDouble());
            }
            case "crawl" -> {
                System.out.println("New crawl time: ");
                ((CompetitionSwimmer) memberEdit).setCrawlTime(scanner.nextDouble());
            }
            case "back crawl" -> {
                System.out.println("New back crawl time: ");
                ((CompetitionSwimmer) memberEdit).setBackCrawlTime(scanner.nextDouble());
            }
            case "butterfly" -> {
                System.out.println("New butterfly time: ");
                ((CompetitionSwimmer) memberEdit).setButterflyTime(scanner.nextDouble());
            }
        }

    }


}


















