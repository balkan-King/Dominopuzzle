import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains all methods of the program that have nothing to do with the calculation or the filehandling.
 */
public class DominoEditor {

    private ArrayList<Dominostone> allStones = new ArrayList<>();
    private DominoCalculator dominoCalculator;
    private FileEditor fileEditor;
    private Scanner inputValue = new Scanner(System.in);

    public DominoEditor(String dominoPath, String dummyDataPath, String resultFile) {
        fileEditor = new FileEditor(dummyDataPath, dominoPath, resultFile);
        dominoCalculator = new DominoCalculator(fileEditor);
        fileEditor.readDominosFile(allStones);
    }


    /**
     * Prints all dominostones to the screen with the position in the array.
     */
    public void printAllStones() {
        if (dominoArrayIsNotEmpty()){
            int count = 0;
            while (count < allStones.size()) {
                for (int x = 0; x < 5; x++) {
                    if (count == allStones.size())
                        break;
                    printToScreen(count);
                    count++;
                }
                System.out.println();
            }
        }
    }

    /**
     * Prints the dominostone to the screen with the position in the array.
     * @param count position in allStones array.
     */
    public void printToScreen(int count){
        if (count < 9)
            System.out.print("[0" + (count + 1) + "] ");
        else
            System.out.print("[" + (count + 1) + "] ");
        System.out.print(allStones.get(count).toString());
        System.out.print("\t");
    }

    /**
     * Asks the user, how the new stone should look like, thats going to be added.
     */
    public void addStoneForm(){
        Dominostone dominostone;
        int smallerValue, biggerValue;
        try {

            System.out.println("Whats the smaller value of the domino");
            smallerValue = Integer.parseInt(inputValue.nextLine());
            System.out.println("Whats the bigger value of the domino");
            biggerValue = Integer.parseInt(inputValue.nextLine());
            if(smallerValue <= biggerValue)
                dominostone = new Dominostone(smallerValue, biggerValue);
            else
                dominostone = new Dominostone(biggerValue, smallerValue);

            if(addStone(dominostone))
                System.out.println("A new Dominostone could be created");

        } catch(NumberFormatException e){
            System.out.println("Please enter a valid value");
        }
    }

    /**
     * Adds a new dominostone to the array and the file.
     * @param dominostone is the object that gets added.
     * @return true if the adding was successful, false if not.
     */
    private boolean addStone(Dominostone dominostone){
        if(allStones.add(dominostone)){
            adjustStones();
            allStones.sort(new DominoComparator());
            if(fileEditor.convertArrayToFile(allStones)){
                adjustArray();
                return true;
            }
        }
        return false;
    }

    /**
     * Asks the user which stone to remove.
     */
    public void deleteStoneForm(){
        printAllStones();
        System.out.println("Select which stone you want to remove:");
        try {
            int deleteStone = Integer.parseInt(inputValue.nextLine());
            if (deleteStone(deleteStone))
                System.out.println("The dominostone was successfully deleted");
        }catch(NumberFormatException e){
            System.out.println("Please enter a valid value");
        }
    }

    /**
     * Deletes a stone from the array and the file.
     * @param deleteStone is the object getting deleted.
     */
    private boolean deleteStone(int deleteStone){
        try {
            allStones.remove(deleteStone - 1);
            if (fileEditor.convertArrayToFile(allStones)) {
                adjustArray();
                return true;
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("Please enter a valid value");
        }
        return false;
    }


    /**
     * Executes the calculation and tests that the array doesnt contain too many stones.
     */
    public void calculateSolution(){
        if(dominoArrayIsNotEmpty()){
            String confirmation = "";
            if (allStones.size() > 10) {
                System.out.println("The calculation could take a while with such an amount of dominos\n If you want to adjust your dominostones enter 'x'");
                confirmation = inputValue.nextLine();
            }
            if (!confirmation.toLowerCase().equals("x"))
                dominoCalculator.printAllPossibilities();
        }
    }

    /**
     * Overwrites the file and the array with the given dummydata.
     */
    public void overWriteFileAndArrayWithDummyData(){
        fileEditor.useDummyData();
        fileEditor.readDominosFile(allStones);
    }

    /**
     * Updates the array containing all dominos.
     */
    public void adjustArray(){
        fileEditor.readDominosFile(allStones);
    }

    /**
     * Tests if the array with the dominos contains anything.
     * @return boolean
     */
    public boolean dominoArrayIsNotEmpty(){
        return (!allStones.isEmpty());
    }

    /**
     * Ensures that the left field is smaller then the right.
     */
    public void adjustStones(){
        for(Dominostone d : allStones){
            if(!(d.getLeftField() <= d.getRightField()))
                d.turnFields();
        }
    }


    /**
     * The lower methods are getters and setters.
     * They return private fields or save values for private fields.
     */
    public ArrayList<Dominostone> getAllStones() {
        return allStones;
    }

    public void setAllStones(ArrayList<Dominostone> allStones) {
        this.allStones = allStones;
    }

    public DominoCalculator getDominoCalculator() {
        return dominoCalculator;
    }

    public void setDominoCalculator(DominoCalculator dominoCalculator) {
        this.dominoCalculator = dominoCalculator;
    }

    public FileEditor getFileEditor() {
        return fileEditor;
    }

    public void setFileEditor(FileEditor fileEditor) {
        this.fileEditor = fileEditor;
    }

    public Scanner getInputValue() {
        return inputValue;
    }

    public void setInputValue(Scanner inputValue) {
        this.inputValue = inputValue;
    }
}
