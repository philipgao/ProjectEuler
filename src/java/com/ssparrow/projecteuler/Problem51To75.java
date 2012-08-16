/**
 * 
 */
package com.ssparrow.projecteuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author Philip
 *
 */
public class Problem51To75 {

	public static int p067FindFindMaxPathInMatrix(String filename, int size) throws IOException{
		BufferedReader reader=new BufferedReader(new FileReader(filename));
		int [][] matrix = new int[size][];
		int [][] result = new int[size][];

		int rowIndex=0;
		String line;
		while((line=reader.readLine())!=null){
			StringTokenizer st=new StringTokenizer(line, " ");
			int [] row=new int[st.countTokens()];
			int [] resultRow=new int[st.countTokens()];
			
			int columnIndex=0;
			while(st.hasMoreElements()){
				String element = (String) st.nextElement();
				int value=Integer.parseInt(element);
				row[columnIndex++]=value;
			}
			
			matrix[rowIndex]=row;
			result[rowIndex]=resultRow;
			
			rowIndex++;
		}
		
		findFindMaxPathInMatrix(matrix, result, 0, 0, 0);
		
		int max=0;
		for(int i=0;i<result[size-1].length;i++){
			if(result[size-1][i]>max){
				max=result[size-1][i];
			}
		}
		
		return max;
	}
	
	public static void findFindMaxPathInMatrix(int[][] matrix, int [][] result, int row, int column, int sum){
		if(row>=matrix.length || result[row][column]>=sum+matrix[row][column]){
			return;
		}
		
		sum=sum+matrix[row][column];
		result[row][column]=sum;
		
		if(row==matrix.length-1){
			return;
		}
		
		findFindMaxPathInMatrix(matrix, result, row+1, column, sum);
		findFindMaxPathInMatrix(matrix, result, row+1, column+1, sum);
	}
}
