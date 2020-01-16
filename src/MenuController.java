import java.util.Scanner;

public class MenuController {


    Scanner inputValue = new Scanner(System.in);
    DominoUtilizer dominoUtilizer;

    public MenuController(String DominoPath, String dummyDataPath) {
        dominoUtilizer = new DominoUtilizer(DominoPath, dummyDataPath);
    }

    public String showMenu() {
        System.out.println("[1] to see all your Dominos\n" +
                "[2] to add a new Domino\n" +
                "[3] to remove a Domino\n" +
                "[4] to execute the calculation\n" +
                "[5] to overwrite with some dummydata\n" +
                "[x] to turn off the application");
        String entry = inputValue.nextLine();
        System.out.println();
        return entry;
    }

    public void switchCase(String userInput) {
        System.out.println("\n".repeat(20));
        switch (userInput) {
            case "1":
                dominoUtilizer.printAllStones();
                inputValue.nextLine();
                break;
            case "2":
                dominoUtilizer.addStoneForm();
                inputValue.nextLine();
                break;
            case "3":
                dominoUtilizer.deleteStoneForm();
                inputValue.nextLine();
                break;
            case "4":
                dominoUtilizer.calculateSolution();
                inputValue.nextLine();
                break;
            case "5":
                dominoUtilizer.overWriteFileAndArrayWithDummyData();
                break;
            case "X":
            case "x":
                System.out.println("System shut down");
                break;
            default:
                System.out.println("Please enter a valid value");
        }
    }
}
