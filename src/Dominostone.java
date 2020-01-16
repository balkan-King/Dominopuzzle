public class Dominostone{

    private int leftField;
    private int rightField;
    private boolean isSet;
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

    public void print(){
        System.out.print("| " + leftField + " | " + rightField + " |");
    }

    public String printInFile(){
        return leftField + ":" + rightField + "\n";
    }

    public void turnFields(){
        int save = leftField;
        leftField = rightField;
        rightField = save;
    }

    //getters and setters
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
