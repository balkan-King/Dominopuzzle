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
     * Method which shows the menu and saves the entry of the user.
     * @return variable which saved the userentry in itself.
     */
    public String showMenuAndGetUserInput() {
        System.out.println("[1] to see all your Dominos\n" +
                "[2] to add a new Domino\n" +
                "[3] to remove a Domino\n" +
                "[4] to execute the calculation\n" +
                "[5] to overwrite with some dummydata\n" +
                "[x] to turn off the application");
        String userInput = inputValue.nextLine();
        System.out.println();
        return userInput;
    }

    /**
     * Method which contains a switch-case redirecting the user to the particular methodlogic.
     * @param userInput is the entry of the user.
     */
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
