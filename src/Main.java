/**
 * The Main Class contains the main function, which gets executed first, once you start the program.
 * It contains a loop that contains the whole application and gets repeated until the user enters an 'x'.
 *
 * @Author David Pavlic & Suhejl Asani
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Variable which directs to the file containing the dominos inside.
         */
        String dominoPath = "src\\Dominos\\dominos.txt";
        /**
         * Variable which directs to the file containing some dummy dominos, which can be copied into the.
         */
        String dummyDataLocation = "src\\Dominos\\dummydata.txt";
        /**
         * Variable which direct to the file where the result of the calculation will be printed in at the end.
         */
        String resultFile = "src\\Dominos\\solution.txt";
        /**
         * Variable which saves the decision of the user, after seeing the menu.
         */
        String userInput;

        MenuController menuController = new MenuController(dominoPath, dummyDataLocation, resultFile);

        do {
            userInput = menuController.showMenuAndGetUserInput();
            menuController.switchCase(userInput);
        } while (!userInput.toLowerCase().equals("x"));
    }
}