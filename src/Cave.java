import java.util.LinkedList;
import java.util.List;

public class Cave {
    private List<Integer> coords;
    private Cave parent;
    private double g;
    private double h;
    private double f;

    public Cave(List<Integer> coords, Cave parent) {
        this.coords = coords;
        this.parent = parent;
        this.g = 0;
        this.h = 0;
        this.f = 0;
    }

    public Cave() {

    }

    public List<Integer> getCoords() {
        return coords;
    }

    public Cave getParent(){
        return parent;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public double getH() {
        return h;
    }

    public double getF() {
        return f;
    }

    public List<Integer> getCaveNums(List<Cave> numsToGet, List<List<Integer>> caves) {
        List<Integer> caveNumbers = new LinkedList<>();
        for (int i = 0; i < numsToGet.size(); i++) {
                if (caves.contains(numsToGet.get(i).getCoords())){
                    caveNumbers.add(caves.indexOf(numsToGet.get(i).getCoords())+1);
                }
        }
        return caveNumbers;
    }

    @Override
    public String toString() {
        return "Cave{" +
                "coords=" + coords +
                '}';
    }
}
