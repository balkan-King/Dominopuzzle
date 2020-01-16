import java.util.Scanner;

public class MenuController {


    Scanner inputValue = new Scanner(System.in);
    DominoEditor dominoEditor;

    public MenuController(String DominoPath, String dummyDataPath, String resultFile) {
        dominoEditor = new DominoEditor(DominoPath, dummyDataPath, resultFile);
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
                dominoEditor.printAllStones();
                inputValue.nextLine();
                break;
            case "2":
                dominoEditor.addStoneForm();
                inputValue.nextLine();
                break;
            case "3":
                dominoEditor.deleteStoneForm();
                inputValue.nextLine();
                break;
            case "4":
                dominoEditor.calculateSolution();
                inputValue.nextLine();
                break;
            case "5":
                dominoEditor.overWriteFileAndArrayWithDummyData();
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
