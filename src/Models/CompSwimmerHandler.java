package Models;

import Members.Member;
import Members.CompetitionSwimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CompSwimmerHandler {
    private static final String fileName = "CompSwimmers.txt";


    //method for saving members to txt file. Needs to be implicit and not a dedicated feature in UserInterface.

    public static void saveCompSwimmerToFile(ArrayList<Member> memberList) {
        try (PrintStream output = new PrintStream(fileName)) {

            for (Member member : memberList) {
                if (member instanceof CompetitionSwimmer) {
                    output.println("Name: " + member.getName());
                    output.println("Status: " + member.getStatus());
                    output.println("Age: " + member.getAge());
                    output.println("Coach: " + ((CompetitionSwimmer) member).getCoach());
                    output.println("Breaststroke time: " + ((CompetitionSwimmer) member).getBreastTime());
                    output.println("Crawl time: " + ((CompetitionSwimmer) member).getCrawlTime());
                    output.println("Back crawl time: " + ((CompetitionSwimmer) member).getBackCrawlTime());
                    output.println("Butterfly time: " + ((CompetitionSwimmer) member).getButterflyTime());
                    output.println("Fee: " + member.getFee());
                }
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Members could not be saved" + fnfe.getMessage());
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
                scanner.nextLine();

                int fee = controller.calculateFee(age, status);
                Member member = new CompetitionSwimmer(name, status, age, coach, breastTime, crawlTime, backCrawl, butterfly, fee);

                memberList.add(member);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }
}