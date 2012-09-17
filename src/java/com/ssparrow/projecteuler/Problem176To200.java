/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gao, Fei
 *
 */
public class Problem176To200 {
	/**
	 *Consider the following configuration of 64 triangles:
	 *http://projecteuler.net/project/images/p_189_grid.gif
	 *
	 *We wish to colour the interior of each triangle with one of three colours: red, green or blue, so that no two neighbouring triangles have the same colour. Such a colouring shall be called valid. Here, two triangles are said to be neighbouring if they share an edge.
	 *Note: if they only share a vertex, then they are not neighbours.
	 *
	 *For example, here is a valid colouring of the above grid:
	 *http://projecteuler.net/project/images/p_189_colours.gif
	 *
	 *A colouring C' which is obtained from a colouring C by rotation or reflection is considered distinct from C unless the two are identical.
	 *
	 *How many distinct valid colourings are there for the above configuration?
	 * @param n
	 * @return
	 */
	public static BigInteger p189GetPossibleColoringPlans(int n){
		Color [][] plan = new Color [n][2*n-1];
		boolean [][] border=new boolean [n][2*n-1];
		
		for(int i=0;i<n;i++){
			boolean hasBorder=true;
			for(int j=n-1-i;j<=n-1+i;j++){
				border[i][j]=hasBorder;
				hasBorder=!hasBorder;
			}
		}
		
		return getPossibleColoringPlans(n, plan, border, 0, n-1);
	}
	
	private static BigInteger getPossibleColoringPlans(int n, Color [][] plan, boolean [][] border,int row, int column) {
		if(row==n-1 && column==2*n-2){
			return BigInteger.valueOf(2);
		}
		
		BigInteger count=BigInteger.ZERO;
		
		boolean hasBorderWithAbove = row==0?false:border[row-1][column];
		
		boolean hasLeftNeighbour= !(column==n-1-row);
		
		Set<Color> availableColors=new HashSet<Color>();
		availableColors.add(Color.RED);
		availableColors.add(Color.GREEN);
		availableColors.add(Color.BLUE);
		
		if(hasBorderWithAbove){
			availableColors.remove(plan[row-1][column]);
		}
		
		if(hasLeftNeighbour){
			availableColors.remove(plan[row][column-1]);
		}
		
		for(Color color:availableColors){
			plan[row][column] = color;
			
			int nextRow = column==n-1+row?row+1:row;
			int nextColumn= column==n-1+row?n-1-(row+1):column+1;
					
			count= count.add(getPossibleColoringPlans(n, plan, border, nextRow, nextColumn));
		}
		
		return count;
	}
}
