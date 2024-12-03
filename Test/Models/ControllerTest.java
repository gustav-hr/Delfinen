package Models;

import Enums.PaymentStatus;
import Members.Member;
import Members.MembersList;
import Members.WorkoutSwimmer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void calculateAllFees() {
        // Arrange: Set up the controller and members list
        Controller controller = new Controller();
        MembersList membersList = new MembersList();
        controller.membersList = membersList;

        // Add members manually to the list
        ArrayList<Member> mockMembers = membersList.getAllMembers();
        //1600
        Member john = new WorkoutSwimmer("John","active", 30, controller.calculateFee(30,"active"), PaymentStatus.PAID);
        mockMembers.add(john);
        //1200
        Member jane = new WorkoutSwimmer("Jane", "active", 65, controller.calculateFee(65, "active"), PaymentStatus.PAID);
        mockMembers.add(jane);
        //500
        Member alice = new WorkoutSwimmer("Alice", "passive", 20, controller.calculateFee(20,"passive"),PaymentStatus.PAID);
        mockMembers.add(alice);


        // Assert: Check if the total fee is calculated correctly
        assertEquals(3300, john.getFee() + jane.getFee() + alice.getFee()); // Expected total fee: 1600 + 1200 + 500

    }
}