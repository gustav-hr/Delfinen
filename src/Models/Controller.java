package Models;

import Members.AllMembers;
import Members.CompetitionSwimmer;
import Members.MembersList;
import Members.WorkoutSwimmer;

public class Controller {

    MembersList membersList = new MembersList();

    public void addCompetitive(String name, String status, int age, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime ) {

        if(age >= 18) {

            AllMembers competitive = new CompetitionSwimmer(name, status, age, "Joachim", breastTime, crawlTime, backCrawlTime, butterflyTime);

        }
        else {
            AllMembers competitive = new CompetitionSwimmer(name, status, age, "Sara", breastTime, crawlTime, backCrawlTime, butterflyTime);
        }
    }

    public void addWorkout(String name, String status, int age) {

        AllMembers workout = new WorkoutSwimmer(name, status, age);
    }

    public void getMembers() {
        membersList.getMembers();
    }






}
