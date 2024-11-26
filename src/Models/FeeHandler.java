package Models;

import Members.AllMembers;
import Members.WorkoutSwimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FeeHandler {

    private static final String fileName = "Fee.txt";

    public static void saveFeesToFile(ArrayList<AllMembers> membersList) {

        try (PrintStream output = new PrintStream(fileName)) {

            for (AllMembers member : membersList) {
                    output.println("Name: " + member.getName());
                    output.println("Status: " + member.getStatus());
                    output.println("Age: " + member.getAge());
                    output.println("Fee: " + member.getFee());
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Members could not be saved" + fnfe.getMessage());
        }
    }


    public static ArrayList<AllMembers> loadFeesFromFile() {
        ArrayList<AllMembers> memberList = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // Læs linjerne i den forventede rækkefølge
                String nameLine = scanner.nextLine().trim();
                if (nameLine.isEmpty()) continue; // Skip tomme linjer



                String name = nameLine.replace("Name: ", "").trim();

                String statusLine = scanner.nextLine().trim();
                if (!statusLine.startsWith("Status: ")) {
                    continue;
                }
                String status = statusLine.replace("Status: ", "").trim();

                String ageLine = scanner.nextLine().trim();
                if (!ageLine.startsWith("Age: ")) {
                    continue;
                }
                int age = Integer.parseInt(ageLine.replace("Age: ", "").trim());

                String feeLine = scanner.nextLine().trim();
                if(!feeLine.startsWith("Fee: ")) {
                    continue;
                }
                int fee = Integer.parseInt(feeLine.replace("Fee: ", "").trim());


                // Opret WorkoutSwimmer og tilføj til listen
                AllMembers member = new WorkoutSwimmer(name, status, age, fee);
                memberList.add(member);
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("File not found: " + fnfe.getMessage());
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Error parsing file. Please check the file format: " + nfe.getMessage());
        }
        return memberList;
    }

}
