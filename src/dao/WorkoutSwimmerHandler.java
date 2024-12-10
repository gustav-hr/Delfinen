package dao;

import domain.enums.PaymentStatus;
import domain.Member;
import domain.WorkoutSwimmer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutSwimmerHandler {
    private static final String fileName = "WorkoutSwimmers.csv";

    // FROM CONSOLE TO FILE --------------------------------------------------------------------------------------------
    public static void saveWorkoutSwimmerToFile(ArrayList<Member> memberList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            // Skriv header
            writer.write("Name,Status,Age,Fee,PaymentStatus");
            writer.newLine();
            for (Member member : memberList) {
                if (member instanceof WorkoutSwimmer) {
                    writer.write(member.getName() + ",");
                    writer.write(member.getStatus() + ",");
                    writer.write(member.getAge() + ",");
                    writer.write(member.getFee() + ",");
                    writer.write(member.getPaymentStatus().name());
                    writer.newLine();
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Fejl ved gemning af WorkoutSwimmers: " + ioe.getMessage(), ioe);
        }
    }

    // FROM FILE TO CONSOLE --------------------------------------------------------------------------------------------
    public static ArrayList<Member> loadWorkoutFromFile() {
        ArrayList<Member> memberList = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Filen findes ikke endnu. Ingen medlemmer er indlæst.");
            return memberList;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); // Spring header over
            while (scanner.hasNextLine()) {
                String[] attributes = scanner.nextLine().split(",");
                if (attributes.length == 5) {
                    String name = attributes[0];
                    String status = attributes[1];
                    int age = Integer.parseInt(attributes[2]);
                    int fee = Integer.parseInt(attributes[3]);
                    PaymentStatus paymentStatus = PaymentStatus.valueOf(attributes[4]);

                    WorkoutSwimmer swimmer = new WorkoutSwimmer(name, status, age, fee, paymentStatus);
                    memberList.add(swimmer);
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Fejl ved indlæsning af WorkoutSwimmers: " + ioe.getMessage(), ioe);
        }

        return memberList;
    }
}
