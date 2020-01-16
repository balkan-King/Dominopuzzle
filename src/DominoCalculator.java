import java.util.ArrayList;

public class DominoCalculator {

    private ArrayList<Dominostone> orderedDominos = new ArrayList<>();

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

    private void printDominoArray(){
        for (Dominostone d : orderedDominos) {
            d.print();
        }
        System.out.println();
    }

    private boolean stoneCanBeAdded(ArrayList<Dominostone> allOtherStones, int x){
        if(!allOtherStones.get(x).isSet() && doesMatch(allOtherStones.get(x)))
            return true;
        else
            return false;
    }

    private boolean doesMatch(Dominostone actualDominostone){
        if(orderedDominos.get(orderedDominos.size() -1).getRightField() == actualDominostone.getLeftField())
            return true;
        else
            return false;
    }
}