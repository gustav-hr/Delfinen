package Members;

import Models.CompSwimmerHandler;
import Models.WorkoutSwimmerHandler;

import java.util.ArrayList;

public class MembersList {

    private ArrayList<Member> members = new ArrayList<>();
    // This is the ArrayList where we gather the added members.

    public void addWorkoutSwimmer(String name, String status, int age, int fee) {
        Member member = new WorkoutSwimmer(name, status, age, fee);
        members.add(member);
    }

    public void addCompetitionSwimmer(String name, String status, int age, String coach, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime, int fee) {
        Member member = new CompetitionSwimmer(name, status, age, coach, breastTime, crawlTime, backCrawlTime, butterflyTime, fee);
        members.add(member);
    }
    // To show the members if we need that somewhere in the code. Will need most likely sometime.

    public ArrayList<Member> getMembersList() {
        return members;
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
