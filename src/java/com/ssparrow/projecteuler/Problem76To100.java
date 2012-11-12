/**
 * 
 */
package com.ssparrow.projecteuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author Gao, Fei
 *
 */
public class Problem76To100 {
	/**
	 * 
	 *  NOTE: This problem is a significantly more challenging version of Problem 81.

		In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by moving left, right, up, and down, 
		is indicated in bold red and is equal to 2297.
		
		
		131	673	234	103	18
		201	96	342	965	150
		630	803	746	422	111
		537	699	497	121	956
		805	732	524	37	331
		
		Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target As...'), 
		a 31K text file containing a 80 by 80 matrix, from the top left to the bottom right by moving left, right, up, and down.
	 * @param fileName
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public static int p083FindMinimalPath(String fileName, int size) throws IOException{
		BufferedReader reader=new BufferedReader(new FileReader(fileName));
		
		int [][] matrix=new int[size][size];
		String line;
		int row=0;
		while((line=reader.readLine())!=null){
			int column=0;
			StringTokenizer st=new StringTokenizer(line,",");
			while(st.hasMoreTokens()&&column<size){
				matrix[row][column]=Integer.parseInt(st.nextToken());
				column++;
			}
			row++;
		}
		
		return p083FindMinimalPath(matrix);
	}
	
	public static int p083FindMinimalPath(int [][] matrix){
		int [][] result=new int[matrix.length][matrix[0].length];
		for(int i=0;i<result.length;i++){
			for(int j=0;j<result[i].length;j++){
				result[i][j]=Integer.MAX_VALUE;
			}
		}
		
		findMinimalPath(matrix, result, 0, 0, 0);
		
		return result[result.length-1][result[result.length-1].length-1];
	}
	
	public static void findMinimalPath(int[][] matrix, int [][] result, int sum, int row, int column){
		if(result[row][column]<=sum+matrix[row][column]){
			return;
		}
		
		sum = sum+matrix[row][column];
		result[row][column] = sum;
		
		if(row==result.length-1 && column==result[row].length-1){
			return;
		}
		
		if(row>0){
			findMinimalPath(matrix, result, sum, row-1, column);
		}
		
		if(row<result.length-1){
			findMinimalPath(matrix, result, sum, row+1, column);
		}
		
		if(column>0){
			findMinimalPath(matrix, result, sum, row, column-1);
		}
		
		if(column<result[row].length-1){
			findMinimalPath(matrix, result, sum, row, column+1);
		}
	}
}
