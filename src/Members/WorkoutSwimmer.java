package Members;

public class WorkoutSwimmer extends AllMembers {


    public WorkoutSwimmer(String name, String status, int age) {
        super(name, status, age);
    }


    MembersList membersList = new MembersList();

    @Override
    public String toString() {
        String result = ""; // Start med en tom streng
        for (AllMembers workoutswimmer : membersList.membersListArray()) {
            if (workoutswimmer instanceof WorkoutSwimmer) {
                CompetitionSwimmer swimmer = (CompetitionSwimmer) workoutswimmer;
                result += "Name: " + getName() +
                        "\nStatus: " + getStatus() +
                        "\nAge :" + getAge() +
                        "_____________________________________________\n";
            }
        }
        return result;
    }


}

