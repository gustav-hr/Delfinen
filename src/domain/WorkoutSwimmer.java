package domain;

import domain.enums.PaymentStatus;

public class WorkoutSwimmer extends Member {


    public WorkoutSwimmer(String name, String status, int age, int fee, PaymentStatus paymentStatus) {
        super(name, status, age, fee, paymentStatus);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n_____________________________________________\n";

    }


}

