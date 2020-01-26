import java.util.Scanner;

/**
 * This class is a controller class and is only needed to redirect the user entry to the particular function.
 */
public class MenuController {
    Scanner inputValue = new Scanner(System.in);
    DominoEditor dominoEditor;

    public MenuController(String dominoPath, String dummyDataPath, String resultFile) {
        dominoEditor = new DominoEditor(dominoPath, dummyDataPath, resultFile);
    }

    /**
     * Shows the menu and saves the entry of the user.
     * @return variable which saved the userentry in itself.
     */
    public String showMenuAndGetUserInput() {
        System.out.println("\n".repeat(20));
        System.out.println("[1] to see all your Dominos\n" +
                "[2] to add a new Domino\n" +
                "[3] to remove a Domino\n" +
                "[4] to execute the calculation\n" +
                "[5] to overwrite with default data\n" +
                "[x] to turn off the application");
        String userInput = inputValue.nextLine();
        System.out.println();
        return userInput;
    }

    /**
     * Contains a switch-case redirecting the user to the particular method.
     * @param userInput is the entry of the user.
     */
    public void switchCase(String userInput) {
        switch (userInput) {
            case "1":
                dominoEditor.printAllStones();
                System.out.println("[Press Enter to continue]");
                inputValue.nextLine();
                break;
            case "2":
                dominoEditor.addStoneForm();
                System.out.println("[Press Enter to continue]");
                inputValue.nextLine();
                break;
            case "3":
                dominoEditor.deleteStoneForm();
                System.out.println("[Press Enter to continue]");
                inputValue.nextLine();
                break;
            case "4":
                dominoEditor.calculateSolution();
                System.out.println("[Press Enter to continue]");
                inputValue.nextLine();
                break;
            case "5":
                dominoEditor.overWriteFileAndArrayWithDummyData();
                System.out.println("[Press Enter to continue]");
                inputValue.nextLine();
                break;
            case "X":
            case "x":
                System.out.println("System shut down");
                break;
            default:
                System.out.println("Please enter a valid value");
                System.out.println("[Press Enter to continue]");
                inputValue.nextLine();
        }
    }
}
