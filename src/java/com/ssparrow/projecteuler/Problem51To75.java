/**
 * 
 */
package com.ssparrow.projecteuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Philip
 *
 */
public class Problem51To75 {
	/**
	 * By replacing the 1st digit of *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
	 * 
	 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, 
	 * yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.
	 * 
	 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
	 * @return
	 */
	public static int p051FindSmallest8PrimeValueFamily(int familyNum){
		final int MAX=10000000;
		
		int[] primeList = PrimeUtil.getAllPrimeBelowN(MAX);
		BitSet bitSet=new BitSet();
		for(int i=1;i<primeList.length;i++){
			bitSet.set(primeList[i]);
		}
		
		for(int i=1;i<primeList.length;i++){
			if(isPrimeValueFamily(primeList[i], bitSet, familyNum)){
				return primeList[i];
			}
		}
		
		return 0;
	}
	
	private static boolean isPrimeValueFamily(int prime, BitSet bitSet, int familyNum){
		int [] digits=NumberUtil.getNumberDigits(prime, Endian.BIG);
		Map<Integer, List<Integer>> digitIndexsMap=getDigitGroups(digits);
		
		for(int digit:digitIndexsMap.keySet()){
			
			List<Integer> indexList = digitIndexsMap.get(digit);
			
			int maxLength=indexList.size();
			for(int length=1;length<=maxLength;length++){
				for(int start=0;start<=maxLength-length;start++){

					int primeCount=0;
					for(int subsititue=0;subsititue<=9;subsititue++){
						if(subsititue==0 && indexList.get(start)==0){
							continue;
						}
						
						int[] clone = digits.clone();
						
						for(int i=start;i<start+length;i++){
							clone[indexList.get(i)]=subsititue;
						}
						
						int value=NumberUtil.getNumber(clone, 0, clone.length);
						
						if(bitSet.get(value)){
							primeCount++;
						}
					}
					
					if(primeCount>=familyNum){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private static Map<Integer, List<Integer>> getDigitGroups(int [] digits){
		Map<Integer, List<Integer>> digitIndexsMap=new HashMap<Integer, List<Integer>>();
		
		for(int i=0;i<digits.length;i++){
			List<Integer> indexList=digitIndexsMap.get(digits[i]);
			if(indexList==null){
				indexList=new ArrayList<Integer>();
				digitIndexsMap.put(digits[i], indexList);
			}
			indexList.add(i);
		}
		
		return digitIndexsMap;
	}
	
	/**
	 * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.
	 * 
	 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
	 * @return
	 */
	public static int p052FindSmallestNumberWithSameDigitsAsItsTimes(){
		for(int number=1;number<=10000000;number++){
			boolean match=true;
			for(int times=2;times<=6;times++){
				int product=times*number;
				if(!hasSameDigits(number, product)){
					match=false;
				}
			}
			
			if(match){
				return number;
			}
		}
		
		return 0;
	}
	
	private static boolean hasSameDigits(int number1, int number2){
		String str1=String.valueOf(number1);
		String str2=String.valueOf(number2);
		
		if(str1.length()==str2.length()){
			char [] array1=str1.toCharArray();
			char [] array2=str2.toCharArray();
			
			Arrays.sort(array1);
			Arrays.sort(array2);
			
			return Arrays.equals(array1, array2);
		}
		
		return false;
	}
	
	
	/**
	 * There are exactly ten ways of selecting three from five, 12345:
	 * 
	 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
	 * 
	 * In combinatorics, we use the notation, 5C3 = 10.
	 * 
	 * In general,
	 * 
	 * nCr =	
	 * n!
	 * r!(nr)!
	 * ,where r  n, n! = n(n1)...321, and 0! = 1.
	 * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
	 * 
	 * How many, not necessarily distinct, values of  nCr, for 1  n  100, are greater than one-million?
	 * @param MAX
	 * @param target
	 * @return
	 */
	public static int p053FindCountOfCnrLargerThanTarget(int MAX, int target){
		int sum=0;
		
		for(int n=1;n<=MAX;n++){
			int value=1;
			for(int r=1;r<=n-1;r++){
				value=(value*(n-r+1))/r;
				
				if(value>target){
					sum+=n-2*(r-1)-1;
					break;
				}
			}
		}
		
		return sum;
	}
	
	/**
	 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
	 * 
	 * 3
	 * 7 4
	 * 2 4 6
	 * 8 5 9 3
	 * 
	 * That is, 3 + 7 + 4 + 9 = 23.
	 * 
	 * Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'), a 15K text file containing a triangle with one-hundred rows.
	 * 
	 * NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to solve this problem, as there are 299 altogether! 
	 * If you could check one trillion (1012) routes every second it would take over twenty billion years to check them all. There is an efficient algorithm to solve it. ;o)
	 * @param filename
	 * @param size
	 * @return
	 * @throws IOException
	 */
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
		int squareRoot=(int) Math.sqrt(limit);
		
		int start = PrimeUtil.getAllPrimeBelowN(squareRoot/2).length;
		int [] primeList  =PrimeUtil.getAllPrimeBelowN(squareRoot*2);
		
		double minRatio=Double.MAX_VALUE;
		int result=0;
		
		for(int i=start;i<primeList.length;i++){
			for(int j=i+1;j<primeList.length;j++){
				int number=primeList[i]*primeList[j];
				
				if(number<=limit){
					int totient= (primeList[i]-1)*(primeList[j]-1);
					double ratio=((double)number)/((double)totient);
					
					if(NumberUtil.isPermute(number, totient)){
						if(ratio<minRatio){
							result=number;
							minRatio=ratio;
						}
					}
				}
			}
		}
		
		return result;
	}
}
