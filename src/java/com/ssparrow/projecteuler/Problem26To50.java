/**
 * 
 */
package com.ssparrow.projecteuler;

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
}
