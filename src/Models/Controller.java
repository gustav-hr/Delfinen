package Models;

import Members.AllMembers;
import Members.MembersList;
import Members.WorkoutSwimmer;

import java.util.ArrayList;

public class Controller {

    MembersList membersList = new MembersList();

    public void addCompetitive(String name, String status, int age, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime) {

        if (age >= 18) {
            if(status.equalsIgnoreCase("passive")) {
                membersList.addCompetitionSwimmer(name, status, age, "Joachim", breastTime, crawlTime, backCrawlTime, butterflyTime, 500);
            }
            else if(age >= 60) {
                membersList.addCompetitionSwimmer(name, status, age, "Joachim", breastTime, crawlTime, backCrawlTime, butterflyTime, 1200);
            }
            else {
                membersList.addCompetitionSwimmer(name, status, age, "Joachim", breastTime, crawlTime, backCrawlTime, butterflyTime, 1600);
            }
        } else {
            if(status.equalsIgnoreCase("passive")) {
                membersList.addCompetitionSwimmer(name, status, age, "Sara", breastTime, crawlTime, backCrawlTime, butterflyTime, 500);
            }
            else {
                membersList.addCompetitionSwimmer(name, status, age, "Sara", breastTime, crawlTime, backCrawlTime, butterflyTime, 1000);
            }
        }
    }

    public void addWorkout(String name, String status, int age) {

        if(age >= 18) {
            if(status.equalsIgnoreCase("passive")) {
                membersList.addWorkoutSwimmer(name, status, age, 500);
            }
            else if(age >= 60) {
                membersList.addWorkoutSwimmer(name, status, age, 1200);
            }
            else {
                membersList.addWorkoutSwimmer(name, status, age, 1600);
            }
        } else {
            if (status.equalsIgnoreCase("passive")) {
                membersList.addWorkoutSwimmer(name, status, age, 500);
            } else {
                membersList.addWorkoutSwimmer(name, status, age, 1000);
            }
        }
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
    public void changeFee(AllMembers member){

        if(member.getAge() >= 18) {
            if(member.getStatus().equalsIgnoreCase("passive")) {
                member.setFee(500);
            }
            else if(member.getAge() >= 60) {
                member.setFee(1200);
            }
            else {
                member.setFee(1600);
            }
        } else {
            if (member.getStatus().equalsIgnoreCase("passive")) {
                member.setFee(500);
            } else {
                member.setFee(1000);
            }
        }
    }

    public void saveFeeToList() {
        membersList.saveFeesToFile();
    }
    public void loadFeesFromFile() {
        membersList.loadFeesFromFile();
    }


    }

