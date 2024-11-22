package Members;

import java.util.ArrayList;

public class MembersList {

    private ArrayList<AllMembers> members = new ArrayList<>();
    // This is the ArrayList where we gather the added members.

    public void addMember(String name, String status, int age) {
        AllMembers member = new AllMembers(name, status, age);
        members.add(member);
    }

    // To show the members if we need that somewhere in the code. Will need most likely sometime.
    public ArrayList<AllMembers> getMembers() {
        return members;
    }
}
