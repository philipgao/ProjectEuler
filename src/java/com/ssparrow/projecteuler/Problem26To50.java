/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
}
