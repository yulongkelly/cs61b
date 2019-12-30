package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int length;
	private int openSite;
	private int[][] array2d;
	private WeightedQuickUnionUF disjointSet;

	public Percolation(int N) {
		array2d = new int[N][N];
		openSite = 0;
		length = N; // never change
		disjointSet = new WeightedQuickUnionUF(N*N+1);
		// connect 0 to the first row
		// for(int i =1; i<=N; i++) {
		// 	disjointSet.union(0,i);
		// }
		// connect the last position to the last row
		// for(int i = N*N; i>N*N-N; i--) {
		// 	disjointSet.union(N*N+1, i);
		// }
	}

	/** the num of each box. */
	public int getNum(int row, int col) {
		return row*length+col+1;
	}

	public void myUnionMiddle(int row, int col) {
		int num = getNum(row, col);
		if(num-length >= 1 && isOpen(row-1, col)) {
			disjointSet.union(num, num-length);
		}
		if(num+length <= length*length && isOpen(row+1, col)) {
			disjointSet.union(num, num+length);
		}
		if(col >=1 && isOpen(row, col-1)) {
			disjointSet.union(num, num-1);
		}
		if(col < length-1 && isOpen(row, col+1)) {
			disjointSet.union(num, num+1);
		}
	}

	/* 1 means open */
   	public void open(int row, int col) {
   		if(row == 0) {
   			array2d[row][col] = 2;
   			disjointSet.union(0, getNum(row, col));
   			if(openSite >= 1) {
   				myUnionMiddle(row, col);
   				disjointSet.union(0, getNum(row, col));
   			}
   		}
   		else {
   			array2d[row][col] = 1;
   			if(openSite >= 1) {
   				myUnionMiddle(row, col);
   			}
   			// if(row == length-1) {
   			// 	disjointSet.union(length*length+1, getNum(row, col));
   			// }
   		}
   		openSite++;
   	}

   	public boolean isOpen(int row, int col) {
   		if(array2d[row][col] != 0) {
			return true;
		}
		return false;
   	} 
   	public boolean isFull(int row, int col) {
   		if(disjointSet.connected(getNum(row, col), 0)) {
   			if(row == length-1) {
   				
   			}
			return true;
		}
   		return false;
   	}  
   	public int numberOfOpenSites() {
   		return openSite;
   	}

   	public boolean percolates() {
   		for(int i = length*length; i>length*length-length; i--) {
   			if(disjointSet.connected(0, i)) {
   				return true;
   			}
   		}
   		return false;
   	}            
   	public static void main(String[] args) {
   		Percolation perc = new Percolation(4);
   		perc.open(1,2);
   		perc.open(2, 2);
   		// System.out.println(perc.disjointSet.connected(perc.getNum(1,2), perc.getNum(2,2)));
  //  		int length = perc.length;
  //  		for(int i = length*length; i>(length*length-length); i--) {
		// 	if(!perc.disjointSet.connected((length*length+1), i)) {
		// 		System.out.println("false");
		// 	}
		// }
		perc.open(0,2);
		System.out.println(perc.disjointSet.connected(0, perc.getNum(1,2)));
		System.out.println(perc.isFull(1,2));
		System.out.println(perc.isOpen(0,0)); // false
		System.out.println(perc.isOpen(0,3)); //false
		perc.open(3,2);
		System.out.println(perc.numberOfOpenSites());
   	}
}                       
