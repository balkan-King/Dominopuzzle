/**
 * This class represents a dominostone.
 * These are needed to perform the calculation and the algoritm of the class
 */
public class Dominostone{
    /**
     * Variable representing the left field of the dominostone
     * usually saves the smaller value of the sides in itself
     */
    private int leftField;
    /**
     * Variable representing the right field of the dominostone
     * usually saves the bigger value of the sides in itself
     */
    private int rightField;
    /**
     * Variable that knows if the stone was already set in the calculation
     */
    private boolean isSet;
    /**
     * Varible that shows if smallerField and biggerField are the same
     * if they are canTurn is false otherwise true
     */
    private boolean canTurn;

    public Dominostone(int leftField, int rightField){
        this.leftField = leftField;
        this.rightField = rightField;
        isSet = false;
        if(leftField == rightField)
            canTurn = false;
        else
            canTurn = true;
    }

    /**
     * @return string that prints a dominostone on the screen
     */
    public String toString(){
        return "| " + leftField + " | " + rightField + " |";
    }

    /**
     * @return string that can save a dominostone in a file, corresponding the FileEditor algorithm.
     */
    public String printInFile(){
        return leftField + ":" + rightField + "\n";
    }

    /**
     * Method that changes the fields so that we're able to turn the stone
     */
    public void turnFields(){
        int save = leftField;
        leftField = rightField;
        rightField = save;
    }

    /**
     * The lower methods are getters and setters
     * They return private fields or save values for private fields
     */
    public int getLeftField() {
        return leftField;
    }

    public void setLeftField(int leftField) {
        this.leftField = leftField;
    }

    public int getRightField() {
        return rightField;
    }

    public void setRightField(int rightField) {
        this.rightField = rightField;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public boolean isCanTurn() {
        return canTurn;
    }

    public void setCanTurn(boolean canTurn) {
        this.canTurn = canTurn;
    }

}
