package Models;

import Enums.PaymentStatus;
import Members.Member;
import Members.WorkoutSwimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutSwimmerHandler {
    private static final String fileName = "WorkoutSwimmers.txt";

    public static void saveWorkoutSwimmerToFile(ArrayList<Member> memberList) {

        try (PrintStream output = new PrintStream(fileName)) {

            for (Member member : memberList) {
                if (member instanceof WorkoutSwimmer) {
                    output.println("Name: " + member.getName());
                    output.println("Status: " + member.getStatus());
                    output.println("Age: " + member.getAge());
                   output.println("Fee: " + member.getFee());
                }
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Members could not be saved" + fnfe.getMessage());
        }
    }


    public static ArrayList<Member> loadWorkoutFromFile() {
        ArrayList<Member> memberList = new ArrayList<>();
        File file = new File(fileName);
        Controller controller = new Controller();

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
                scanner.nextLine();
                // Opret WorkoutSwimmer og tilføj til listen

                int fee = controller.calculateFee(age, status);
                Member member = new WorkoutSwimmer(name, status, age, fee, PaymentStatus.PAID);
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
