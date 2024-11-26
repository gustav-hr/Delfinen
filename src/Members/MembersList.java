package Members;

import Models.CompSwimmerHandler;
import Models.FeeHandler;
import Models.WorkoutSwimmerHandler;

import java.util.ArrayList;

public class MembersList {

    private ArrayList<AllMembers> members = new ArrayList<>();
    // This is the ArrayList where we gather the added members.

    public void addWorkoutSwimmer(String name, String status, int age, int fee) {
        AllMembers member = new WorkoutSwimmer(name, status, age, fee);
        members.add(member);
    }

    public void addCompetitionSwimmer(String name, String status, int age, String coach, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime, int fee) {
        AllMembers member = new CompetitionSwimmer(name, status, age, coach, breastTime, crawlTime, backCrawlTime, butterflyTime, fee);
        members.add(member);
    }
    // To show the members if we need that somewhere in the code. Will need most likely sometime.

    public ArrayList<AllMembers> getMembersList() {
        return members;
    }

    public boolean removeMember(String name) {

        loadCompSwimmers();
        for (AllMembers allMembers : getMembersList()) {
            if (allMembers.getName().equalsIgnoreCase(name)) {
                getMembersList().remove(allMembers); // Needs to be "allMembers" here and not "name" since it is looking at the whole ArrayList of names .
                saveCompSwimmers();
                return true;
            }
        }
        loadWorkoutSwimmers();
        for(AllMembers allMembers : getMembersList()) {
            if(allMembers.getName().equalsIgnoreCase(name)) {
                getMembersList().remove(allMembers);
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

    public void saveFeesToFile() {
        FeeHandler.saveFeesToFile(members);
    }
    public void loadFeesFromFile() {
        members = FeeHandler.loadFeesFromFile();
    }


}
