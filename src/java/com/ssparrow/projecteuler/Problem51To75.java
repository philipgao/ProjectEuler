/**
 * 
 */
package com.ssparrow.projecteuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
	
	/**
	 * Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of positive numbers less than or equal to n which are relatively prime to n. 
	 * For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.
	 * The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.
	 * 
	 * Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
	 * 
	 * Find the value of n, 1  n  107, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.
	 * 
	 * @param limit
	 * @return
	 */
	public static int p070FIndMinRatioPermutedTotient(int limit){
		int [] primeList=PrimeUtil.getAllPrimeBelowN(limit);
		
		double minRatio=1;
		int result=0;
		
		int number=3;
		for(;number<limit;number++){
			int totient=1;
			
			int count=0;
			
			for(int j=1;j<primeList.length && primeList[j]<=number;j++){
				if(number % primeList[j]==0){
					int times=0;
					for(int k=1; k*primeList[j] < number; k++){
						times++;
					}
					count+=times;
				}
			}
			
			totient=number-count-1;
			
			if(isPermute(number, totient)){
				double ratio=((double)totient)/((double)number);
				if(ratio<minRatio){
					minRatio=ratio;
					result=number;
				}
			}
		}
		
		return result;
	}
	
	public static boolean isPermute(int source, int target){
		char[] sourceArray = String.valueOf(source).toCharArray();
		Arrays.sort(sourceArray);
		String sortedSource=new String(sourceArray);
		
		char[] targetArray = String.valueOf(target).toCharArray();
		Arrays.sort(targetArray);
		String sortedTarget=new String(targetArray);
		
		return sortedSource.equals(sortedTarget);
	}
}
