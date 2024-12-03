package Models;

import Enums.PaymentStatus;
import Members.Member;
import Members.CompetitionSwimmer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CompSwimmerHandler {
    private static final String fileName = "CompSwimmers.csv";


    //method for saving members to txt file. Needs to be implicit and not a dedicated feature in UserInterface.

    public static void saveCompSwimmerToFile(ArrayList<Member> memberList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write("Name,Status,Age,Coach,BreastTime,CrawlTime,BackCrawlTime,ButterflyTime,Fee,PaymentStatus");
            writer.newLine();
            for (Member member : memberList) {

                if (member instanceof CompetitionSwimmer) {

                    writer.write(member.getName() + ",");

                    writer.write(member.getStatus() + ",");

                    writer.write(member.getAge() + ",");

                    writer.write(((CompetitionSwimmer) member).getCoach() + ",");

                    writer.write(((CompetitionSwimmer) member).getBreastTime() + ",");

                    writer.write(((CompetitionSwimmer) member).getCrawlTime() + ",");

                    writer.write(((CompetitionSwimmer) member).getBackCrawlTime() + ",");

                    writer.write(((CompetitionSwimmer) member).getButterflyTime() + ",");

                    writer.write(member.getFee() + ",");

                    writer.write(member.getPaymentStatus().name());

                    //skifter linje

                    writer.newLine();

                }
                writer.flush();

            }

        } catch (IOException ioe) {

            throw new RuntimeException("Fejl ved gemning af konkurrerende svømmere: " + ioe.getMessage(), ioe);

        }

    }



    public static ArrayList<Member> loadCompSwimmerFromFile() {
        ArrayList<Member> memberList = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Filen findes ikke endnu. Ingen medlemmer er indlæst.");
            return memberList;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] attributes = scanner.nextLine().split(",");
                if (attributes.length == 10) {
                    String name = attributes[0];
                    String status = attributes[1];
                    int age = Integer.parseInt(attributes[2]);
                    String coach = attributes[3];
                    double breastTime = Double.parseDouble(attributes[4]);
                    double crawlTime = Double.parseDouble(attributes[5]);
                    double backCrawlTime = Double.parseDouble(attributes[6]);
                    double butterflyTime = Double.parseDouble(attributes[7]);
                    int fee = Integer.parseInt(attributes[8]);
                    PaymentStatus paymentStatus = PaymentStatus.valueOf(attributes[9]);

                    CompetitionSwimmer swimmer = new CompetitionSwimmer(name, status, age, coach, breastTime, crawlTime, backCrawlTime, butterflyTime, fee, paymentStatus);
                    memberList.add(swimmer);
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Fejl ved indlæsning af konkurrerende svømmere: " + ioe.getMessage(), ioe);
        }

        return memberList;
    }
}