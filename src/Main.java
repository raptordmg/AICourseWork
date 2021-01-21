import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileHandling file = new FileHandling(System.getProperty("user.dir")+ "\\" + args[0]+".cav"); //Test
        List<Integer> start = file.getCaves().get(0);
        List<Integer> target = file.getCaves().get(file.getNumCaves()-1);
        List<Cave> optimalCoords = new PathFinder(file.getCaves(),start,target, file).findOptimalPath();
        List<Integer> output = new LinkedList<>();

        if (optimalCoords != null) {
            output = new Cave().getCaveNums(optimalCoords, file.getCaves());
            file.outputResult(output);
        }else{
            output.add(0);
            file.outputResult(output);
        }
    }
}
