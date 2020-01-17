import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

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


    //This function is needed to display all stones (COULD BE BETTER)
    public void printAllStones() {
        if (dominoArrayIsNotEmpty()){
            int count = 0;
            while (count < allStones.size()) {
                for (int x = 0; x < 5; x++) {
                    if (count == allStones.size())
                        break;
                    if (count < 9)
                        System.out.print("[0" + (count + 1) + "] ");
                    else
                        System.out.print("[" + (count + 1) + "] ");
                    System.out.print(allStones.get(count).toString());
                    System.out.print("\t");
                    count++;
                }
                System.out.println();
            }
        }
    }


    //This two functions are needed to add a stone (UNFINISHED)
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

    private boolean addStone(Dominostone d){
        if(allStones.add(d)){
            adjustStones();
            allStones.sort(new DominoComparator());
            if(fileEditor.convertArrayToFile(allStones)){
                if(adjustArray())
                    return true;
            }
        }
        return false;
    }


    //This two methods are needed to delete a stone (EXTEND TO SHOW IF IT COULDNT BE DELETED BECAUSE THE USERENTRY WAS WRONG)
    public void deleteStoneForm(){
        printAllStones();
        System.out.println("Select which stone you want to remove:");
        int deleteStone = Integer.parseInt(inputValue.nextLine());
        deleteStone(deleteStone);
    }

    private void deleteStone(int deleteStone){
        allStones.remove(deleteStone - 1);
        fileEditor.convertArrayToFile(allStones);
        adjustArray();
    }


    //performs the calculation
    public void calculateSolution(){
        if(dominoArrayIsNotEmpty()){
            String confirmation = "";
            //if the array contains more then 15 stones it prints a warning, that it could take a while
            if (allStones.size() > 15) {
                System.out.println("The calculation could take a while with such an amount of dominos\n If you want to adjust your dominostones enter 'x'");
                confirmation = inputValue.nextLine();
            }
            if (!confirmation.toLowerCase().equals("x"))
                dominoCalculator.printAllPossibilities();
        }
    }

    //overwrites the array and the userfile with some dummydata
    public void overWriteFileAndArrayWithDummyData(){
        fileEditor.useDummyData();
        fileEditor.readDominosFile(allStones);
    }

    //updates the array containing all Dominostones
    public boolean adjustArray(){
        return fileEditor.readDominosFile(allStones);
    }

    //tests if the array containing all Dominostones, if not it prints a warning
    public boolean dominoArrayIsNotEmpty(){
        if(!allStones.isEmpty()) {
            return true;
        } else{
            System.err.println("Your Array is empty");
            return false;
        }
    }

    public void adjustStones(){
        for(Dominostone d : allStones){
            if(!(d.getLeftField() <= d.getRightField()))
                d.turnFields();
        }
    }







    //Getters and Setters
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
