public class Main {
    public static void main(String[] args) {
        String fileLocation = "src\\Dominos\\dominos.txt";
        String dummyDataLocation = "src\\Dominos\\dummydata.txt";
        String entry = "0";

        MenuController menuController = new MenuController(fileLocation, dummyDataLocation);

        do {
            entry = menuController.showMenu();
            menuController.switchCase(entry);
        } while (!entry.toLowerCase().equals("x"));
    }
}