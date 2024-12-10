package services;

import domain.enums.PaymentStatus;
import domain.CompetitionSwimmer;
import domain.Member;
import domain.MembersList;
import services.comparators.BackCrawlTimeComparator;
import services.comparators.BreastTimeComparator;
import services.comparators.ButterflyTimeComparator;
import services.comparators.CrawlTimeComparator;
import domain.Coach;

import java.util.ArrayList;
import java.util.Comparator;

public class Controller {

    public MembersList membersList = new MembersList();
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
                    .append("\nPayment status: ").append(member.getPaymentStatus())
                    .append("\n-----------------------------------\n");
        }

        return overview.toString();
    }


    //CHANGE PAYMENT STATUS OF SWIMMERS COMPETITIVE AND WORKOUT -------------------------------------------------------

    public void changePaymentStatus(String name) {

        membersList.loadCompSwimmers();
        for (Member member : membersList.getMembersList()) {
            if (member.getName().equalsIgnoreCase(name)) {

                togglePaymentStatus(member);

                membersList.saveCompSwimmers();
                return;
            }
        }

        membersList.loadWorkoutSwimmers();
        for (Member member : membersList.getMembersList()) {
            if (member.getName().equalsIgnoreCase(name)) {

                togglePaymentStatus(member);

                membersList.saveWorkoutSwimmers();
                return;
            }
        }
        System.out.println("Member not found.");
    }

    private void togglePaymentStatus(Member member) {

        if (member.getPaymentStatus().name().equalsIgnoreCase("PAID")) {
            member.setPaymentStatus(PaymentStatus.UNPAID);
        } else {
            member.setPaymentStatus(PaymentStatus.PAID);
        }
    }

    // VIEW UNPAID USERS ONLY ---------------------------------------------------------------------------


    public String viewUNPAIDSwimmers() {

        membersList.loadAllMembers();

        StringBuilder overview = new StringBuilder();
        for (Member member : membersList.getAllMembers()) {

            if (member.getPaymentStatus().name().equalsIgnoreCase("UNPAID")) {
                overview.append("Name: ").append(member.getName())
                        .append("\nStatus: ").append(member.getStatus())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nFee: ").append(member.getFee())
                        .append("\nPayment status: ").append(member.getPaymentStatus())
                        .append("\n-----------------------------------\n");
            }
        }

        if (overview.toString().isEmpty()) {
            return "No swimmers have an UNPAID Payment status";
        } else {
            return overview.toString();
        }

    }


    //  COACH SENIOR METHODS  -----------------------------------------------------------------------------

    public String viewCompSenior() {

        membersList.loadCompSwimmers();

        StringBuilder overview = new StringBuilder();
        for (Member member : membersList.getMembersList()) {

            if (member.getAge() > 18 && member instanceof CompetitionSwimmer) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nBreaststroke time: ").append(((CompetitionSwimmer) member).getBreastTime())
                        .append("\nBreaststroke date: ").append(((CompetitionSwimmer) member).getBreastTimeDate())
                        .append("\nCrawl time: ").append(((CompetitionSwimmer) member).getCrawlTime())
                        .append("\nCrawl date: ").append(((CompetitionSwimmer) member).getCrawlTimeDate())
                        .append("\nBack crawl time: ").append(((CompetitionSwimmer) member).getBackCrawlTime())
                        .append("\nBack crawl date: ").append(((CompetitionSwimmer) member).getBackCrawlTimeDate())
                        .append("\nButterfly time: ").append(((CompetitionSwimmer) member).getButterflyTime())
                        .append("\nButterfly date: ").append(((CompetitionSwimmer) member).getButterflyTimeDate())
                        .append("\n--------------------------------------\n");

            }
        }
        if (overview.toString().isEmpty()) {
            return "No senior competitive swimmers matches the search.";
        } else {
            return overview.toString();
        }
    }

    public Member editCompSenior(String name) {

        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() > 18 && member instanceof CompetitionSwimmer && member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

    //SENIOR SORTING BY CATEGORY

    //BREASTTIME
    public String sortBreastTimeSenior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() >= 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getBreastTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new BreastTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nBreaststroke time: ").append(((CompetitionSwimmer) member).getBreastTime())
                        .append("\nBreaststroke date: ").append(((CompetitionSwimmer) member).getBreastTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    public String sortBreastTimeJunior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() < 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getBreastTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new BreastTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nBreaststroke time: ").append(((CompetitionSwimmer) member).getBreastTime())
                        .append("\nBreaststroke date: ").append(((CompetitionSwimmer) member).getBreastTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    //CRAWLTIME
    public String sortCrawlTimeSenior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() >= 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getCrawlTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new CrawlTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nCrawl time: ").append(((CompetitionSwimmer) member).getCrawlTime())
                        .append("\nCrawl date: ").append(((CompetitionSwimmer) member).getCrawlTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    public String sortCrawlTimeJunior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() < 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getCrawlTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new CrawlTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nCrawl time: ").append(((CompetitionSwimmer) member).getCrawlTime())
                        .append("\nCrawl date: ").append(((CompetitionSwimmer) member).getCrawlTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    //BACK CRAWL TIME
    public String sortBackCrawlTimeSenior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() >= 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getBackCrawlTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new BackCrawlTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nBack crawl time: ").append(((CompetitionSwimmer) member).getBackCrawlTime())
                        .append("\nBack crawl date: ").append(((CompetitionSwimmer) member).getBackCrawlTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    public String sortBackCrawlTimeJunior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() < 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getBackCrawlTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new BackCrawlTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nBack crawl time: ").append(((CompetitionSwimmer) member).getBackCrawlTime())
                        .append("\nBack crawl date: ").append(((CompetitionSwimmer) member).getBackCrawlTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    //BUTTERFLY TIME
    public String sortButterflyTimeSenior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() >= 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getButterflyTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new ButterflyTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nButterfly time: ").append(((CompetitionSwimmer) member).getButterflyTime())
                        .append("\nButterfly date: ").append(((CompetitionSwimmer) member).getButterflyTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    public String sortButterflyTimeJunior() {
        ArrayList<CompetitionSwimmer> list = new ArrayList<>();
        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() < 18 && member instanceof CompetitionSwimmer && ((CompetitionSwimmer) member).getButterflyTime() != 0.0) {
                list.add((CompetitionSwimmer) member);
            }
        }
        Comparator<CompetitionSwimmer> comparator = new ButterflyTimeComparator();
        list.sort(comparator);
        StringBuilder overview = new StringBuilder();


        int count = 0;
        for (Member member : list) {
            count++;
            if (count < 6) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nButterfly time: ").append(((CompetitionSwimmer) member).getButterflyTime())
                        .append("\nButterfly date: ").append(((CompetitionSwimmer) member).getButterflyTimeDate())
                        .append("\n--------------------------------------\n");
            }
        }
        return overview.toString();
    }

    public void addSeniorTournament(String tournamentName, String date, String discipline, String[][] swimmers) {
        CompSeniorTournamentHandler handler = new CompSeniorTournamentHandler();
        handler.addSeniorTournamentData(tournamentName, date, discipline, swimmers);
    }

    public String loadTournaments() {
        CompSeniorTournamentHandler handler = new CompSeniorTournamentHandler();
        return handler.loadSeniorTournamentData();
    }

    //COACH JUNIOR METHODS ----------------------------------------------------------------------------

    public void addJuniorTournament(String tournamentName, String date, String discipline, String[][] swimmers) {
        CompJuniorTournamentHandler handler = new CompJuniorTournamentHandler();
        handler.addJuniorTournamentData(tournamentName, date, discipline, swimmers);
    }

    public String loadJuniorTournaments() {
        CompJuniorTournamentHandler handler = new CompJuniorTournamentHandler();
        return handler.loadJuniorTournamentData();
    }

    public String viewCompJunior() {

        membersList.loadCompSwimmers();

        StringBuilder overview = new StringBuilder();
        for (Member member : membersList.getMembersList()) {

            if (member.getAge() < 18 && member instanceof CompetitionSwimmer) {
                overview.append("Name: ").append(member.getName())
                        .append("\nAge: ").append(member.getAge())
                        .append("\nBreaststroke time: ").append(((CompetitionSwimmer) member).getBreastTime())
                        .append("\nBreaststroke date: ").append(((CompetitionSwimmer) member).getBreastTimeDate())
                        .append("\nCrawl time: ").append(((CompetitionSwimmer) member).getCrawlTime())
                        .append("\nCrawl date: ").append(((CompetitionSwimmer) member).getCrawlTimeDate())
                        .append("\nBack crawl time: ").append(((CompetitionSwimmer) member).getBackCrawlTime())
                        .append("\nBack crawl date: ").append(((CompetitionSwimmer) member).getBackCrawlTimeDate())
                        .append("\nButterfly time: ").append(((CompetitionSwimmer) member).getButterflyTime())
                        .append("\nButterfly date: ").append(((CompetitionSwimmer) member).getButterflyTimeDate())
                        .append("\n--------------------------------------\n");

            }
        }
        if (overview.toString().isEmpty()) {
            return "No junior competitive swimmers matches the search.";
        } else {
            return overview.toString();
        }
    }

    public Member editCompJunior(String name) {

        membersList.loadCompSwimmers();

        for (Member member : membersList.getMembersList()) {
            if (member.getAge() < 18 && member instanceof CompetitionSwimmer && member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

}