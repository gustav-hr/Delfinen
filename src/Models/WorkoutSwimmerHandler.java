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
                String name = scanner.nextLine().replace("Name: ", "").trim();
                String status = scanner.nextLine().replace("Status: ", "").trim();
                int age = Integer.parseInt(scanner.nextLine().replace("Age: ", "").trim());

                AllMembers member = new WorkoutSwimmer(name, status, age);

                memberList.add(member);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }
}
