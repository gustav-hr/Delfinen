package Models;

import Members.AllMembers;
import Members.CompetitionSwimmer;
import Members.WorkoutSwimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutSwimmerHandler {
    private static final String fileName = "WorkoutSwimmers.txt";

    public static void saveWorkoutSwimmerToFile(ArrayList<AllMembers> membersList) {

        try (PrintStream output = new PrintStream(fileName)) {

            for (AllMembers member : membersList) {
                if (member instanceof WorkoutSwimmer) {
                    output.println("Name: " + member.getName());
                    output.println("Status: " + member.getStatus());
                    output.println("Age: " + member.getAge());
                }
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Members could not be saved" + fnfe.getMessage());
        }
    }


    public static ArrayList<AllMembers> loadWorkoutFromFile() {
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

                // Opret WorkoutSwimmer og tilføj til listen
                AllMembers member = new WorkoutSwimmer(name, status, age);
                memberList.add(member);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing file. Please check the file format: " + e.getMessage());
        }
        return memberList;
    }

}
