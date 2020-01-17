import java.util.ArrayList;

/**
 * This is the heart of the programm.
 * This class contains the whole logic for the calculation.
 */
public class DominoCalculator {

    private ArrayList<Dominostone> orderedDominos = new ArrayList<>();
    private FileEditor fileEditor;
    ArrayList<Dominostone> unorderedDominos = new ArrayList<>();

    public DominoCalculator(FileEditor fileEditor) {
        this.fileEditor = fileEditor;
    }

    /**
     * Executes generateNextArray with all dominos in unorderedDominos as parameter.
     * It also checks if the stone can be turned, and tries it again if so.
     */
    public void printAllPossibilities(){
        fileEditor.readDominosFile(unorderedDominos);
        for(Dominostone d : unorderedDominos) {
            generateNextArray(d, unorderedDominos);
            if(d.turnFields())
                generateNextArray(d, unorderedDominos);
        }
    }

    /**
     * Generates all possible solutions with dominostone as first object and remainingStones as the following.
     * @param dominostone is the first object in the ordereddominos array.
     * @param remainingStones is an array containing all objects, that are needed to be added to ordereddominos for the correct solution.
     */
    private void generateNextArray(Dominostone dominostone, ArrayList<Dominostone> remainingStones){
        remainingStones = createArrayWithoutObject(dominostone, remainingStones);
        addStoneToArray(dominostone);
        for(Dominostone d : remainingStones){
            if(stoneCanBeAdded(d)) {
                generateNextArray(d, remainingStones);
            }
            else if(!d.isSet() && d.canTurn()){
                d.turnFields();
                if(stoneCanBeAdded(d)) {
                    generateNextArray(d, remainingStones);
                }
            }
        }
        if(ifArrayContainsAnswer(unorderedDominos))
            printDominoArray();
        removeStoneFromArray(dominostone);
    }

    /**
     * Returns a new Array that is identical to remainingStones without the given Dominostone object.
     * @param dominoStone object in remainingStones.
     * @param remainingStones .
     * @return remainingStones as new Array without dominoStone.
     */
    public ArrayList<Dominostone> createArrayWithoutObject(Dominostone dominoStone, ArrayList<Dominostone> remainingStones){
        remainingStones = new ArrayList<>(remainingStones);
        remainingStones.remove(dominoStone);
        return remainingStones;
    }

    /**
     * Checks if the ordereddominos array contains a solution.
     * That is indicated by having the same size as the unorderedDominos array.
     * @param unorderedDominos .
     * @return true if the ordereddominos array contains a solution.
     */
    private boolean ifArrayContainsAnswer(ArrayList<Dominostone> unorderedDominos){
        return (orderedDominos.size() == unorderedDominos.size());
    }

    /**
     * Removes the given stone from the ordereddominos array and sets the attribute isSet to false.
     * @param dominostone .
     */
    private void removeStoneFromArray(Dominostone dominostone){
        orderedDominos.remove(dominostone);
        dominostone.setSet(false);
    }

    /**
     * Tests if the dominostone can be added to the ordereddominos array.
     * The conditions are that the stone is not set already and doesMatch() returns true.
     * @param dominostone .
     * @return true if it can be added, false if not.
     */
    private boolean stoneCanBeAdded(Dominostone dominostone){
        return !dominostone.isSet() && doesMatch(dominostone);
    }

    /**
     * Tests if the left field of the given parameter matches,
     * with the right field of the last entry in the ordereddominos array.
     * @param dominostone .
     * @return true if they match, false if not.
     */
    private boolean doesMatch(Dominostone dominostone){
        return orderedDominos.get(orderedDominos.size() - 1).getRightField() == dominostone.getLeftField();
    }

    /**
     * Adds the given stone into the ordereddominos array and sets the attribute isSet to true.
     * @param dominostone .
     */
    private void addStoneToArray(Dominostone dominostone){
        orderedDominos.add(dominostone);
        dominostone.setSet(true);
    }

    /**
     * Gets executed as soon as all dominos are set in the array.
     * prints the array onto screen and into a file.
     */
    private void printDominoArray(){
        for (Dominostone d : orderedDominos) {
            System.out.print(d.toString());
            fileEditor.printSolution(d);
        }
        System.out.println();
        fileEditor.addLineSeperator();
    }
}