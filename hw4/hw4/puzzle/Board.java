package hw4.puzzle;

public class Board implements Comparable<Board> {
    private int[][] board;
    private int length;
    protected int moves;

    public Board(int[][] tiles) {
        board = tiles;
        length = tiles[0].length;
        moves = 0;
    }

    public Board(int[][] tiles, int moves) {
        board = tiles;
        length = tiles[0].length;
        this.moves = moves;
    } 

    public int moves() {
        return moves;
    }

    public int tileAt(int i, int j) {
        if(i < 0 || i >= length || j < 0 || j >= length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    public int size() {
    return length;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(i*length+j+1 <= 8 && board[i][j] != (i*length+j+1)) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    public int getRow(int num, int row) {
        if(num > length) {
            getRow(num, row++);
        }
        return row;
    }

    public int getCol(int num, int col) {
        int row = this.getRow(num, 0);
        return num - 1 - row*length;
    }

    /** get manhattan for a board */
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int num = board[i][j];
                manhattan += Math.abs(i - this.getRow(num, 0)) + Math.abs(j - this.getCol(num, 0));
            }
        }
        return manhattan;
    }

    public boolean isGoal() {
        if(hamming() == 0) {
            return true;
        }
        return false;
    }

    public boolean equals(Board y) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(board[i][j] != y.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int compareTo(Board board2) {
        int priority1 = this.moves + this.manhattan();
        int priority2 = board2.moves + board2.manhattan();
        if(priority1 > priority2) {
            return 1;
        }
        return -1;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
