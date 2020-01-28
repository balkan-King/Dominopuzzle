import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the logic for handling with the files.
 * It can read from files write into files and everything besides that.
 */
public class FileEditor {

    /**
     * Variable which directs to the file containing some default dominos, which can be copied into the.
     */
    private String defaultDataPath;
    /**
     * Variable which directs to the file containing the dominos inside.
     */
    private String dominoPath;
    /**
     * Variable which direct to the file where the result of the calculation will be printed in at the end.
     */
    private String resultFile;

    public FileEditor(String defaultDataPath, String dominoPath, String resultFile) {
        this.defaultDataPath = defaultDataPath;
        this.dominoPath = dominoPath;
        this.resultFile = resultFile;
        File endResult = new File(resultFile);
        deleteFileIfAlreadyExistsThenCreate(endResult);
    }

    /**
     * Creates a file, it already exists it gets deleted.
     * @param file that gets deleted and created.
     * @returnn true if the creation was successful, false if not.
     */
    public boolean deleteFileIfAlreadyExistsThenCreate(File file){
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Reads the dominofile and puts the data into the given arraylist.
     * @param allStones arraylist in which the data gets extracted.
     * @return true if the creation was successful, false if not.
     */
    public void readDominosFile(ArrayList<Dominostone> allStones) {
        allStones.removeAll(allStones);
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
    }

    /**
     * Copies the given arraylist into the dominoFile to overwrite it.
     * @param allStones ArrayList.
     * @return true if the creation was successful, false if not.
     */
    public boolean convertArrayToFile(ArrayList<Dominostone> allStones){
        File oldData = new File(dominoPath);
        if(deleteOldFile(oldData)){
            if(createNewFile(oldData)){
                try {
                    BufferedWriter copyToWriter = new BufferedWriter(new FileWriter(oldData));
                    for(int x = 0; x <allStones.size(); x++){
                        copyToWriter.append(allStones.get(x).printInFile());
                    }
                    copyToWriter.close();
                    return true;
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * Overwrites the dominoFile with the given default data.
     */
    public void useDefaultData() {
        File dominoFile = new File(dominoPath);
        File defaultFile = new File(defaultDataPath);
        if(deleteOldFile(dominoFile)){
            if(createNewFile(dominoFile)){
                if(copyData(defaultFile, dominoFile)){
                    System.out.println("Defaultdata was successfully applied");
                }
            }
        }
    }

    /**
     * Copies the data from the source File to the copyTo File.
     * @param source File.
     * @param copyTo File.
     * @return true if the creation was successful, false if not.
     */
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

    /**
     * Tries to create the file given as parameter.
     * @param file .
     * @return true if it could be created.
     */
    public boolean createNewFile(File file){
        try{
            if(file.createNewFile())
                return true;
            System.out.println("The File already exists");
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Tries to delete the file given as parameter.
     * @param file .
     * @return true if it could be deleted.
     */
    public boolean deleteOldFile(File file){
        if(file.delete())
            return true;
        System.out.println("File could not be deleted");
        return false;
    }

    /**
     * Prints dominostone into the solutionFile.
     * @param dominostone .
     */
    public void printSolution(Dominostone dominostone){
        try {
            FileWriter fileWriter = new FileWriter(resultFile, true);
            fileWriter.append(dominostone.toString());
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds a new line into the solutionFile.
     */
    public void addLineSeperator(){
        try {
            FileWriter fileWriter = new FileWriter(resultFile, true);
            fileWriter.append("\n");
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes and creates the file again
     */
    public void recreateFile(){
        deleteOldFile(new File(resultFile));
        createNewFile(new File(resultFile));
    }


    /**
     * The lower methods are getters and setters.
     * They return private fields or save values for private fields.
     */
    public String getDefaultDataPath() {
        return defaultDataPath;
    }

    public void setDefaultDataPath(String defaultDataPath) {
        this.defaultDataPath = defaultDataPath;
    }

    public String getDominoPath() {
        return dominoPath;
    }

    public void setDominoPath(String dominoPath) {
        this.dominoPath = dominoPath;
    }

    public String getResultFile() {
        return resultFile;
    }

    public void setResultFile(String resultFile) {
        this.resultFile = resultFile;
    }
}
