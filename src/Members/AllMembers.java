package Members;

public class AllMembers {

    private String name;
    private String status;
    private int age;

    public AllMembers (String name, String status, int age) {
        this.name=name;
        this.status=status;
        this.age=age;
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
}
