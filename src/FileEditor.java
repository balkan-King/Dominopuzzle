import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileEditor {

    private String dummyDataPath;
    private String dominoPath;

    public FileEditor(String dummyDataPath, String dominoPath) {
        this.dummyDataPath = dummyDataPath;
        this.dominoPath = dominoPath;
    }

    public ArrayList<Dominostone> readDominosFile() {
        ArrayList<Dominostone> allStones = new ArrayList<>();
        Scanner dominosFile;
        try{
            dominosFile = new Scanner(new File(dominoPath));
            while(dominosFile.hasNextLine()){
                String[] fields = dominosFile.nextLine().split(":");
                Dominostone dominostone = new Dominostone(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
                allStones.add(dominostone);
            }
            dominosFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allStones;
    }

    //converts the dominoarray to file
    public void convertArrayToFile(ArrayList<Dominostone> allStones){
        File oldData = new File(dominoPath);
        if(deleteOldFile(oldData)){
            File newData = new File(dominoPath);
            if(createNewFile(newData)){
                try {
                    BufferedWriter copyToWriter = new BufferedWriter(new FileWriter(newData));
                    for(int x = 0; x <allStones.size(); x++){
                        copyToWriter.append(allStones.get(x).printInFile());
                    }
                    copyToWriter.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //The following functions are needed to copy the dummydata into the normal file
    public void useDummyData() {
        File oldData = new File(dominoPath);
        File dummyFile = new File(dummyDataPath);
        if(deleteOldFile(oldData)){
            File newData = new File(dominoPath);
            if(createNewFile(newData)){
                if(copyData(dummyFile, newData)){
                    System.out.println("Dummydata was successfully applied");
                }
            }
        }
    }

    public boolean copyData(File source, File copyTo){
        try {
            FileInputStream sourceStream = new FileInputStream(source);
            FileOutputStream copyToStream = new FileOutputStream(copyTo);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = sourceStream.read(buffer)) > 0) {

                copyToStream.write(buffer, 0, length);
            }

            sourceStream.close();
            copyToStream.close();
            return true;
        } catch(FileNotFoundException e){
            System.out.println("File was not found");
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean createNewFile(File newFile){
        try{
            if(newFile.createNewFile())
                return true;
            System.out.println("The File already exists");
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOldFile(File oldData){
        if(oldData.delete())
            return true;
        System.out.println("Old File could not be deleted");
        return false;
    }
}
