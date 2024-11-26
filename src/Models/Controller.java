package Models;

import Members.AllMembers;
import Members.MembersList;
import Members.WorkoutSwimmer;

import java.util.ArrayList;

public class Controller {

    MembersList membersList = new MembersList();

    public void addCompetitive(String name, String status, int age, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime) {

        if (age >= 18) {

            membersList.addCompetitionSwimmer(name, status, age, "Joachim", breastTime, crawlTime, backCrawlTime, butterflyTime);
        } else {
            membersList.addCompetitionSwimmer(name, status, age, "Sara", breastTime, crawlTime, backCrawlTime, butterflyTime);
        }


    }

    public void addWorkout(String name, String status, int age) {
        membersList.addWorkoutSwimmer(name, status, age);
    }

    public String getMembers() {
        if (membersList.getMembersList().isEmpty()) {
            return "No members in the swimming club";
        }
        String result = "";
        for (AllMembers members : membersList.getMembersList()) {
            result += members.toString() + "\n";
        }
        return result;
    }

    public boolean removeMember(String name) {
        return membersList.removeMember(name);
    }


    public AllMembers editMembers(String membersName) {
        loadCompSwimmerFromList();
        for (AllMembers allmembers : membersList.getMembersList()) {
            if (allmembers.getName().equalsIgnoreCase(membersName)) {
                return allmembers;
            }
        }
        return null;
    }
    public AllMembers editWorkoutMembers(String membersName) {
        loadWorkoutSwimmersFromList();
        for(AllMembers allMembers : membersList.getMembersList()) {
            if(allMembers.getName().equalsIgnoreCase(membersName)) {
                return  allMembers;
            }
        }
        return null;
    }

    public void saveCompSwimmerToList() {
        membersList.saveCompSwimmers();
    }

    public void loadCompSwimmerFromList() {
        membersList.loadCompSwimmers();
    }

    public void saveWorkoutSwimmersToList(){
        membersList.saveWorkoutSwimmers();
    }

    public void loadWorkoutSwimmersFromList(){
        membersList.loadWorkoutSwimmers();
    }


    }

