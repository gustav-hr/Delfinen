package Models;

import Members.AllMembers;
import Members.CompetitionSwimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class CompSwimmerHandler {
    private static final String fileName = "CompSwimmers.txt";

    //method for saving members to txt file. Needs to be implicit and not a dedicated feature in UserInterface.

    public static void saveMembersToFile(ArrayList<AllMembers> membersList) {
        try (PrintStream output = new PrintStream(fileName)) {

            for (AllMembers member : membersList) {
                if (member instanceof CompetitionSwimmer) {
                    output.println("Name: " + member.getName());
                    output.println("Status: " + member.getStatus());
                    output.println("Age: " + member.getAge());
                    output.println("Coach: " + ((CompetitionSwimmer) member).getCoach());
                    output.println("Breaststroke time: " + ((CompetitionSwimmer) member).getBreastTime());
                    output.println("Crawl time: " + ((CompetitionSwimmer) member).getCrawlTime());
                    output.println("Back crawl time: " + ((CompetitionSwimmer) member).getBackCrawlTime());
                    output.println("Butterfly time: " + ((CompetitionSwimmer) member).getButterflyTime());

                }
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Members could not be saved" + fnfe.getMessage());
        }
    }


    public static ArrayList<AllMembers> loadMembersToList(){
        ArrayList<AllMembers> memberList = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String name = scan.nextLine().replace("Name: ", "").trim();
                String status = scan.nextLine().replace("Status: ", "").trim();
                int age = Integer.parseInt(scan.nextLine().replace("Age: ", "").trim());
                String coach = scan.nextLine().replace("Coach: ", "").trim();
                double breastTime = Double.parseDouble(scan.nextLine().replace("Breaststroke time: ","").trim());
                double crawlTime = Double.parseDouble(scan.nextLine().replace("Crawl time: ", "").trim());
                double backCrawl = Double.parseDouble(scan.nextLine().replace("Back crawl time: ","").trim());
                double butterfly = Double.parseDouble(scan.nextLine().replace("Butterfly time: ", "").trim());

                AllMembers member = new CompetitionSwimmer(name,status,age,coach,breastTime,crawlTime,backCrawl,butterfly);

                memberList.add(member);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }
}