import org.apache.commons.collections4.ListUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHandling {
    private String rawData = "";
    private int[] data;
    private String fileName = "";

    public FileHandling(String fileName) {
        this.fileName = fileName;
    }

    private void openFile() {
        try {
            File caverns = new File(fileName);
            Scanner myReader = new Scanner(caverns);
            while (myReader.hasNextLine()){
                rawData = myReader.next();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }

        String[] array = rawData.split(",");
        data = Arrays.asList(array).stream().mapToInt(Integer::parseInt).toArray();
    }

    public int getNumCaves(){
        checkDataAdded();
        return data[0];
    }

    public List<List<Integer>> getCaves(){
        checkDataAdded();
        int[] rawCaves = Arrays.copyOfRange(data, 1, data[0]*2+1);
        Integer[] rawCavesInteger = Arrays.stream( rawCaves ).boxed().toArray( Integer[]::new );

        List<Integer> caves = Arrays.asList(rawCavesInteger);
        List<List<Integer>> caveCoords = ListUtils.partition(caves, 2);

        return caveCoords;
    }

    public List<List<Integer>> getConnections(){
        checkDataAdded();
        int[] rawConnections = Arrays.copyOfRange(data, data[0]*2+1, data.length);
        Integer[] rawConnectionsInteger = Arrays.stream( rawConnections ).boxed().toArray( Integer[]::new );

        List<Integer> connections = Arrays.asList(rawConnectionsInteger);
        List<List<Integer>> caveConnections = ListUtils.partition(connections, data[0]);

        return caveConnections;
    }

    private void checkDataAdded() {
        if (data == null){
            openFile();
        }
    }

    public void outputResult(List<Integer> results) {
        try{
            FileWriter outputFile = new FileWriter(fileName.substring(0, fileName.length()-4) + ".csn");
            String output = "";
            for (int result: results){
                output = output + result + " ";
            }
            outputFile.write(output);
            outputFile.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
