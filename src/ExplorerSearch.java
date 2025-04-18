import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        boolean[][] visited = new boolean[island.length][island[0].length];
        int[] current = findStart(island);

        return reachableArea(island, visited, current, 0);
    }

    public static int reachableArea(int[][] island, boolean[][] visited, int[] current, int spaces){
        int row = current[0];
        int column = current[1];
        if (visited[row][column]) return 0;

        visited[row][column] = true;

        spaces++;

        for (int[] field : nearbyFields(island, current)){
            spaces = Math.max(reachableArea(island, visited, field, spaces), spaces);
        }
        return spaces;
    }

    /**
     * Finds indices of the space the explorer is starting from
     * represented as 0
     * 
     * throws IllegalArgumentException if no starting point is found
     * 
     * @param island locations on the island
     * @return array of indices ([0] = row, [1] = column) where explorer is starting 
     */
    public static int[] findStart(int[][] island){
        // for each array element, if element = 0, return indices
        for(int r = 0; r < island.length; r++){
            for (int c=0; c < island[0].length; c++){
                if (island[r][c] == 0) return new int[]{r, c};
            }
        }

        // if there was no return in for loop, throw exception
        throw new IllegalArgumentException("Island must have a starting location");
    }

    /**
     * Finds any field spaces that are directly accessible from the given starting point (current)
     * 
     * @param island locations on the island
     * @param current starting location
     * @return List of indices where the explorer can move to (field spaces)
     */
    public static List<int[]> nearbyFields(int[][] island, int[] current){
        // create list to be returned
        List<int[]> spaces = new ArrayList<>();
        // create variables for current row and column
        int rCurr = current[0];
        int cCurr = current[1];

        // check above curr
        int r = rCurr -1;
        int c = cCurr;
        if (r >= 0){
            if (island[r][c] != 2 && island[r][c] != 3){
                spaces.add(new int[]{r, c});
            }
        }

        // check below curr
        r = rCurr + 1;
        c = cCurr;
        if ( r < island.length){
            if (island[r][c] != 2 && island[r][c] != 3){
                spaces.add(new int[]{r, c});
            }
        } 

        // check left
        r = rCurr;
        c = cCurr -1;
        if ( c >= 0){
            if (island[r][c] != 2 && island[r][c] != 3){
                spaces.add(new int[]{r, c});
            }
        }     

        // check right
        r = rCurr;
        c = cCurr + 1;
        if ( c < island[0].length){
            if (island[r][c] != 2 && island[r][c] != 3){
                spaces.add(new int[]{r, c});
            }
        }

        return spaces;
    }

}
