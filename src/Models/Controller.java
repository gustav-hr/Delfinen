package Models;

import Members.AllMembers;
import Members.CompetitionSwimmer;
import Members.MembersList;
import Members.WorkoutSwimmer;

import java.util.ArrayList;

public class Controller {

    MembersList membersList = new MembersList();
    AllMembers allMembers;


    public void addCompetitive(String name, String status, int age, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime ) {

        if(age >= 18) {
            membersList.addCompetitionSwimmer(name, status, age, "Joachim", breastTime, crawlTime, backCrawlTime, butterflyTime);
        }
        else {
            membersList.addCompetitionSwimmer(name, status, age, "Sara", breastTime, crawlTime, backCrawlTime, butterflyTime);
        }
    }

    public void addWorkout(String name, String status, int age) {
        membersList.addWorkoutSwimmer(name, status, age);
    }

//    public ArrayList<AllMembers> getMembers() {
//       return membersList.getMembersList();
//    }
//
//   public String getMemberss() {
//        if(membersList.getMembersList().isEmpty()) {
//            return "No Members in the club";
//        }
//        String members = "";
//        for(AllMembers allMembers : membersList.getMembersList()) {
//            members += members + "\n";
//        }
//        return allMembers;
//   }

   public String getMembers() {
        if(membersList.getMembersList().isEmpty()) {
            return "no Members in the club";
        }
        String result = "";
        for(AllMembers members : membersList.getMembersList()) {
            result += members.toString() + "\n";
        }
        return result;
   }






}