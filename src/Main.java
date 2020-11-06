import java.util.List;

public class Main {

    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();
        FileHandling file = new FileHandling("generated30-1.cav");
        List<Integer> start = file.getCaves().get(0);
        List<Integer> target = file.getCaves().get(file.getNumCaves()-1);

        List<Cave> optimalCoords = new PathFinder(file.getCaves(),start,target, file).findOptimalPath();

        if (optimalCoords != null) {
            System.out.println(new Cave().getCaveNums(optimalCoords, file.getCaves()));
            System.out.println(optimalCoords.size());
        }else{
            System.out.println("null");
        }

        double stopTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + ((stopTime - startTime) / 1000));
    }
}
