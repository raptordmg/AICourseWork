import java.util.*;

import static java.lang.Math.abs;

public class PathFinder {
    private final List<List<Integer>> caves;
    private final List<Integer> start;
    private final List<Integer> target;
    private final FileHandling file;

    public PathFinder(List<List<Integer>> caves, List<Integer> start, List<Integer> target, FileHandling file) {
        this.caves = caves;
        this.start = start;
        this.target = target;
        this.file = file;
    }

    private List<List<Integer>> getPossibleMoves(int index, ArrayList<List<Integer>> visitedList) {
        List<Integer> indexes = new LinkedList<>();
        for (List<Integer> connection:file.getConnections()) {
            if (connection.get(index) == 1) {
                indexes.add(file.getConnections().indexOf(connection));
            }
        }

        List<List<Integer>> possibleMoves = new LinkedList<>();
        for (int i = 0; i < indexes.size(); i++) {
            if (!(visitedList.contains(caves.get(indexes.get(i))))) {
                possibleMoves.add(caves.get(indexes.get(i)));
            }
        }

        return possibleMoves;
    }

    private boolean checkAddCave(ArrayList<Cave> openSet, Cave move) {
        for (Cave cave:openSet){
            if ((move == cave) && (move.getF() >= cave.getF())) {
                return false;
            }
        }
        return true;
    }

    public List<Cave> findOptimalPath() {
        Cave startCave = new Cave(start, null);
        Cave targetCave = new Cave(target, null);

        ArrayList<Cave> openSet = new ArrayList<>();
        openSet.add(startCave);
        ArrayList<List<Integer>> visitedList = new ArrayList<>();

        while (openSet.size() != 0) {
            Cave currentCave = openSet.get(0);
            openSet.remove(0);

            if (currentCave.getCoords().equals(targetCave.getCoords())) {
                List<Cave> path = new LinkedList<>();
                while (currentCave != startCave) {
                    path.add(currentCave);
                    currentCave = currentCave.getParent();
                }
                path.add(startCave);
                Collections.reverse(path);
                return path;
            }

            List<List<Integer>> possibleMoves;
            possibleMoves = getPossibleMoves(caves.indexOf(currentCave.getCoords()), visitedList);

            for (List<Integer> cave: possibleMoves){
                //find best move then make cave
                Cave move = new Cave(cave,currentCave);

                if (visitedList.contains(move.getCoords())){
                    continue;
                }

                visitedList.add(move.getCoords());

                move.setG(abs(move.getCoords().get(0) - startCave.getCoords().get(0)) + abs(move.getCoords().get(1) - startCave.getCoords().get(1)));
                move.setH(abs(move.getCoords().get(0) - targetCave.getCoords().get(0)) + abs(move.getCoords().get(1) - targetCave.getCoords().get(1)));
                move.setF(move.getG() + move.getH());

                if (checkAddCave(openSet, move)) {
                    openSet.add(move);
                }
            }
        }
        return null;
    }
}