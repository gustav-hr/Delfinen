package Models;

import Enums.PaymentStatus;
import Members.CompetitionSwimmer;
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
                   output.println("Payment status: " + member.getPaymentStatus());
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
                String name = scanner.nextLine().replace("Name: ", "").trim();
                String status = scanner.nextLine().replace("Status: ", "").trim();
                int age = Integer.parseInt(scanner.nextLine().replace("Age: ", "").trim());

                //skip the Fee line
                scanner.nextLine();

                String paymentStatus = scanner.nextLine().replace("Payment status: ", "").trim();

                int fee = controller.calculateFee(age, status);

                Member member = new WorkoutSwimmer(name, status, age, fee, paymentStatus);
                memberList.add(member);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing file. Please check the file format: " + e.getMessage());
        }
        return memberList;
    }

    private static PaymentStatus parsePaymentStatus(String status) {
        try {
            return PaymentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            //Handles any invalid status and sets default to UNPAID
            return PaymentStatus.UNPAID;
        }
    }

}
