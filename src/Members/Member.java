package Members;

public abstract class Member {

    private String name;
    private String status;
    private int age;
    private int fee;

    public Member(String name, String status, int age, int fee) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.fee = fee;
    }
    // GETTER & SETTER FOR NAME ------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // GETTER & SETTER FOR AGE ------------------------------------------------------------------------------------
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    // GETTER & SETTER FOR STATUS ------------------------------------------------------------------------------------

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // GETTER & SETTER FOR FEE ---------------------------------------------------------------------------------------

    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nStatus: " + status +
                "\nAge: " + age;
    }
}