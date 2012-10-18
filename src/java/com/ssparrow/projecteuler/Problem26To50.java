/**
 * 
 */
package com.ssparrow.projecteuler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

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
	
	
	/**
	 * Consider all integer combinations of ab for 2  a  5 and 2  b  5:
	 * 
	 * 22=4, 23=8, 24=16, 25=32
	 * 32=9, 33=27, 34=81, 35=243
	 * 42=16, 43=64, 44=256, 45=1024
	 * 52=25, 53=125, 54=625, 55=3125
	 * If they are then placed in numerical order, with any repeats removed, we get the following sequence of 15 distinct terms:
	 * 
	 * 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
	 * 
	 * How many distinct terms are in the sequence generated by ab for 2  a  100 and 2  b  100?
	 * 
	 * @param limit1
	 * @param limit2
	 * @return
	 */
	public static int p029CalculateDistinctTerms(int limit1, int limit2){
		Set<Pair> redundantTerms=new HashSet<Pair>();
		
		for(int a=2; a<=limit1;a++){
			//here we use limit2*8 because we need to count case such as 4^99=8^66 or 16^100=32^80
			for(int b=4;b<=limit2*8;b++){
				//we keep the count of redundant terms for current (a,b)
				int count=0;
				Set<Pair> tempTerms=new HashSet<Pair>();
				Pair firstPair=null;
				for(int k=2;k<b;k++){
					if(b%k==0){
						int redundantA=(int) Math.pow(a, k);
						int redundantB=b/k;
						if(redundantA>=2&&redundantA<=limit1&&
								redundantB>=2&&redundantB<=limit2){
							count++;
							Pair pair = new Pair(redundantA, redundantB);
							tempTerms.add(pair);
							//the first pair need to to kept because when (a,b) is out of range, the fist redundant term need to be kept
							if(firstPair==null){
								firstPair=pair;
							}
						}
					}
				}
				
				if(b<=limit2){
					redundantTerms.addAll(tempTerms);
				}else if(count>=2){
					//remove the first pair from redundant when (a,b) is out of range of the match terms are valid
					tempTerms.remove(firstPair);
					redundantTerms.addAll(tempTerms);
				}
				
			}
		}
		
		return (limit1-1)*(limit2-1)-redundantTerms.size();
	}
	
	
	/**
	 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
	 * 
	 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
	 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
	 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
	 * As 1 = 1^4 is not a sum it is not included.
	 * 
	 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
	 * 
	 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
	 * @param n
	 * @return
	 */
	public static List<Integer> p030FindNumberOfNPowerSum(int n){
		List<Integer> result=new ArrayList<Integer>();
				
		for(int k=2;Math.pow(10, k-1)<=k*Math.pow(9, n);k++){
			int start=(int) Math.pow(10, k-1);
			int end=(int) (Math.pow(10, k)-1);
			
			for(int number=start;number<=end;number++){
				int sum = 0;
				
				int temp=number;
				while(temp>0){
					sum+=Math.pow(temp%10, n);
					temp=temp/10;
				}
				
				if(sum==number){
					result.add(number);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
	 * 
	 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
	 * It is possible to make £2 in the following way:
	 * 
	 * 1£1 + 150p + 220p + 15p + 12p + 31p
	 * How many different ways can £2 be made using any number of coins?
	 * @param value
	 * @param coin
	 * @return
	 */
	public static int p031FindCoinCombinations(int value, int coin){
		if(value==0){
			return 1;
		}else if(coin==0){
			return 0;
		}
		
		int nextCoin;
		switch (coin) {
		case 200:
			nextCoin=100;
			break;
		case 100:
			nextCoin=50;
			break;
		case 50:
			nextCoin=20;
			break;
		case 20:
			nextCoin=10;
			break;
		case 10:
			nextCoin=5;
			break;
		case 5:
			nextCoin=2;
			break;
		case 2:
			nextCoin=1;
			break;
		default:
			nextCoin=0;
			break;
		}
		
		int sum=0;
		for(int i=0;i*coin<=value;i++){
			sum+=p031FindCoinCombinations(value-(i*coin), nextCoin);
		}
		
		return sum;
	}
	
	/**
	 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; 
	 * for example, the 5-digit number, 15234, is 1 through 5 pandigital.
	 * 
	 * The product 7254 is unusual, as the identity, 39  186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.
	 * 
	 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
	 * 
	 * 
	 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
	 * @return
	 */
	public static Map<Integer, Pair> p032FindAllPandigitalNumber(){
		boolean [] used=new  boolean[9];
		
		Map<Integer, Pair> result=new HashMap<Integer, Pair>();
		int [] number = new int[4];
		findAllPandigitalNumber(result, used, number, 0);
		
		return result;
	}
	
	private static void findAllPandigitalNumber(Map<Integer, Pair> result, boolean [] used, int [] number, int position){
		if(position==4){
			int value=number[0]*1000+number[1]*100+number[2]*10+number[3];
			Pair pandigitalMultipliers = getPandigitalMultipliers(value, used);
			if(pandigitalMultipliers!=null){
				result.put(value, pandigitalMultipliers);
			}
			return;
		}
		
		for(int i=0;i<used.length;i++){
			if(!used[i]){
				number[position]=i+1;
				
				used[i]=true;
				findAllPandigitalNumber(result, used, number, position+1);
				used[i]=false;
			}
		}
	}
	
	private static Pair getPandigitalMultipliers(int value, boolean [] used){
		//find possible xxxx=x*xxxx
		for(int i=0;i<used.length;i++){
			if(!used[i]){
				int oneDigitNumber=i+1;
				
				used[i]=true;
				int [] fourDigitsNumber=new int [4];
				Pair pair = get1x4PandigitalMultipliers(value, used, oneDigitNumber, fourDigitsNumber, 0);
				used[i]=false;
				
				if(pair!=null){
					return pair;
				}
			}
		}
		
		//find possible xxxx=xx*xxx
		int [] twoDigitNumber = new int[2];
		return get2x3PandigitalMultipliers(value, used, twoDigitNumber, 0);
	}
	
	private static Pair get1x4PandigitalMultipliers(int value, boolean [] used, int oneDigitNumber, int [] fourDigitsNumber, int position){
		if(position==4){
			int fourDigitsNumberValue=fourDigitsNumber[0]*1000+fourDigitsNumber[1]*100+fourDigitsNumber[2]*10+fourDigitsNumber[3];
			if(value==oneDigitNumber*fourDigitsNumberValue){
				return new Pair(oneDigitNumber, fourDigitsNumberValue);
			}
			return null;
		}
		
		for(int i=0;i<used.length;i++){
			if(!used[i]){
				fourDigitsNumber[position]=i+1;
				
				used[i]=true;
				
				Pair pair = get1x4PandigitalMultipliers(value, used, oneDigitNumber, fourDigitsNumber, position+1);
				
				used[i]=false;
				
				if(pair!=null){
					return pair;
				}
			}
		}
		return null;
	}
	
	private static Pair get2x3PandigitalMultipliers(int value, boolean [] used, int [] twoDigitNumber, int position){
		if(position==2){
			int twoDigitNumberValue=twoDigitNumber[0]*10+twoDigitNumber[1];
			int [] threeDigitsNumber=new int [3];
			return get2x3PandigitalMultipliers(value, used, twoDigitNumberValue, threeDigitsNumber, 0);
		}
		
		for(int i=0;i<used.length;i++){
			if(!used[i]){
				twoDigitNumber[position]=i+1;
				
				used[i]=true;
				
				Pair pair = get2x3PandigitalMultipliers(value, used, twoDigitNumber, position+1);
				
				used[i]=false;
				
				if(pair!=null){
					return pair;
				}
			}
		}
		return null;
	}
	
	private static Pair get2x3PandigitalMultipliers(int value, boolean [] used,int twoDigitNumberValue, int [] threeDigitsNumber, int position){
		if(position==3){
			int threeDigitsNumberValue=threeDigitsNumber[0]*100+threeDigitsNumber[1]*10+threeDigitsNumber[2];
			if(value==twoDigitNumberValue*threeDigitsNumberValue){
				return new Pair(twoDigitNumberValue, threeDigitsNumberValue);
			}
			return null;
		}
		
		for(int i=0;i<used.length;i++){
			if(!used[i]){
				threeDigitsNumber[position]=i+1;
				
				used[i]=true;
				
				Pair pair = get2x3PandigitalMultipliers(value, used, twoDigitNumberValue, threeDigitsNumber, position+1);
				
				used[i]=false;
				
				if(pair!=null){
					return pair;
				}
			}
		}
		return null;
	}
	
	/**
	 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
	 *
	 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
	 * 
	 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.
	 * 
	 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
	 * 
	 * @return
	 */
	public static int p033GetLowestCommonDenominatorForCuriousFractions(){
		List<Pair> curiousFractions=new ArrayList<Pair>();
		List<Pair> equvalentFractions=new ArrayList<Pair>();
		
		for(int numerator=11;numerator<100;numerator++){
			if(numerator%10!=0){
				int start=(numerator%10)*10+1;
				int end=(numerator%10)*10+9;
				
				for(int denominator=start;denominator<=end;denominator++){
					if(numerator<denominator){
						int eqNumerator=numerator/10;
						int eqDenomiator=denominator%10;
						
						if((double)numerator/(double)denominator == (double)eqNumerator/(double)eqDenomiator){
							curiousFractions.add(new Pair(numerator, denominator));
							equvalentFractions.add(new Pair(eqNumerator, eqDenomiator));
						}
					}
				}
			}
		}
		
		int denomProduct=1;
		for(Pair pair:equvalentFractions){
			int num = pair.getA();
			int denom = pair.getB();
			
			denomProduct=denomProduct*denom;
			
			if(denomProduct%num==0){
				denomProduct=denomProduct/num;
			}else{
				int[] numDivisors = PrimeUtil.getAllDivisors(num);
				for(int i=numDivisors.length-1;i>=0;i--){
					if(denomProduct%numDivisors[i]==0){
						denomProduct=denomProduct/numDivisors[i];
						break;
					}
				}
			}
		}
		return denomProduct;
	}
	
	/**
	 * 45 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
	 * 
	 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
	 * 
	 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
	 * @return
	 */
	public static List<Integer> p034FindAllNumbersSumToDigitsFactorials(){
		int [] factorials=new int [10];
		factorials[0]=1;
		for(int i=1;i<10;i++){
			factorials[i]=factorials[i-1]*i;
		}
		
		List<Integer> curiousNumbers=new ArrayList<Integer>();
		for(int i=3;i<10000000;i++){
			int temp=i;
			
			int sum=0;
			while(temp>0){
				sum+=factorials[temp%10];
				temp=temp/10;
			}
			
			if(i==sum){
				curiousNumbers.add(i);
			}
		}
		
		return curiousNumbers;
	}
	
	/**
	 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
	 * 
	 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
	 * 
	 * How many circular primes are there below one million?
	 * @param limit
	 * @return
	 */
	public static List<Integer> p035FindAllCircularPrimes(int limit){
		List<Integer> result= new ArrayList<Integer>();
		
		int[] primeList = PrimeUtil.getAllPrimeBelowN(limit);
		
		boolean [] flags=new boolean[limit+1];
		for(int i=1;i<primeList.length;i++){
			flags[primeList[i]]=true;
		}
		
		for(int i=1;i<primeList.length;i++){
			int prime = primeList[i];
			
			if(prime<10){
				result.add(prime);
			}else{
				int index=0;
				
				int [] tempDigits=new int [7];
				int temp=prime;
				while(temp>0){
					tempDigits[6-index]=temp%10;
					temp=temp/10;
					index++;
				}
				
				int [] digits=new int[index];
				System.arraycopy(tempDigits, 7-index, digits, 0, index);
				
				boolean isCircularPrime=true;
				int length = digits.length;
				for(int j=1;j<length;j++){
					if(digits[j]>0){
						int number=0;
						for(int k=0;k<length;k++){
							int digitIndex=(j+k)>=length?(j+k-length):(j+k);
							number+=digits[digitIndex] * Math.pow(10, length-1-k);
						}
						
						if(!flags[number]){
							isCircularPrime=false;
							break;
						}
					}
				}
				
				if(isCircularPrime){
					result.add(prime);
				}
				
			}
		}
		
		return result;
	}
	
	/**
	 * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
	 * 
	 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
	 * 
	 * (Please note that the palindromic number, in either base, may not include leading zeros.)
	 * @param limit
	 * @return
	 */
	public static int p036GetSumOfAllPalindromeNumberOnBase2And10(int limit){
		List<Integer> palindromeNumbers=new ArrayList<Integer>();
		
		for(int number=1;number<limit;number++){
			if(NumberUtil.isPalindrome(number, 10) &&NumberUtil.isPalindrome(number, 2)){
				palindromeNumbers.add(number);
			}
		}
		
		int sum=0;
		for(Integer number:palindromeNumbers){
			sum+=number;
		}
		
		return sum;
	}
	
	/**
	 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
	 * 
	 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
	 * 
	 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
	 * 
	 * @return
	 */
	public static int p037FindSumOfTruncatablePrimes(){
		int limit = 10000000;
		boolean [] flags=new boolean[limit+1];
		flags[0]=true;
		flags[1]=true;
		
		List<Integer> result=new ArrayList<Integer>();
		int currentPrime=2;
		while(true){
			if(isTrunctablePrime(currentPrime, flags)){
				result.add(currentPrime);
			}
			
			for(int i=2;i*currentPrime<=limit;i++){
				flags[i*currentPrime]=true;
			}
			
			boolean foundPrime=false;
			for(int j=currentPrime+1;j<=limit;j++){
				if(!flags[j]){
					currentPrime=j;
					foundPrime=true;
					break;
				}
			}
			
			if(!foundPrime){
				break;
			}
		}
		
		int sum=0;
		for(Integer number:result){
			sum+=number;
		}
		
		return sum;
	}
	


	
	/**
	 * @param prime
	 * @param flags
	 * @return
	 */
	public static boolean isTrunctablePrime(int prime, boolean[] flags){
		if(prime<10){
			return false;
		}
		
		int [] allDigits = new int [10];
		
		int index=0;
		int temp=prime;
		while(temp>0){
			allDigits[10-1-index]=temp%10;
			temp=temp/10;
			index++;
		}
		
		int [] digits=new int[index];
		System.arraycopy(allDigits, 10-index, digits, 0, index);
		
		int number=0;
		for(int i=0;i<digits.length-1;i++){
			number= number+digits[digits.length-1-i]*(int)Math.pow(10, i);
			if(flags[number]){
				return false;
			}
		}
		
		number=0;
		for(int i=0;i<digits.length-1;i++){
			number=number*10+digits[i];
			if(flags[number]){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Take the number 192 and multiply it by each of 1, 2, and 3:
	 * 
	 * 192  1 = 192
	 * 192  2 = 384
	 * 192  3 = 576
	 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)
	 * 
	 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, 
	 * which is the concatenated product of 9 and (1,2,3,4,5).
	 * 
	 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n  1?
	 * @return
	 */
	public static int p038FindLargestPandigitNumberFormedByConcatProducts(){
		boolean [] used =new boolean [9];
		int [] digits=new int[9];
		return findLargestPandigitNumberFormedByConcatProducts(used, digits, 0);
	}
	
	private static int findLargestPandigitNumberFormedByConcatProducts(boolean [] used, int [] digits, int position){
		if(position==9){
			if(isFormedByConcatProducts(digits)){
				return getNumber(digits, 0, 9);
			}
			return -1;
		}
		
		for(int i=0;i<digits.length;i++){
			if(!used[i]){
				used[i]=true;
				
				digits[position]=9-i;
				int result = findLargestPandigitNumberFormedByConcatProducts(used, digits, position+1);
				
				if(result>0){
					return result;
				}
				
				used[i]=false;
			}
		}
		
		return -1;
	}
	
	private static  boolean isFormedByConcatProducts(int [] digits){
		for(int l=1;l<5;l++){
			int start=0;

			int firstNumber=getNumber(digits, start, l);
			
			int times=2;
			int nextNumber=firstNumber*times;
			int nextNumberLength = getNumberLength(nextNumber);
			
			start=start+l;
			while(digits.length-start>=nextNumberLength){
				if(getNumber(digits, start, nextNumberLength)!=nextNumber){
					break;
				}
				
				start=start+nextNumberLength;
				times++;
				nextNumber=firstNumber*times;
				nextNumberLength = getNumberLength(nextNumber);
			}
			
			if(start==digits.length){
				return true;
			}
		}
		
		return false;
	}

	private static int getNumber(int [] digits, int start, int length){
		int end=start+length-1;
		
		int index=start;
		int number=0;
		while(index<=end){
			number=number*10+digits[index];
			index++;
		}
		
		return number;
	}
	
	private static BigInteger getBigNumber(int [] digits, int start, int length){
		BigInteger number=BigInteger.ZERO;
		
		int end=start+length-1;
		
		int index=start;
		while(index<=end){
			number=number.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(digits[index]));
			index++;
		}
		
		return number;
	}

	/**
	 * @param number
	 * @return
	 */
	private static int getNumberLength(int number) {
		int numberLength=0;
		while(number>0){
			numberLength++;
			number=number/10;
		}
		return numberLength;
	}
	
	/**
	 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
	 * 
	 * {20,48,52}, {24,45,51}, {30,40,50}
	 * 
	 * For which value of p  1000, is the number of solutions maximised?
	 * 
	 * @param limit
	 * @return
	 */
	public static Pair p039FindMaxNumberOfSolution(int limit){
		Map<Integer, List<Triplet>> solutionMap=new HashMap<Integer, List<Triplet>>();
		
		int max=0;
		int number=0;
		for(int sum=limit;sum>3;sum--){
			List<Triplet> solutions =new ArrayList<Triplet>();
			for(int c=sum/3+1;c<=sum/2;c++){
				for(int a=1;a<=(sum-c)/2;a++){
					int b=sum-a-c;
					if((a*a+b*b)==c*c){
						solutions.add(new Triplet(a, b, c));
					}
				}
			}
			if(solutions.size()>max){
				max=solutions.size();
				number=sum;
			}
			
			if(solutions.size()>0){
				solutionMap.put(sum, solutions);
			}
		}
		
		return new Pair(number, solutionMap.get(number).size());
	}
	
	/**
	 * An irrational decimal fraction is created by concatenating the positive integers:
	 * 
	 * 0.123456789101112131415161718192021...
	 * 
	 * It can be seen that the 12th digit of the fractional part is 1.
	 * 
	 * If dn represents the nth digit of the fractional part, find the value of the following expression.
	 * 
	 * d1  d10  d100  d1000  d10000  d100000  d1000000
	 * @param n
	 * @return
	 */
	public static int p040FindDigitOfIrrationalFraction(int n){
		int numLength=1;
		int numStart=1;
		int digitStart=1;
		
		while(true){
			int numCount=(int) (Math.pow(10, numLength)-Math.pow(10,numLength-1));
			int totalDigits=numLength*numCount;
			int digitEnd=digitStart+totalDigits-1;
			
			if(n<=digitEnd){
				int length=n-digitStart+1;
				
				if(length%numLength==0){
					return (length/numLength+numStart-1)%10;
				}else{
					int index=length%numLength;
					int number = length/numLength+numStart;
					
					int i=0;
					int [] numDigits=new int[numLength];
					while(number>0){
						numDigits[i++]=number%10;
						number=number/10;
					}
					
					return numDigits[numLength-index];
				}
			}
			
			numStart=(int) Math.pow(10, numLength);
			digitStart=digitEnd+1;
			numLength++;
			
		}
	}
	
	/**
	 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.
	 * 
	 * What is the largest n-digit pandigital prime that exists?
	 * @return
	 */
	public static int p041FindLargestPandigitPrime(){
		for(int digitNum=9;digitNum>=1;digitNum--){
			int [] number=new int[digitNum];
			boolean [] used=new boolean[digitNum];
			
			int result = findLargestPandigitPrime(number, used, 0);
			if(result>0){
				return result;
			}
		}
		
		return -1;
	}
	
	private static int findLargestPandigitPrime(int [] number, boolean [] used, int position){
		if(position==number.length){
			int value=getNumber(number, 0, number.length);
			
			if(number[number.length-1]%2!=0 && PrimeUtil.isPrime(value)){
				return value;
			}
			
			return -1;
		}
		
		for(int i=0;i<number.length;i++){
			if(!used[i]){
				used[i]=true;
				
				number[position]=number.length-i;
				int result = findLargestPandigitPrime(number, used, position+1);
				if(result>0){
					return result;
				}
				
				used[i]=false;
			}
		}
		
		return -1;
	}
	
	/**
	 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
	 * 
	 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
	 * 
	 * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.
	 * 
	 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static List<String> p042FindAllTriangleWords(String filename) throws Exception{
		int n=1;
		int t=1;
		
		Set<Integer> triangleNumbers=new HashSet<Integer>();
		while(t<=26*50){
			triangleNumbers.add(t);
			
			n++;
			t=(n*(n+1))/2;
		}
		
		List<String> result=new ArrayList<String>();
		FileChannel channel=new FileInputStream(filename).getChannel();
		ByteBuffer buffer= ByteBuffer.allocate((int) channel.size()+1);
		
		int read;
		String prefix="";
		while((read=channel.read(buffer))!=-1){
			String record=prefix + new String(buffer.array());
			
			prefix="";
			if(!record.endsWith(",") && !record.endsWith("\"")){
				prefix=record.substring(record.lastIndexOf("\"")+1);
				record=record.substring(0, record.lastIndexOf('\"'));
			}
			
			StringTokenizer st=new StringTokenizer(record,"\", ");
			while(st.hasMoreElements()){
				String word=(String) st.nextElement();
				
				int value=0;
				for(int i=0;i<word.length();i++){
					int charValue=word.charAt(i)-'A'+1;
					if(charValue>=1 && charValue<=26){
						value+=charValue;
					}else{
						return null;
					}
				}
				
				if(triangleNumbers.contains(value)){
					result.add(word);
				}
			}
			
			buffer.clear();
		}
		
		
		return result;
	}
	
	/**
	 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.
	 * 
	 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
	 * 
	 * d2d3d4=406 is divisible by 2
	 * d3d4d5=063 is divisible by 3
	 * d4d5d6=635 is divisible by 5
	 * d5d6d7=357 is divisible by 7
	 * d6d7d8=572 is divisible by 11
	 * d7d8d9=728 is divisible by 13
	 * d8d9d10=289 is divisible by 17
	 * Find the sum of all 0 to 9 pandigital numbers with this property.
	 * @return
	 */
	public static List<BigInteger> p043FindAllPandigitWithGivenAttributes(){
		boolean [] flags=new boolean[10];
		List<BigInteger> result=new ArrayList<BigInteger>();
		
		int []number=new int[10];
		findAllPandigitWithGivenAttributes(result, flags, number, 0);
		
		return result;
	}
	
	private static void findAllPandigitWithGivenAttributes(List<BigInteger> result, boolean [] flags, int [] number, int position){
		if(position==flags.length){
			int d1 = getNumber(number, 1, 3);
			if(d1%2!=0){
				return;
			}
			int d2 = getNumber(number, 2, 3);
			if(d2%3!=0){
				return;
			}
			int d3 = getNumber(number, 3, 3);
			if(d3%5!=0){
				return;
			}
			int d4 = getNumber(number, 4, 3);
			if(d4%7!=0){
				return;
			}
			int d5 = getNumber(number, 5, 3);
			if(d5%11!=0){
				return;
			}
			int d6 = getNumber(number, 6, 3);
			if(d6%13!=0){
				return;
			}
			int d7 = getNumber(number, 7, 3);
			if(d7%17!=0){
				return;
			}
			result.add(getBigNumber(number, 0, number.length));
		}
		int start=position==0?1:0;
		for(int i=start;i<flags.length;i++){
			if(!flags[i]){
				flags[i]=true;
				number[position]=i;
				findAllPandigitWithGivenAttributes(result,flags, number, position+1);
				flags[i]=false;
			}
		}
		
	}
	
	/**
	 * Pentagonal numbers are generated by the formula, Pn=n(3n1)/2. The first ten pentagonal numbers are:
	 * 
	 * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
	 * 
	 * It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70  22 = 48, is not pentagonal.
	 * 
	 * Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference is pentagonal and D = |Pk  Pj| is minimised; what is the value of D?
	 * @return
	 */
	public static Pair p044FindRequiredPentagonalNumber(){
		int LIMIT = 10000;
		
		int [] pentagonalNumbers=new int[LIMIT];
		BitSet bitSet=new BitSet();
		for(int i=1;i<=LIMIT;i++){
			pentagonalNumbers[i-1]=(i*(3*i-1))/2;
			bitSet.set(pentagonalNumbers[i-1]);
		}
		
		for(int i=0;i<LIMIT;i++){
			int a=pentagonalNumbers[i];
			for(int j=i+1;j<LIMIT;j++){
				int d=pentagonalNumbers[j];
						
				int b = a+d;
				if(bitSet.get(b)){
					if(bitSet.get(a+b)){
						return new Pair(a, b);
					}
					
					if(bitSet.get(d+b)){
						return new Pair(d, b);
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
	 * 
	 * Triangle	 	Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
	 * Pentagonal	 	Pn=n(3n1)/2	 	1, 5, 12, 22, 35, ...
	 * Hexagonal	 	Hn=n(2n1)	 	1, 6, 15, 28, 45, ...
	 * It can be verified that T285 = P165 = H143 = 40755.
	 * 
	 * Find the next triangle number that is also pentagonal and hexagonal.
	 * @param start
	 * @return
	 */
	public static int p045FindNextTrianglePentagonalHexagonalNumber(int start){
		int MAX=100000;

		
		Set<BigInteger> pentagonalNumbers=new HashSet<BigInteger>();
		Set<BigInteger> hexagonalNumbers=new HashSet<BigInteger>();
		for(int n=1;n<=MAX;n++){
			BigInteger p = BigInteger.valueOf(n).multiply(BigInteger.valueOf(3)).subtract(BigInteger.ONE).multiply(BigInteger.valueOf(n)).divide(BigInteger.valueOf(2));
			pentagonalNumbers.add(p);
			BigInteger h= BigInteger.valueOf(n).multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE).multiply(BigInteger.valueOf(n));
			hexagonalNumbers.add(h);
		}
		
		for(int n=start;n<=MAX;n++){
			BigInteger triangle = BigInteger.valueOf(n).add(BigInteger.ONE).multiply(BigInteger.valueOf(n)).divide(BigInteger.valueOf(2));
			
			if(pentagonalNumbers.contains(triangle) && hexagonalNumbers.contains(triangle)){
				return n;
			}
		}
		
		return 0;
	}
	
	/**
	 * t was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
	 * 
	 * 9 = 7 + 212
	 * 15 = 7 + 222
	 * 21 = 3 + 232
	 * 25 = 7 + 232
	 * 27 = 19 + 222
	 * 33 = 31 + 212
	 * 
	 * It turns out that the conjecture was false.
	 * 
	 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
	 * @param limit
	 * @return
	 */
	public static int p046FindFirstOddComposisteNumberNotMatchFormula(int limit){
		int[] primeList = PrimeUtil.getAllPrimeBelowN(limit);
		BitSet primeBitSet=new BitSet();
		for(int i=1;i<primeList.length;i++){
			primeBitSet.set(primeList[i]);
		}
		
		BitSet squareBitSet=new BitSet();
		for(int i=0;i<=Math.sqrt(limit);i++){
			squareBitSet.set(i*i);
		}
		
		for(int number=2; number<=limit;number++ ){
			if(number%2!=0 && !primeBitSet.get(number)){
				boolean match=false;
				for(int i=1;i<primeList.length && primeList[i]<number;i++){
					if(primeList[i]%2!=0){
						int value=(number-primeList[i])/2;
						if(squareBitSet.get(value)){
							match=true;
							break;
						}
					}
				}
				if(!match){
					return number;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * The first two consecutive numbers to have two distinct prime factors are:
	 * 
	 * 14 = 2  7
	 * 15 = 3  5
	 * 
	 * The first three consecutive numbers to have three distinct prime factors are:
	 * 
	 * 644 = 2²  7  23
	 * 645 = 3  5  43
	 * 646 = 2  17  19.
	 * 
	 * Find the first four consecutive integers to have four distinct primes factors. What is the first of these numbers?
	 * @param limit
	 * @return
	 */
	public static int p047FindFirst4ConsecutiveNumberWith4PrimeFactors(){
		int number=2;
		
		while(true){
			int[] primeFactors = PrimeUtil.getPrimeFactors(number);
			
			if(primeFactors.length==4){
				int count=1;
				while(count<4){
					int next= number+count;
					
					int [] nextPrimeFactors = PrimeUtil.getPrimeFactors(next);

					if(nextPrimeFactors.length!=4){
						break;
					}
					
					count++;
				}
				
				if(count==4){
					return number;
				}
				
				number=number+count+1;
			}else{
				number++;
			}
		}
	}
	
	
	/**
	 * The series, 11 + 22 + 33 + ... + 1010 = 10405071317.
	 * 
	 * Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.
	 * 
	 * @param max
	 * @return
	 */
	public static String p048StringFindLast10DigitOfSeriesSum(int max){
		BigInteger sum=BigInteger.ZERO;
		
		for(int i=1;i<=max;i++){
			BigInteger number=BigInteger.valueOf(i).pow(i);
			sum=sum.add(number);
		}
		
		String result="";
		for(int i=0;i<10;i++){
			BigInteger[] divideAndRemainder = sum.divideAndRemainder(BigInteger.TEN);
			
			result = divideAndRemainder[1].toString()+result;
			sum=divideAndRemainder[0];
		}
		
		return result;
	}
	
	/**
	 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
	 * 
	 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
	 * 
	 * What 12-digit number do you form by concatenating the three terms in this sequence?
	 * @return
	 */
	public static Triplet p049FindThreeIncreasingPermutePrimes(){
		int[] primeList = PrimeUtil.getAllPrimeBelowN(10000);
		BitSet primeBitSet=new BitSet();
		for(int i=1;i<primeList.length;i++){
			primeBitSet.set(primeList[i]);
		}
		
		for(int i=1;i<primeList.length;i++){
			if(primeList[i]>1000 && primeList[i]!=1487){
				int next1=primeList[i]+3330;
				int next2=next1+3330;
				
				if(primeBitSet.get(next1) && NumberUtil.isPermute(primeList[i], next1)
						&& primeBitSet.get(next2) && NumberUtil.isPermute(primeList[i], next2)){
					return new Triplet(primeList[i], next1, next2);
				}
			}
		}
		
		return null;
	}
	
}
