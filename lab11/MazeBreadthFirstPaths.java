import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    Queue<Integer> fringe = new LinkedList<>();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        for(int neighbor: maze.adj(s)) {
            edgeTo[neighbor] = s;
            distTo[neighbor] = distTo[s]+1;
            fringe.add(s);
        }
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int v) {
        marked[v] = true;
        announce();

        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }
        // if target has not been found
        if(fringe != null) {
            int first = fringe.remove();
            for(int neighbor: maze.adj(first)) {
                if (marked[neighbor] == false) {
                    edgeTo[neighbor] = first;
                    fringe.add(neighbor);
                    distTo[neighbor] = distTo[first] + 1;
                }
            }
            bfs(first);
        }
    }


    @Override
    public void solve() {
         bfs(s);
    }
}

