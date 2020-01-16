import java.util.ArrayList;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) {
        String exit = "0";

        Scanner inputValue = new Scanner(System.in);
        ArrayList<Dominostone> allStones = FileEditor.readDominosFile("src\\Dominos.txt");
        DominoCalculator dominoCalculator = new DominoCalculator();

        do {
            showMenu();
            exit = inputValue.nextLine();
            switchCase(exit, allStones, dominoCalculator, inputValue);
        } while (!exit.toLowerCase().equals("x"));
    }

    public static void showMenu() {
        System.out.println("[1] to see all your Dominos\n" +
                "[2] to add a new Domino\n" +
                "[3] to remove a Domino\n" +
                "[4] to execute the calculation\n" +
                "[5] to overwrite with some dummydata\n" +
                "[x] to turn off the application");
    }

    public static void switchCase(String userInput, ArrayList<Dominostone> allStones, DominoCalculator dominoCalculator, Scanner inputValue) {
        DominoController dominoController = new DominoController(allStones);
        System.out.println("\n".repeat(20));
        switch (userInput) {
            case "1":
                dominoController.showAllStones();
                inputValue.nextLine();
                break;
            case "2":
                //function to add
                inputValue.nextLine();
                allStones = FileEditor.readDominosFile("src\\Dominos.txt");
                break;
            case "3":
                if (!allStones.isEmpty()){
                    dominoController.showAllStones();
                    System.out.println("Select which stone you want to remove:");
                    int deleteStone = Integer.parseInt(inputValue.nextLine());
                    dominoController.deleteStone(deleteStone);
                    inputValue.nextLine();
                    allStones = FileEditor.readDominosFile("src\\Dominos.txt");
                }else{
                    System.out.println("Your Array is empty");
                }
                break;
            case "4":
                String confirmation = "";
                if(allStones.size() > 15){
                    System.out.println("The calculation could take a while with such an amount of dominos\n If you want to adjust your dominostones enter 'x'");
                    confirmation = inputValue.nextLine();
                }
                if(!confirmation.toLowerCase().equals("x"))
                    dominoCalculator.printAllPossibilities(allStones);
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