import java.util.ArrayList;

public class DominoCalculator {

    private ArrayList<Dominostone> orderedDominos = new ArrayList<>();
    private FileEditor fileEditor;

    public DominoCalculator(FileEditor fileEditor) {
        this.fileEditor = fileEditor;
    }

    public void printAllPossibilities(ArrayList<Dominostone> allStones){
        for(int x = 0; x<allStones.size(); x++) {
            allStones.get(x).setSet(true);
            generateNextArray(allStones.get(x), allStones);
            if(allStones.get(x).isCanTurn()){
                allStones.get(x).turnFields();
                for(int y = 0; y<allStones.size();y++){
                    allStones.get(y).setSet(false);
                }
                orderedDominos = new ArrayList<>();
                allStones.get(x).setSet(true);
                generateNextArray(allStones.get(x), allStones);
            }
            for(int y = 0; y<allStones.size();y++){
                allStones.get(y).setSet(false);
            }
            orderedDominos = new ArrayList<>();
        }
    }

    private void generateNextArray(Dominostone dominostone, ArrayList<Dominostone> allOtherStones){
        orderedDominos.add(dominostone);
        for(int x = 0; x <allOtherStones.size(); x++){
            if(stoneCanBeAdded(allOtherStones, x)) {
                addStoneToArray(allOtherStones, x);
            }
            else if(!allOtherStones.get(x).isSet() && allOtherStones.get(x).isCanTurn()){
                allOtherStones.get(x).turnFields();
                if(stoneCanBeAdded(allOtherStones, x)) {
                    addStoneToArray(allOtherStones, x);
                }
                else
                    allOtherStones.get(x).turnFields();
            }
        }
        if((orderedDominos.get(orderedDominos.size() - 1) == dominostone)) {
            if((orderedDominos.size() == allOtherStones.size())){
                printDominoArray();
            }
            orderedDominos.remove(dominostone);
            dominostone.setSet(false);
        }
    }

    
    private void addStoneToArray(ArrayList<Dominostone> allOtherStones, int x){
        allOtherStones.get(x).setSet(true);
        generateNextArray(allOtherStones.get(x), allOtherStones);
    }

    /**
     * Method that tests if the stone at position x of allOtherStones can be added to the ordereddominos array.
     * The conditions are that the stone is not set already and the sides of both stones match in doesMatch().
     * @param allOtherStones
     * @param x position of the stone in allOtherStones array
     * @return true if they can be added, false if not
     */
    private boolean stoneCanBeAdded(ArrayList<Dominostone> allOtherStones, int x){
        if(!allOtherStones.get(x).isSet() && doesMatch(allOtherStones.get(x)))
            return true;
        else
            return false;
    }

    /**
     * Method that tests if the left field of the given parameter matches,
     * with the right field of the last entry in the ordereddominos array.
     * @param actualDominostone
     * @return true if they match, false if not
     */
    private boolean doesMatch(Dominostone actualDominostone){
        if(orderedDominos.get(orderedDominos.size() -1).getRightField() == actualDominostone.getLeftField())
            return true;
        else
            return false;
    }

    /**
     * Method that gets executed as soon as all dominos are set in the array.
     * prints the array onto screen and into a file.
     */
    private void printDominoArray(){
        for (Dominostone d : orderedDominos) {
            System.out.println(d.toString());
            fileEditor.printSolution(d);
        }
        System.out.println();
        fileEditor.addLineSeperator();
    }

}