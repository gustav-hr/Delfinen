package Models;

import Enums.PaymentStatus;
import Members.CompetitionSwimmer;
import Members.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CompSeniorTournamentHandler {

    private static final String fileName = "CompSeniorTournament.txt";


    public static void saveCompSeniorTournamentSwimmerToFile(ArrayList<Member> memberList) {

        System.out.println("Competition name: ");

        //SCANNER FOR COMP NAME

        System.out.println("Swimming discipline:");

        //Discipline

        System.out.println("Date");

        //date

        System.out.println("Ranking:");

        //rank

        String[] information = {

            "Competition name: ",
            "Swimming discipline",
            "Date of competition",
            "Swimmers ranking",

        };

        try (PrintStream output = new PrintStream(fileName)) {

            output.print("Tournament: ");
            output.print("Date: ");
            output.print("discipline");
            for (Member member : memberList) {
                if (member instanceof CompetitionSwimmer) {

                    output.println("Name: " + member.getName());
                    output.println("Age: " + member.getAge());
                    output.println("Tournament Time: ");
                    output.println("Rank: ");
                }
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Members could not be saved: " + fnfe.getMessage());
        }


    }


    public static ArrayList<Member> loadCompSwimmerFromFile() {
        ArrayList<Member> memberList = new ArrayList<>();
        File file = new File(fileName);
        Controller controller = new Controller();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().replace("Name: ", "").trim();
                String status = scanner.nextLine().replace("Status: ", "").trim();
                int age = Integer.parseInt(scanner.nextLine().replace("Age: ", "").trim());
                String coach = scanner.nextLine().replace("Coach: ", "").trim();
                double breastTime = Double.parseDouble(scanner.nextLine().replace("Breaststroke time: ", "").trim());
                double crawlTime = Double.parseDouble(scanner.nextLine().replace("Crawl time: ", "").trim());
                double backCrawl = Double.parseDouble(scanner.nextLine().replace("Back crawl time: ", "").trim());
                double butterfly = Double.parseDouble(scanner.nextLine().replace("Butterfly time: ", "").trim());
                int fee = Integer.parseInt(scanner.nextLine().replace("Fee: ", "").trim());
                String paymentStatusStr = scanner.nextLine().replace("PaymentStatus: ", "").trim();
                PaymentStatus paymentStatus = PaymentStatus.valueOf(paymentStatusStr);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        }
        return memberList;
    }

}