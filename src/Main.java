public class Main {
    public static void main(String[] args) {
        String fileLocation = "src\\Dominos\\dominos.txt";
        String dummyDataLocation = "src\\Dominos\\dummydata.txt";
        String resultFile = "src\\Dominos\\solution.txt";
        String entry;

        MenuController menuController = new MenuController(fileLocation, dummyDataLocation, resultFile);

        do {
            entry = menuController.showMenu();
            menuController.switchCase(entry);
        } while (!entry.toLowerCase().equals("x"));
    }
}