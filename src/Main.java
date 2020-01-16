import java.util.Scanner;


public class Main {

    private static String fileLocation = "src\\Dominos\\dominos.txt";

    public static void main(String[] args) {
        String entry = "0";
        MenuController menuController = new MenuController(fileLocation);

        do {
            entry = menuController.showMenu();
            menuController.switchCase(entry);
        } while (!entry.toLowerCase().equals("x"));
    }




}