/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;

/**
 * @author Gao, Fei
 *
 */
public class Problem26To50 {
	/**
	 * 
	 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
	 * 
	 * 1/2	= 	0.5
	 * 1/3	= 	0.(3)
	 * 1/4	= 	0.25
	 * 1/5	= 	0.2
	 * 1/6	= 	0.1(6)
	 * 1/7	= 	0.(142857)
	 * 1/8	= 	0.125
	 * 1/9	= 	0.(1)
	 * 1/10	= 	0.1
	 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
	 * 
	 * Find the value of d  1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
	 * @return
	 */
	public static int p026FindLongestRecurringCircle(int limit){
		int max=0;
		int maxLength=0;
		
		for(int d=limit;d>=2;d--){
			if(maxLength>d){
				break;
			}
			
			int [] remainders=new int[d+1];
			
			int value=1;
			int position=0;
			
			while(remainders[value]==0 && value!=0){
				remainders[value]=position;
				value=value*10;
				value=value%d;
				position++;
			}
			
			if(position-remainders[value]>maxLength){
				max=d;
				maxLength=position-remainders[value];
			}
		}
		
		return max;
	}
	
	
	/**
	 * Euler published the remarkable quadratic formula:
	 * 
	 * n² + n + 41
	 * 
	 * It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. 
	 * However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.
	 * 
	 * Using computers, the incredible formula  n²  79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. 
	 * The product of the coefficients, 79 and 1601, is 126479.
	 * 
	 * Considering quadratics of the form:
	 * 
	 * n² + an + b, where |a|  1000 and |b|  1000

	 * where |n| is the modulus/absolute value of n
	 * e.g. |11| = 11 and |4| = 4
	 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
	 * 
	 * @param limit1
	 * @param limit2
	 * @return
	 */
	public static Pair p027GetQuadraticEexpressionWithMaxNumberOfPrimes(int limit1, int limit2){
		int maxNumber=0;
		Pair maxPair = null;
		
		int [] primeList=PrimeUtil.getAllPrimeBelowN(limit2);
		int [] list2=new int[primeList.length*2];
		
		int index=0;
		while(index<list2.length){
			if(index<primeList.length){
				list2[index]=primeList[primeList.length-1-index]*-1;
			}else{
				list2[index]=primeList[index-primeList.length];
			}
			index++;
		}
		
		for(int a=limit1*-1;a<=limit1;a++){
			if(a!=0){
				for(int i=0;i<list2.length;i++){
					int primeNumber = getPrimeNumber(a,list2[i]);
					if(primeNumber>maxNumber){
						maxNumber=primeNumber;
						maxPair=new Pair(a, list2[i]);
					}
				}
			}
		}
		
		
		return maxPair;
	}
	
	private static int getPrimeNumber(int a, int b){
		int number=0;
		
		while(true){
			int value=number*number+a*number+b;
			if(value<=1 || !PrimeUtil.isPrime(value)){
				return number;
			}
			number++;
		}
	}
	
	/**
	 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
	 * 
	 * 	21 22 23 24 25
	 * 	20  7  8  9 10
	 * 	19  6  1  2 11
	 * 	18  5  4  3 12
	 * 	17 16 15 14 13
	 * 
	 * It can be verified that the sum of the numbers on the diagonals is 101.
	 * 
	 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
	 * @param size
	 * @return
	 */
	public static BigInteger p028GetDiagonalSumOfSpiralArray(int size){
		int [][] matrix= new int[size][size];
		int mid = size/2;
		
		int value=1;
		int layer=0;
		int row=mid;
		int column=mid;
		while(layer<=size/2){
			matrix[row][column]=value++;
			
			if(row==mid-layer&&column==mid+layer){
				layer++;
				column++;
			}else if(row>mid-layer&&row<mid+layer&&column==mid+layer){
				row++;
			}else if(row==mid+layer&&column>mid-layer&&column<=mid+layer){
				column--;
			}else if(row>mid-layer&&row<=mid+layer&&column==mid-layer){
				row--;
			}else if(row==mid-layer&&column>=mid-layer&&column<mid+layer){
				column++;
			}
			
		}
		
		int start=0;
		int end=size-1;
		BigInteger sum=BigInteger.ZERO;
		for(int i=0;i<matrix.length;i++){
			if(start!=end){
				sum=sum.add(BigInteger.valueOf(matrix[i][start])).add(BigInteger.valueOf(matrix[i][end]));;
			}else{
				sum=sum.add(BigInteger.valueOf(matrix[i][start]));
			}
			start++;
			end--;
		}
		
		return sum;
		
	}
}