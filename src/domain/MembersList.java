package domain;

import domain.enums.PaymentStatus;
import dao.CompSwimmerHandler;
import dao.WorkoutSwimmerHandler;

import java.util.ArrayList;

public class MembersList {

    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Member> allMembers = new ArrayList<>();
    // This is the ArrayList where we gather the added members.

    public void addWorkoutSwimmer(String name, String status, int age, int fee) {
        Member member = new WorkoutSwimmer(name, status, age, fee, PaymentStatus.PAID);
        members.add(member);
    }

    public void addCompetitionSwimmer(String name, String status, int age, String coach, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime, int fee) {
        Member member = new CompetitionSwimmer(name, status, age, coach, breastTime, "00/00/00", crawlTime, "00/00/00", backCrawlTime, "00/00/00", butterflyTime, "00/00/00", fee, PaymentStatus.PAID);
        members.add(member);
    }
    // To show the members if we need that somewhere in the code. Will need most likely sometime.

    public ArrayList<Member> getMembersList() {
        return members;
    }

    public ArrayList<Member> getAllMembers() {
        return allMembers;
    }
    public void loadAllMembers() {
        // Tøm listen først, hvis der allerede er data
        allMembers.clear();

        // Indlæs træningssvømmere
        ArrayList<Member> workoutMembers = WorkoutSwimmerHandler.loadWorkoutFromFile();
        allMembers.addAll(workoutMembers);

        // Indlæs konkurrencesvømmere
        ArrayList<Member> compMembers = CompSwimmerHandler.loadCompSwimmerFromFile();
        allMembers.addAll(compMembers);
    }

    public boolean removeMember(String name) {

        loadCompSwimmers();
        for (Member member : getMembersList()) {
            if (member.getName().equalsIgnoreCase(name)) {
                getMembersList().remove(member); // Needs to be "allMembers" here and not "name" since it is looking at the whole ArrayList of names .
                saveCompSwimmers();
                return true;
            }
        }
        loadWorkoutSwimmers();
        for(Member member : getMembersList()) {
            if(member.getName().equalsIgnoreCase(name)) {
                getMembersList().remove(member);
                saveWorkoutSwimmers();
                return true;
            }
        }
        return false;
    }

    public void saveCompSwimmers() {
        CompSwimmerHandler.saveCompSwimmerToFile(members);
    }

    public void loadCompSwimmers() {
        members = CompSwimmerHandler.loadCompSwimmerFromFile();
    }

    public void saveWorkoutSwimmers() {
        WorkoutSwimmerHandler.saveWorkoutSwimmerToFile(members);
    }

    public void loadWorkoutSwimmers() {
        members = WorkoutSwimmerHandler.loadWorkoutFromFile();
    }
}