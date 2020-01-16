import java.util.ArrayList;

public class DominoController {

    private ArrayList<Dominostone> allStones = new ArrayList<>();

    public DominoController(ArrayList<Dominostone> allStones) {
        this.allStones = allStones;
    }

    public void showAllStones(){
        int count = 0;
        while(count < allStones.size()){
            for(int x = 0; x < 5; x++){
                if(count == allStones.size())
                    break;
                if(count < 9)
                    System.out.print("[0" + (count + 1) + "] ");
                else
                    System.out.print("[" + (count + 1) + "] ");
                allStones.get(count).print();
                System.out.print("\t");
                count++;
            }
            System.out.println();
        }
    }

    public void deleteStone(int deleteStone){
        allStones.remove(deleteStone - 1);
        //delete from file
    }
}
