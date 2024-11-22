package Members;

import java.util.ArrayList;

public class MembersList {

    private ArrayList<AllMembers> members = new ArrayList<>();
    // This is the ArrayList where we gather the added members.

    public void addWorkoutSwimmer(String name, String status, int age) {
        AllMembers member = new WorkoutSwimmer(name, status, age);
        members.add(member);
    }

    public void addCompetitionSwimmer(String name, String status, int age, String coach, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime){
        AllMembers member = new CompetitionSwimmer(name,status,age,coach,breastTime,crawlTime,backCrawlTime,butterflyTime);
        members.add(member);
    }

    // To show the members if we need that somewhere in the code. Will need most likely sometime.
    public ArrayList<AllMembers> getMembers() {
        return members;
    }
}
