package com.ssparrow.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class Problem1To25 {

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
	
	/**
	 * The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:
	 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
	 * 
	 * Let us list the factors of the first seven triangle numbers:
	 * �1: 1
	 * �3: 1,3
	 * �6: 1,2,3,6
	 * 10: 1,2,5,10
	 * 15: 1,3,5,15
	 * 21: 1,3,7,21
	 * 28: 1,2,4,7,14,28
	 * 
	 * We can see that 28 is the first triangle number to have over five divisors.
	 * What is the value of the first triangle number to have over five hundred divisors?

	 * @param number
	 * @return
	 */
	public static int p012GetTriangleNumberWithGivenDivisors(int number){
		
		int [] primeList= PrimeUtil.getAllPrimeBelowN(50000);
		
		int i=100;
		int sum = (i*(i+1))/2;
		while(true){
			
			int count=1;
			int temp=sum;
			
			for(int k=1;k<primeList.length;k++){
				if(temp>=primeList[k]){
					int exp=1;
					while(temp%primeList[k]==0){
						exp++;
						temp=temp/primeList[k];
					}
					count*=exp;
				}else{
					break;
				}
			}
			
			if(count>number){
				return sum;
			}
			
			i++;
			sum+=i;
		}
	}
	
	/**
	 * Work out the first ten digits of the sum of the given one-hundred 50-digit numbers.
	 * 
	 * @param numbers
	 * @return
	 */
	public static int [] p013FindFirst10DigitOfSum(String [] numbers){
		int [] digits = new int[numbers[0].length()+3];
		int index=0;
		
		int carryover=0;
		
		for (int i = numbers[0].length() - 1; i >= 0; i--) {
			int sum = carryover;
			
			for (int j = 0; j < numbers.length; j++) {
				int digit = numbers[j].charAt(i) - '0';
				if (digit < 0 || digit > 9) {
					return null;
				}
				sum+=digit;

			}
			
			digits[index++]=sum%10;
			
			carryover=sum/10;
		}
		
		while(carryover>0){
			digits[index++]=carryover%10;
			carryover=carryover/10;
		}
		
		int [] result = new int [10];
		int k=0;
		int l=index-1;
		while(k<10){
			result[k++]=digits[l--];
		}
		
		return result;
	}
	
	/**
	 * The following iterative sequence is defined for the set of positive integers:
	 *
	 *		n  n/2 (n is even)
	 *		n  3n + 1 (n is odd)
	 *
	 *Using the rule above and starting with 13, we generate the following sequence:
	 *
	 *		13  40  20  10  5  16  8  4  2  1
	 *It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
	 *
	 *Which starting number, under one million, produces the longest chain?
	 *
	 *NOTE: Once the chain starts the terms are allowed to go above one million.
	 * @param maxStart
	 * @return
	 */
	public static int p014findLongestCollatzChain(int maxStart){
		int maxStartNumber=0;
		int maxLength=0;
		
		HashMap<BigInteger, Integer> map=new HashMap<BigInteger, Integer>();
		
		for(int start=maxStart;start>=2;start--){
			//number will possibly extend limit of java integer, so define its type as BigInteger  
			BigInteger number=BigInteger.valueOf(start);
			int index=0;
			
			while(number.compareTo(BigInteger.valueOf(1))>0){
				if(index>0){
					if(map.get(number)!=null && map.get(number)>=index){
						break;
					}else{
						map.put(number,index);
					}
				}
				
				BigInteger[] divideAndRemainder = number.divideAndRemainder(BigInteger.valueOf(2));
				
				if(divideAndRemainder[1].equals(BigInteger.valueOf(0))){
					number=divideAndRemainder[0];
				}else{
					number=number.multiply(BigInteger.valueOf(3)).add(BigInteger.valueOf(1));
				}
				
				index++;
			}
			
			if(index>maxLength){
				maxStartNumber=start;
				maxLength=index;
			}
		}
		
		
		return maxStartNumber;
		
	}
	
	/**
	 * Starting in the top left corner of a 2*2 grid, there are 6 routes (without backtracking) to the bottom right corner.
	 * 
	 * How many routes are there through a 20*20 grid?
	 * 
	 * @param row
	 * @param column
	 * @param n
	 * @return
	 */
	public static BigInteger p015CalculateRouteNumberInMatrix(BigInteger [][] result, int row, int column, int n){
		
		if(result[row][column]!=null){
			return result[row][column];
		}
		

		if(row==n && column==n){
			result[row][column]=BigInteger.valueOf(1);
			return BigInteger.valueOf(1);
		}
		
		//tricky part, although 10*10 grid seems small, the total path will exist limit of integer
		BigInteger route =BigInteger.valueOf(0);
		
		if(row<n){
			route=route.add(p015CalculateRouteNumberInMatrix(result, row+1, column, n));
		}
		
		if(column<n){
			route=route.add(p015CalculateRouteNumberInMatrix(result, row, column+1, n));
		}
		
		result[row][column]=route;
		
		return route;
	}
	
	/**
	 * 215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
	 * 
	 * What is the sum of the digits of the number 21000?
	 * @param exp
	 * @return
	 */
	public static BigInteger p016GetSumOfDigitsFor2Exp(int exp){
		BigInteger number= BigInteger.valueOf(2);
		number = number.pow(exp);
		
		BigInteger sum=BigInteger.ZERO;
		BigInteger[] divideAndRemainder = number.divideAndRemainder(BigInteger.TEN);
		
		while(!number.equals(BigInteger.ZERO)){
			sum=sum.add(divideAndRemainder[1]);
			
			number=divideAndRemainder[0];
			divideAndRemainder = number.divideAndRemainder(BigInteger.TEN);
		}
		
		return sum;
	}
	
	/**
	 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
	 * 
	 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
	 * 
	 * 
	 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. 
	 * The use of "and" when writing out numbers is in compliance with British usage.
	 * @param max
	 * @return
	 */
	public static int p017GetTotalLetterNumber(int max){
		int total=0;
		
		for(int i=1;i<=max;i++){
			total+=getStringRepresentationLength(i);
		}
		
		return total;
	}
	
	public static int getStringRepresentationLength(int number){
		
		String [] singles = new String[]{"", "one", "two", "three","four","five","six","seven","eight","nine"};
		String [] tenSome = new String[]{"", "eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
		String [] tens	  = new String[]{"","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
		String hundred="hundred";
		String thousand="thousand";
		String and="and";
		
		int length=0;
		
		if(number<10){
			length+=singles[number].length();
		}else if(number<100 && number%10==0){
			length+=tens[number/10].length();
		}else if(number>10 && number<20){
			length+=tenSome[number-10].length();
		}else if(number<100){
			length+=tens[number/10].length()+singles[number%10].length();
		}else if(number<1000 && number%100==0){
			length+=singles[number/100].length()+hundred.length();
		}else if(number<1000){
			int tenAndSingle=number-(number/100)*100;
			length+=singles[number/100].length()+hundred.length()+and.length()+getStringRepresentationLength(tenAndSingle);
		}else if(number<10000 && number%1000==0){
			length+=singles[number/1000].length()+thousand.length();
		}else if(number<10000){
			int hundredBelow=number-(number/1000)*1000;
			length+=singles[number/1000].length()+thousand.length()+getStringRepresentationLength(hundredBelow);
		}else{
			return -1;
		}
		
		return length;
	}
	
	/**
	 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
	 *
	 *3
	 *7 4
	 *2 4 6
	 *8 5 9 3
	 *
	 *That is, 3 + 7 + 4 + 9 = 23.
	 *
	 * Find the maximum total from top to bottom of the specified triangle :
	 * 
	 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. 
	 * However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)
	 * @param matrix
	 * @param row
	 * @param column
	 * @param path
	 * @return
	 */
	public static int p018FindMaxPathInMatrix(int [][] matrix, int row, int column,int []path){
		path[row]=matrix[row][column];
		
		if(row==matrix.length-1){
			int sum=0;
			for(int i=0;i<path.length;i++){
				sum+=path[i];
			}
			return sum;
		}
		
		int downMax=p018FindMaxPathInMatrix(matrix, row+1, column, path);
		int downRightMax=p018FindMaxPathInMatrix(matrix, row+1, column+1, path);
		
		return Math.max(downMax, downRightMax);
	}
	
	
	/**
	 * You are given the following information, but you may prefer to do some research for yourself.
	 * 
	 * 1 Jan 1900 was a Monday.
	 * Thirty days has September,
	 * April, June and November.
	 * All the rest have thirty-one,
	 * Saving February alone,
	 * Which has twenty-eight, rain or shine.
	 * And on leap years, twenty-nine.
	 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
	 * 
	 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
	 * @param start
	 * @param end
	 * @return
	 */
	public static int p019FindSundayOnFirstDay(int start, int end){
		int [] daysInMoth=new int []{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int daysInYear=0;
		for(int i=0;i<daysInMoth.length;i++){
			daysInYear+=daysInMoth[i];
		}
		
		int count=0;
		int startDayOfWeek=daysInYear%7+1;
		
		for(int year=start;year<=end;year++){
			boolean isLeapyear= year%4==0 && year%100!=0;
			for(int month=0;month<12;month++){
				if(startDayOfWeek==7){
					count++;
				}
				
				int daysInCurrrentMonth = (month==1&& isLeapyear)?daysInMoth[month]+1:daysInMoth[month];
				int remainder = (startDayOfWeek+daysInCurrrentMonth)%7;
				startDayOfWeek= remainder==0? 7 : remainder ;
			}
		}
		
		return count;
	}
	
	/**
	 * The Fibonacci sequence is defined by the recurrence relation:
	 * 		Fn = Fn1 + Fn2, where F1 = 1 and F2 = 1.
	 * The 12th term, F12, is the first term to contain three digits.
	 * What is the first term in the Fibonacci sequence to contain 1000 digits?
	 * @param digits
	 * @return
	 */
	public static int p025FindFirstFibonacciNumberWithGivenDigits(int digits){
		BigInteger fibNMinus2 = BigInteger.ONE;
		BigInteger fibNMinus1 = BigInteger.ONE;
		
		BigInteger number=fibNMinus2.add(fibNMinus1);
		int n=3;
		while(true){
			if(getNumberOfDigits(number)>=digits){
				return n;
			}
			
			fibNMinus2=fibNMinus1;
			fibNMinus1=number;
			
			number = fibNMinus2.add(fibNMinus1);;
			n++;
		}
		
	}
	
	public static BigInteger getFibonacciNumber(List<BigInteger> numbers, int n){
		if(n<=numbers.size()){
			return numbers.get(n-1);
		}
		
		BigInteger number=getFibonacciNumber(numbers, n-2).add(getFibonacciNumber(numbers, n-1));
		numbers.add(number);
		return number;
	}
	
	public static int getNumberOfDigits(BigInteger number){
		int digits=1;
		
		while(number.divide(BigInteger.TEN).compareTo(BigInteger.ZERO)>0){
			 number=number.divide(BigInteger.TEN);
			 digits++;
		}
		
		return digits;
	}
	
}
