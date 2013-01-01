/**
 * 
 */
package com.ssparrow.projecteuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
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
	 * if we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
	 * 
	 * Not all numbers produce palindromes so quickly. For example,
	 * 
	 * 349 + 943 = 1292,
	 * 1292 + 2921 = 4213
	 * 4213 + 3124 = 7337
	 * 
	 * That is, 349 took three iterations to arrive at a palindrome.
	 * 
	 * Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. A number that never forms a palindrome through the reverse and add process is called a Lychrel number. Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that a number is Lychrel until proven otherwise. In addition you are given that for every number below ten-thousand, it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome. In fact, 10677 is the first number to be shown to require over fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).
	 * 
	 * Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.
	 * 
	 * How many Lychrel numbers are there below ten-thousand?
	 * 
	 * NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.
	 * @param limit
	 * @return
	 */
	public static int p055GetNumberOfLychrelNumber(int limit){
		
		int count=0;
		
		for(int number=1;number<=limit;number++){
			if(isLychrelNumber(number)){
				count++;
			}
		}
		
		return count;
	}
	
	private static boolean isLychrelNumber(int number){
		final int MAX_LOOP=50;
		
		BigInteger sum=BigInteger.valueOf(number);
		
		for(int i=0;i<=MAX_LOOP;i++){
			BigInteger reverseNumber=reverseNumber(sum);
			sum=sum.add(reverseNumber);
			
			if(NumberUtil.isPalindrome(sum)){
				return false;
			}
		}
		
		return true;
	}
	
	private static BigInteger reverseNumber(BigInteger number){
		int[] digits = NumberUtil.getBigIntegerNumberDigits(number, Endian.LITTLE);
		
		return NumberUtil.getBigIntegerNumber(digits, 0, digits.length);
	}
	
	
	/**
	 * 
	 *  It is possible to show that the square root of two can be expressed as an infinite continued fraction.

		 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
		
		By expanding this for the first four iterations, we get:
		
		1 + 1/2 = 3/2 = 1.5
		1 + 1/(2 + 1/2) = 7/5 = 1.4
		1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
		1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
		
		The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
		
		In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?

	 * @param limit
	 * @return
	 */
	public static int p057GetCountOfSpecificSquareRootFraction(int limit){
		int count=0;
		
		Fraction previouFraction=new Fraction(BigInteger.ZERO, BigInteger.ONE);
		Fraction squareRoot = new Fraction(BigInteger.ZERO, BigInteger.ONE);
		for(int i=0;i<limit;i++){
			BigInteger newNumerator=previouFraction.getDenominator();
			BigInteger newDenominator=BigInteger.valueOf(2).multiply(previouFraction.getDenominator()).add(previouFraction.getNumerator());
			
			previouFraction.setNumerator(newNumerator);
			previouFraction.setDenominator(newDenominator);
			
			BigInteger sqtNumberator=newNumerator.add(newDenominator);
			BigInteger sqtDenominator=newDenominator;
			
			squareRoot.setNumerator(sqtNumberator);
			squareRoot.setDenominator(sqtDenominator);
			
			if(getDigits(sqtNumberator)>getDigits(sqtDenominator)){
				count++;
			}
		}
		
		return count;
	}
	
	private static int getDigits(BigInteger number){
		int digits=0;
		
		while(number.compareTo(BigInteger.ZERO)>0){
			number=number.divide(BigInteger.TEN);
			digits++;
		}
		
		return digits;
	}
	
	/**
	 *  Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.

		37 36 35 34 33 32 31
		38 17 16 15 14 13 30
		39 18  5  4  3 12 29
		40 19  6  1  2 11 28
		41 20  7  8  9 10 27
		42 21 22 23 24 25 26
		43 44 45 46 47 48 49
		
		It is interesting to note that the odd squares lie along the bottom right diagonal, 
		but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; 
		that is, a ratio of 8/13  62%.
		
		If one complete new layer is wrapped around the spiral above,
		a square spiral with side length 9 will be formed. If this process is continued, 
		what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?
	 * @param limit
	 * @return
	 */
	public static int p058GetAnticlockSpiralMatrixWithDiagPrimeRateBelowLimit(double limit){
		int MAX = 30001;
		
		int [] primeList=PrimeUtil.getAllPrimeBelowN(MAX*MAX);
		BitSet primeBitset=new BitSet();
		for(int i=1;i<primeList.length;i++){
			primeBitset.set(primeList[i]);
		}
		
		int level=0;
		int row=MAX/2;
		int column=MAX/2;
		
		int number=1;
		int primeCount=0;
		while(level<=MAX/2){
			if(primeBitset.get(number++) && (row==column || row+column==MAX-1)){
				primeCount++;
			}
			
			if(row==MAX/2+level && column==MAX/2+level){
				double rate= (double)primeCount/(double)(4*level+1);
//				System.out.println(level+":"+rate);
				if(rate>0 && rate<limit){
					return 2*level+1;
				}
				
				level++;
				column++;
			}else if(row>MAX/2-level && column==MAX/2+level){
				row--;
			}else if(row==MAX/2-level && column>MAX/2-level){
				column--;
			}else if(row<MAX/2+level && column==MAX/2-level){
				row++;
			}else if(row==MAX/2+level && column<MAX/2+level){
				column++;
			}
		}
		
		return 0;
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
	 *  
	 *  Euler's Totient function, φ(n) [sometimes called the phi function], 
	 *  is used to determine the number of numbers less than n which are relatively prime to n. 
	 *  For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.

		n	Relatively Prime	φ(n)	n/φ(n)
		2	1	1	2
		3	1,2	2	1.5
		4	1,3	2	2
		5	1,2,3,4	4	1.25
		6	1,5	2	3
		7	1,2,3,4,5,6	6	1.1666...
		8	1,3,5,7	4	2
		9	1,2,4,5,7,8	6	1.5
		10	1,3,7,9	4	2.5
		It can be seen that n=6 produces a maximum n/φ(n) for n  10.
		
		Find the value of n  1,000,000 for which n/φ(n) is a maximum.

	 * @param limit
	 * @return
	 */
	public static int p069GetTotientMaximum(int limit){
		int max=2;
		double maxRatio=Double.MIN_VALUE;
		
		for(int n=2;n<=limit;n++){
			int totient=getTotient(n);
			
			double ratio=(double)n/(double)totient;
			if(ratio>maxRatio){
				max=n;
				maxRatio=ratio;
			}
		}
		
		return max;
		
	}
	
	public static int getTotient(int n){
		int result=1;
		
		BitSet flags=new BitSet();
		
		int number=2;
		while(number<n){
			if(!flags.get(number)){
				int count=0;
				for(int i=1;i*number<=n;i++){
					if(i*number==n){
						count=0;
					}else if(!flags.get(i*number) ){
						if(i==1 || n%i!=0){
							count++;
						}
						flags.set(i*number);
					}
				}
				
				result+=count;
			}
			number++;
		}
		
		return result;
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
