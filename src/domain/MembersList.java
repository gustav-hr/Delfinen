package domain;

import domain.enums.PaymentStatus;
import dao.CompSwimmerHandler;
import dao.WorkoutSwimmerHandler;

import java.util.ArrayList;

public class MembersList {

    private ArrayList<Member> members = new ArrayList<>();

    // This is the ArrayList where we gather the added members.
    private ArrayList<Member> allMembers = new ArrayList<>();

    // ADD METHODS FOR WORKOUT SWIMMERS AND COMPETITION SWIMMER --------------------------------------------------------
    public void addWorkoutSwimmer(String name, String status, int age, int fee) {
        Member member = new WorkoutSwimmer(name, status, age, fee, PaymentStatus.PAID);
        members.add(member);
    }

    public void addCompetitionSwimmer(String name, String status, int age, String coach, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime, int fee) {
        Member member = new CompetitionSwimmer(name, status, age, coach, breastTime, "00/00/00", crawlTime, "00/00/00", backCrawlTime, "00/00/00", butterflyTime, "00/00/00", fee, PaymentStatus.PAID);
        members.add(member);
    }

    // GETTER FOR BOTH ARRAY LISTS -------------------------------------------------------------------------------------
    public ArrayList<Member> getMembersList() {
        return members;
    }

    public ArrayList<Member> getAllMembers() {
        return allMembers;
    }

    // Loading method for all members
    public void loadAllMembers() {
        // Empties list
        allMembers.clear();

        // Loads workout swimmer
        ArrayList<Member> workoutMembers = WorkoutSwimmerHandler.loadWorkoutFromFile();
        allMembers.addAll(workoutMembers);

        // Loads competition members
        ArrayList<Member> compMembers = CompSwimmerHandler.loadCompSwimmerFromFile();
        allMembers.addAll(compMembers);
    }

    // Removes member
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

    // Reaches FileHandler
    public void saveCompSwimmers() {
        CompSwimmerHandler.saveCompSwimmerToFile(members);
    }

    // Reaches FileHandler
    public void loadCompSwimmers() {
        members = CompSwimmerHandler.loadCompSwimmerFromFile();
    }

    // Reaches FileHandler
    public void saveWorkoutSwimmers() {
        WorkoutSwimmerHandler.saveWorkoutSwimmerToFile(members);
    }

    // Reaches FileHandler
    public void loadWorkoutSwimmers() {
        members = WorkoutSwimmerHandler.loadWorkoutFromFile();
    }
}