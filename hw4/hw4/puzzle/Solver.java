package hw4.puzzle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Iterator;
import edu.princeton.cs.algs4.Queue;
import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

public class Solver {
    MinPQ<Board> boards = new MinPQ<>();
    private Stack<Board> pathSolution = new Stack<>();
    private int totalMoves;
    private Set<Board> marked = new HashSet<>();

    public Solver(Board initial) {
        boards.insert(initial);
        totalMoves = 0;
        pathSolution = null;
        marked = null;
    }
    
    public static Iterable<Board> neighbors(Board var0) {
        Queue var1 = new Queue();
        int var2 = var0.size();
        int var3 = -1;
        int var4 = -1;

        int var6;
        for(int var5 = 0; var5 < var2; ++var5) {
            for(var6 = 0; var6 < var2; ++var6) {
                if (var0.tileAt(var5, var6) == 0) {
                    var3 = var5;
                    var4 = var6;
                }
            }
        }

        int[][] var9 = new int[var2][var2];

        int var7;
        for(var6 = 0; var6 < var2; ++var6) {
            for(var7 = 0; var7 < var2; ++var7) {
                var9[var6][var7] = var0.tileAt(var6, var7);
            }
        }

        for(var6 = 0; var6 < var2; ++var6) {
            for(var7 = 0; var7 < var2; ++var7) {
                if (Math.abs(var6 - var3) + Math.abs(var7 - var4) == 1) {
                    var9[var3][var4] = var9[var6][var7];
                    var9[var6][var7] = 0;
                    Board var8 = new Board(var9);
                    var1.enqueue(var8);
                    var9[var6][var7] = var9[var3][var4];
                    var9[var3][var4] = 0;
                }
            }
        }

        return var1;
    }

    public static Iterable<Board> getNeighbors(Board board) {
        /** get neighbor boards */
        Iterable<Board> path = neighbors(board);
        return path;
    }

    public Stack<Board> solve(Board min) {
        marked.add(min);
        pathSolution.push(min);
        if(!min.isGoal()) {
            System.out.println("enter ");
            totalMoves++;
            for(Board neighbor: getNeighbors(min)) {
                if(neighbor.isGoal()) {
                    neighbor.moves = min.moves;
                    pathSolution.push(neighbor);
                    return pathSolution;
                }
                else if(!neighbor.equals(min) && !marked.contains(neighbor)) {
                    boards.insert(neighbor);
                }
            }
            Board newMin = boards.min();
            for(Board neighbor: getNeighbors(min)) {
                if(newMin.equals(neighbor)) {
                    solve(newMin);
                }
            }
            pathSolution.pop();
            solve(newMin);
        }
        return pathSolution;
    }

    public int moves() {   
        return totalMoves;
    }

    public Iterable<Board> solution() {
        if(boards.min() == null) {
            System.out.println("min is empty");
            return null;
        }
        Stack<Board> sol = solve(boards.min());
       return sol;
    }
    // DO NOT MODIFY MAIN METHOD
   public static void main(String[] args) {
       In in = new In(args[0]);
       int N = in.readInt();
       int[][] tiles = new int[N][N];
       for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
               tiles[i][j] = in.readInt();
           }
       }
       Board initial = new Board(tiles);
       Solver solver = new Solver(initial);
       StdOut.println("Minimum number of moves = " + solver.moves());
       for (Board board : solver.solution()) {
           StdOut.println(board);
      }
   }

}
