package Ui;

public class UserInterface {

    public static void main(String[] args) {

        System.out.println("Welcome to the Dolphin swimming club!");
        System.out.println("Please choose what profile you want to enter:");
        System.out.println("Dolphin leader" + "\n Dolphin treasurer)" + "\n Dolphin coach");

        String uesrInput = "";

        while (uesrInput.equalsIgnoreCase("leader")) {
            System.out.println("You have signed in as leader of the Dolhpin swimming club. ");
        }
    }
}
