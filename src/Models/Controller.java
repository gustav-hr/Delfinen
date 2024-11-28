package Models;

import Members.Member;
import Members.MembersList;
import Profiles.Coach;

public class Controller {

    MembersList membersList = new MembersList();
    Coach joachim = new Coach("Joachim");
    Coach sara = new Coach("Sara");

    // ADD COMPETITIVE & WORKOUT SWIMMER ------------------------------------------------------------------------------

    public void addCompetitive(String name, String status, int age, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime) {
        String coach = getCoachName(age, status);
        int fee = calculateFee(age, status);
        membersList.addCompetitionSwimmer(name, status, age, coach, breastTime, crawlTime, backCrawlTime, butterflyTime, fee);
    }

    public void addWorkout(String name, String status, int age) {
        int fee = calculateFee(age, status);
        membersList.addWorkoutSwimmer(name, status, age, fee);
    }

    private String getCoachName(int age, String status) {
        return age >= 18 ? joachim.getName() : sara.getName();
    }

    public int calculateFee(int age, String status) {
        if (status.equalsIgnoreCase("passive")) return 500;
        if (age >= 60) return 1200;
        return age >= 18 ? 1600 : 1000;
    }

    // GET ALL MEMBERS -------------------------------------------------------------------------------------------------

    public String getMembers() {
        if (membersList.getMembersList().isEmpty()) {
            return "No members in the swimming club";
        }
        String result = "";
        for (Member member : membersList.getMembersList()) {
            result += member.toString() + "\n";
        }
        return result;
    }

    // REMOVE ONE MEMBER -----------------------------------------------------------------------------------------------

    public boolean removeMember(String name) {
        return membersList.removeMember(name);
    }

    // RETURN SPECIFIC COMPETITIVE MEMBER ------------------------------------------------------------------------------

    public Member editCompMember(String membersName) {
        loadCompSwimmerFromList();
        for (Member allmembers : membersList.getMembersList()) {
            if (allmembers.getName().equalsIgnoreCase(membersName)) {
                return allmembers;
            }
        }
        return null;
    }

    // RETURN SPECIFIC WORKOUT MEMBER ----------------------------------------------------------------------------------

    public Member editWorkoutMembers(String membersName) {
        loadWorkoutSwimmersFromList();
        for (Member member : membersList.getMembersList()) {
            if (member.getName().equalsIgnoreCase(membersName)) {
                return member;
            }
        }
        return null;
    }

    // SAVE / LOAD COMPETITIVE AND WORKOUT SWIMMERS TO LIST ------------------------------------------------------------

    public void saveCompSwimmerToList() {
        membersList.saveCompSwimmers();
    }

    public void loadCompSwimmerFromList() {
        membersList.loadCompSwimmers();
    }

    public void saveWorkoutSwimmersToList() {
        membersList.saveWorkoutSwimmers();
    }

    public void loadWorkoutSwimmersFromList() {
        membersList.loadWorkoutSwimmers();
    }

    // CHANGE FEE FOR MEMBER -------------------------------------------------------------------------------------------

    public void changeFee(Member member) {

        if (member.getAge() >= 18) {
            if (member.getStatus().equalsIgnoreCase("passive")) {
                member.setFee(500);
            } else if (member.getAge() >= 60) {
                member.setFee(1200);
            } else {
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
//    public int calculateAllFees() {
//        membersList.loadAllMembers(); // Indlæs alle medlemmer
//
//        System.out.println("Antal medlemmer: " + membersList.getMembersList().size()); // Debugging
//        for (Member member : membersList.getMembersList()) {
//            System.out.println("Medlem: " + member.getName() + ", Status: " + member.getStatus() + ", Fee: " + member.getFee());
//        }
//
//        int totalFee = 0;
//        for (Member member : membersList.getMembersList()) {
//            totalFee += member.getFee(); // Summér gebyrerne
//        }
//
//        System.out.println("Total gebyrer: " + totalFee); // Debugging-output
//        return totalFee;
//    }

    public int calculateAllFees() {
        membersList.loadAllMembers(); // Indlæs alle medlemmer

        int totalFee = 0;
        for (Member member : membersList.getAllMembers()) { // Brug allMembers
            totalFee += member.getFee();
        }
        return totalFee;
    }

    public String Overview() {
        // Loading all members from the txt files:
        membersList.loadAllMembers();

        StringBuilder overview = new StringBuilder();
        for (Member member : membersList.getAllMembers()) {
            overview.append("Name: ").append(member.getName())
                    .append("\nStatus: ").append(member.getStatus())
                    .append("\nAge: ").append(member.getAge())
                    .append("\nFee: ").append(member.getFee())
                    .append("\n-----------------------------------\n");
        }

        return overview.toString();
    }

}


