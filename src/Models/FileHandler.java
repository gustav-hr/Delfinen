package Models;

import Members.AllMembers;
import Members.CompetitionSwimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final String fileName = "Members.txt";

    //SKAL MÅSKE VÆRE STATIC??

    public void saveMembersToFile(ArrayList<AllMembers> membersList) {
        try (PrintStream output = new PrintStream(fileName)) {

            for (AllMembers member : membersList) {
                if (member instanceof CompetitionSwimmer) {
                    output.println("Name: " + member.getName());
                    output.println("\nStatus: " + member.getStatus());
                    output.println("\nAge: " + member.getAge());
                    output.println("\nCoach: " + ((CompetitionSwimmer) member).getCoach());
                    output.println("\nBreaststroke time: " + ((CompetitionSwimmer) member).getBreastTime());
                    output.println("\nCrawl time: " + ((CompetitionSwimmer) member).getCrawlTime());
                    output.println("\nBack crawl time: " + ((CompetitionSwimmer) member).getBackCrawlTime());
                    output.println("\nButterfly time: " + ((CompetitionSwimmer) member).getButterflyTime());

                } else {

                    output.println("Name: " + member.getName());
                    output.println("\nStatus: " + member.getStatus());
                    output.println("\nAge: " + member.getAge());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Members could not be saved" + e.getMessage());
        }
    }
/*
    public ArrayList<AllMembers> loadMembersToList() {
        ArrayList<AllMembers> memberList = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner scan = new Scanner(file)) {

            while (scan.hasNextLine()) {

            }
        }
    }
}*/
}
