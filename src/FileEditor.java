import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileEditor {

    public static ArrayList<Dominostone> readDominosFile(String fileLocation) {
        ArrayList<Dominostone> allStones = new ArrayList<>();
        try{
        Scanner dominosFile = new Scanner(new File(fileLocation));
        while(dominosFile.hasNextLine()){
            String[] fields = dominosFile.nextLine().split(":");
            Dominostone dominostone = new Dominostone(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
            allStones.add(dominostone);
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allStones;
    }

}
