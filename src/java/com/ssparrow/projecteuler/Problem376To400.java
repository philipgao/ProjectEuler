/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;

/**
 * @author Gao, Fei
 *
 */
public class Problem376To400 {
	/**
	 * An nn grid of squares contains n2 ants, one ant per square.
	 * All ants decide to move simultaneously to an adjacent square (usually 4 possibilities, except for ants on the edge of the grid or at the corners).
	 * We define f(n) to be the number of ways this can happen without any ants ending on the same square and without any two ants crossing the same edge between two squares.
	 * 
	 * You are given that f(4) = 88.
	 * Find f(10).
	 * 
	 * @return
	 */
	public static BigInteger p393GetAntRoutes(int n){
		Direction [][] directions=new Direction[n][n];
		
		return findAllAntRoutes(directions, 0, 0);
	}
	
	private static BigInteger findAllAntRoutes(Direction [][] directions, int i, int j){
		int n=directions.length;
		
		if(i==n){
//			for(int r=0;r<n;r++){
//				for(int c=0;c<n;c++){
//					System.out.print(directions[r][c].getExpression()+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
			return BigInteger.ONE;
		}
		
		BigInteger count=BigInteger.ZERO;
		
		int nextRow=(j==n-1?i+1:i);
		int nextColumn=(j==n-1?0:j+1);
		
		if(j>0 && directions[i][j-1]!=Direction.RIGHT &&
				(j==1 || directions[i][j-2]!=Direction.RIGHT)&&
				(i==0 || directions[i-1][j-1]!=Direction.DOWN)){
			directions[i][j]=Direction.LEFT;
			
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		if(i>0 && directions[i-1][j]!=Direction.DOWN &&
				(i==1 || directions[i-2][j]!=Direction.DOWN)&&
				(j==0 || directions[i-1][j-1]!=Direction.RIGHT)&&
				(j==n-1 || directions[i-1][j+1]!=Direction.LEFT)){
			directions[i][j]=Direction.UP;
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		if(j<n-1 && 
				(i==0 || directions[i-1][j+1]!=Direction.DOWN)){
			directions[i][j]=Direction.RIGHT;
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		if(i<n-1){
			directions[i][j]=Direction.DOWN;
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		return count;
		
	}
}
