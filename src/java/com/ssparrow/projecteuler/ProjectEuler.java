package com.ssparrow.projecteuler;

import java.math.BigInteger;
import java.util.BitSet;

import com.ssparrow.algorithm.array.Triplet;

public class ProjectEuler {

	/**
	 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
	 * The sum of these multiples is 23.
	 * 
	 * Find the sum of all the multiples of 3 or 5 below 1000.
	 * @param n
	 * @return
	 */
	public static int p001Find3or5MultipleSum(int n){
		int sum=0;
		
		int threeMultiple=0;
		int fiveMultiple=0;
		
		while(threeMultiple+3<n || fiveMultiple+5<n){
			if(threeMultiple+3==fiveMultiple){
				threeMultiple=threeMultiple+3;
			}else if(threeMultiple+3<=fiveMultiple+5){
				threeMultiple=threeMultiple+3;
				sum+=threeMultiple;
			}else if(fiveMultiple+5==threeMultiple){
				fiveMultiple=fiveMultiple+5;
			}else if(fiveMultiple+5<threeMultiple+3){
				fiveMultiple=fiveMultiple+5;
				sum+=fiveMultiple;
			}
			
			if(threeMultiple+3>=n && fiveMultiple+5>=n){
				break;
			}
		}
		
		return sum;
		
	}
	
	/**
	 * Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
	 *
	 *					1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
	 *
	 *By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
	 * 
	 * @param n
	 * @return
	 */
	public static int p002FindEvenFibonacciNumberSum(int n){
		int f1=1;
		int f2=2;
		
		if(n<2){
			return 0;
		}
		
		int sum=2;
		int f=f1+f2;
		while(f<=n){
			if(f%2==0){
				sum+=f;
			}
			
			f1=f2;
			f2=f;
			f=f1+f2;
		}
		
		return sum;
	}
	
	/**
	 * The prime factors of 13195 are 5, 7, 13 and 29.
	 * 
	 * What is the largest prime factor of the number 600851475143 ?
	 * @param n
	 * @return
	 */
	public static BigInteger p003FindLargestPrimeFactor(BigInteger n){
		BigInteger lpf=BigInteger.valueOf(1);
		
		BigInteger[] divideAndRemainder = n.divideAndRemainder(BigInteger.valueOf(2));
		while(divideAndRemainder[1].equals(BigInteger.valueOf(0))){
			n=n.divide(BigInteger.valueOf(2));
			lpf=BigInteger.valueOf(2);
			
			divideAndRemainder = n.divideAndRemainder(BigInteger.valueOf(2));
		}
		
		for(BigInteger i=BigInteger.valueOf(3);i.compareTo(n)<=0;i = i.add(BigInteger.valueOf(2))){
			divideAndRemainder = n.divideAndRemainder(i);
			while(divideAndRemainder[1].equals(BigInteger.valueOf(0))){
				n=n.divide(i);
				lpf=i;
				
				divideAndRemainder = n.divideAndRemainder(BigInteger.valueOf(2));
			}
		}
		
		return lpf;
	}
	
	/**
	 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 99.
	 * 
	 * Find the largest palindrome made from the product of two 3-digit numbers.
	 * @return
	 */
	public static int p004FindLargestPalindromicNumber(){
		BitSet visited=new BitSet(999*999);
		int max=-1;
		for(int first=100;first<=999;first++){
			for(int second=100;second<=999;second++){
				int n =first*second;
				if(!visited.get(n) && isPalindrome(n)){
					if(n>max){
						max=n;
					}
				}
			}
		}
		
		return max;
	}
	
	public static boolean isPalindrome(int n){
		String nStr=String.valueOf(n);
		
		int forwardIndex=0;
		int backIndex=0;
		int fastIndex=0;
		
		for(;forwardIndex<nStr.length();forwardIndex++,fastIndex+=2){
			if(fastIndex==nStr.length()-1){
				backIndex=forwardIndex-1;
			}else if(fastIndex==nStr.length()){
				backIndex=forwardIndex-1;
				forwardIndex--;
			}else if(fastIndex>nStr.length()){
				if(nStr.charAt(backIndex)!=nStr.charAt(forwardIndex)){
					return false;
				}
				backIndex--;
			}
		}
		
		return true;
	}
	
	/**
	 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
	 * 
	 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
	 * 
	 * @param n
	 * @return
	 */
	public static int p005FindSmallestDivisibleBy1ToN(int n){
		if(n==1){
			return 1;
		}
		
		int result=1;
		
		for(int i=2;i<=n;i++){
			int multiply=1;
			
			if(result%i!=0){
				int lastDividend=1;
				for(int j=2; j<i;j++){
					if(i%j==0){
						lastDividend = j;
					}
				}
				
				multiply=i/lastDividend;
			}
			
			result*=multiply;
		}
		
		return result;
	}
	
	/**
	 * 
	 * The sum of the squares of the first ten natural numbers is,
	 * 
	 * 12 + 22 + ... + 102 = 385
	 * The square of the sum of the first ten natural numbers is,
	 * 
	 * (1 + 2 + ... + 10)2 = 552 = 3025
	 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025  385 = 2640.
	 * 
	 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
	 * @param n
	 * @return
	 */
	public static int p006FindDiffSumSquareAndSquareSum(int n){
		 int sum = (n*(n+1))/2;
		 int sumSuqare= sum * sum;
		 
		 int squareSum=0;
		 for(int i=1;i<=n;i++){
			 squareSum+=i*i;
		 }
		 
		 return sumSuqare-squareSum;
	}
	
	/**
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
	 * 
	 * What is the 10 001st prime number?
	 * 
	 * @param n
	 * @return
	 */
	public static int p007FindNthPrimeNumber(int n){
		if(n==1){
			return 2;
		}
		
		int [] prime=new int [n];
		prime[0]=2;
		int index=1;
		
		while(index<n){
			int previousPrime = prime[index-1];
			int number=previousPrime==2?previousPrime+1:previousPrime+2;
			while(true){
				boolean isPrime=true;
				for(int i=0;i<index;i++){
					if(number%prime[i]==0){
						isPrime=false;
						break;
					}
				}
				
				if(isPrime){
					prime[index]=number;
					break;
				}
				number+=2;
			}
			index++;
		}
		
		return prime[n-1];
	}
	
	/**
	 * Find the greatest product of five consecutive digits in the 1000-digit number.
	 * 
	 * @param input
	 * @return
	 */
	public static int p008FindLargest5DigitProduct(String input){
		int maxProduct=0;
		int maxStartIndex=-1;
		int [] maxWindow =new int[5];
		
		int slidingWindowStart=-1;
		int SlidingWindowIndex=0;
		int [] SlidingWindow=new int[5];
		int slidingProduct=0;
		
		for(int i=0;i<input.length();i++){
			int digit=input.charAt(i)-'0';
			
			if(digit<0 || digit>=10){
				return -1;
			}
			
			if(digit==0){
				slidingProduct=0;
			}else{
				if(slidingProduct==0){
					slidingWindowStart=i;
					SlidingWindowIndex=0;
					slidingProduct=digit;
					
					SlidingWindow[SlidingWindowIndex++]=digit;
				}else{
					if(i-slidingWindowStart<4){
						SlidingWindow[SlidingWindowIndex++]=digit;
						slidingProduct=slidingProduct*digit;
						continue;
					}else if(i-slidingWindowStart==4){
						SlidingWindow[SlidingWindowIndex]=digit;
						slidingProduct=slidingProduct*digit;
					}else{
						slidingWindowStart++;
						slidingProduct=(slidingProduct/SlidingWindow[0])*digit;
						moveSlidingWindow(SlidingWindow, digit);
					}
					
					if(slidingProduct>maxProduct){
						maxProduct=slidingProduct;
						maxStartIndex=SlidingWindowIndex;
						System.arraycopy(SlidingWindow, 0, maxWindow, 0, 5);
					}
				}
			}
		}
		
		return maxProduct;
		
	}
	
	private static void moveSlidingWindow(int [] window, int value){
		for(int i=0;i<window.length-1;i++){
			window[i]=window[i+1];
		}
		window[window.length-1]=value;
	}
	
	/**
	 * A Pythagorean triplet is a set of three natural numbers, a  b  c, for which,
	 * 
	 * a2 + b2 = c2
	 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
	 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
	 * Find the product abc.
	 * @param sum
	 * @return
	 */
	public static Triplet p009FindTheLargestPythagorean(int sum){
		for(int i=sum/3;i<sum;i++){
			
			for(int j=1;j<i;j++){
				int k=sum-i-j;
				if(j*j+k*k==i*i){
					return new Triplet(j, k, i);
				}
			}
		}
		return null;
	}
	
	/**
	 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
	 * Find the sum of all the primes below two million.
	 * @param n
	 * @return
	 */
	public static BigInteger p010FindSumOfPrimeBelowN(int n){
		int lastPrime=2;
		BigInteger product=BigInteger.valueOf(2);
		
		BitSet flags=new BitSet();
		flags.set(2);
		
		while(lastPrime < n){
			for( int i=2; i*lastPrime<=n;i++){
				flags.set(i*lastPrime);
			}
			
			boolean foundPrime=false;
			for(int i=lastPrime+1; i<=n;i++){
				if(!flags.get(i)){
					lastPrime=i;
					product=product.add(BigInteger.valueOf(i));
					foundPrime=true;
					break;
				}
			}
			
			if(!foundPrime){
				break;
			}
		}
		
		return product;
	}
	
	/**
	 * In the 2020 grid below, four numbers along a diagonal line have been marked in red.
	 * The product of these numbers is 26  63  78  14 = 1788696.
	 * 
	 * What is the greatest product of four adjacent numbers in any direction (up, down, left, right, or diagonally) in the 2020 grid?
	 * @param matrix
	 * @return
	 */
	public static int p011FindLargestAdjacentProduct(int [][] matrix){
		int row=0;
		int column=0;
		Direction direction;
			
		
		int maxProduct=Integer.MIN_VALUE;
		
		for(int i=0; i<matrix.length;i++){
			int downLength=matrix.length-1-i+1;
			for(int j=0;j<matrix[i].length;j++){
				int leftLength=j+1;
				int rightLength=matrix[i].length-1-j+1;
				
				if(rightLength>=4){
					int product=matrix[i][j]*matrix[i][j+1]*matrix[i][j+2]*matrix[i][j+3];
					if(product>maxProduct){
						maxProduct=product;
						row=i;
						column=j;
						direction=Direction.RIGHT;
					}
				}
				
				if(downLength>=4){
					int product=matrix[i][j]*matrix[i+1][j]*matrix[i+2][j]*matrix[i+3][j];
					if(product>maxProduct){
						maxProduct=product;
						row=i;
						column=j;
						direction=Direction.DOWN;
					}
				}
				
				if(rightLength>=4 && downLength>=4){
					int product=matrix[i][j]*matrix[i+1][j+1]*matrix[i+2][j+2]*matrix[i+3][j+3];
					if(product>maxProduct){
						maxProduct=product;
						row=i;
						column=j;
						direction=Direction.DIAGONAL_RIGHT;
					}
				}
				
				if(leftLength>=4 && downLength>=4){
					int product=matrix[i][j]*matrix[i+1][j-1]*matrix[i+2][j-2]*matrix[i+3][j-3];
					if(product>maxProduct){
						maxProduct=product;
						row=i;
						column=j;
						direction=Direction.DIAGONAL_LEFT;
					}
				}
			}
		}
		
		return maxProduct;
	}
	
}

enum Direction{
	DOWN,RIGHT,DIAGONAL_RIGHT,DIAGONAL_LEFT;
}