package Members;

public class WorkoutSwimmer extends Member {


    public WorkoutSwimmer(String name, String status, int age, int fee) {
        super(name, status, age, fee);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n_____________________________________________\n";

    }


}

