/**
 * 
 */
package com.ssparrow.projecteuler;

import java.util.List;

/**
 * @author Gao, Fei
 *
 */
public class PrimeUtil {

	/**
	 * return prime list equal or below n
	 * Please notice that element 0 is kept for 1 although 1 is not prime, this is preserved for usage in special occasions
	 * 
	 * @param n
	 * @return
	 */
	public static int [] getAllPrimeBelowN(int n){
		if(n==1){
			return new int[]{1};
		}
		
		int [] temp = new int [n];
		boolean [] flags = new boolean[n+1];
		
		temp[0]=1;
		temp[1]=2;
		int index=2;
		
		int lastPrime=2;
		while(lastPrime<n){
			for(int i=2;i*lastPrime<=n;i++){
				flags[i*lastPrime]=true;
			}
			
			boolean found=false;
			for(int i=lastPrime+1;i<=n;i++){
				if(!flags[i]){
					found=true;
					lastPrime=i;
					temp[index++]=lastPrime;
					break;
				}
			}
			
			if(!found){
				break;
			}
		}
		
		int [] result= new int[index];
		System.arraycopy(temp, 0, result, 0, index);
		return result;
		
	}
	
	/**
	 * @param number
	 * @return
	 */
	public static boolean isPrime(int number){
		int [] primes=getAllPrimeBelowN(number);
		
		return primes.length>1 && primes[primes.length-1]==number;
	}
	
	/**
	 * @param number
	 * @return
	 */
	public static int [] getAllDivisors(int number){
		int [] divisors=new int[number];
		divisors[0]=1;
		int divisorIndex=1;
		
		int [] primeList = PrimeUtil.getAllPrimeBelowN(number);
		int [] primeDivisorExp=new int [primeList.length];
		
		int i=1;
		int temp=number;
		for(;i<primeList.length && primeList[i]<=temp;i++){
			if(temp%primeList[i]==0){
				int exp=0;
				while(temp%primeList[i]==0){
					exp++;
					temp=temp/primeList[i];
				}
				primeDivisorExp[i]=exp;
			}
		}
		
		//2^2 5 12
		int count = generateDivisorsFromPrimeFactors(number, primeList, 1, i, primeDivisorExp, divisors, 1, divisorIndex);
		
		int [] result=new int[count];
		System.arraycopy(divisors, 0, result, 0, count);
		
		return result;
	}
	
	private static int generateDivisorsFromPrimeFactors(int number,int [] primeList,int primeIndex, int primeLength, int [] primeDivisorExp, int [] divisors, int divisor, int  divisorIndex){
		if(primeIndex>=primeLength){
			return divisorIndex;
		}

		for(int k=1;k<=primeDivisorExp[primeIndex]+1;k++){
			if(divisor>1 && divisor<number && divisors[divisorIndex-1]!=divisor){
				divisors[divisorIndex++]=divisor;
			}
			
			divisorIndex = generateDivisorsFromPrimeFactors(number, primeList, primeIndex+1, primeLength, primeDivisorExp, divisors, divisor, divisorIndex);

			divisor *= primeList[primeIndex];
		}
		
		return divisorIndex;
	}
	
	/**
	 * @param fractions
	 * @return
	 */
	public static int getLowestCommonDenominator(List<Pair> fractions){
		int max=0;
		for(Pair pair:fractions){
			if(pair.getB()>max){
				max=pair.getB();
			}
		}
		
		int [] primeList=PrimeUtil.getAllPrimeBelowN(max);
		int [][] allPrimeExp = new int[fractions.size()][primeList.length];
		int index=0;
		for(Pair pair:fractions){
			int number = pair.getB();
			for(int i=1;i<primeList.length && primeList[i]<=number;i++){
				if(number%primeList[i]==0){
					int temp=number;
					int exp=0;
					while(temp%primeList[i]==0){
						exp++;
						temp=temp/primeList[i];
					}
					
					allPrimeExp[index][i]=exp;
				}
			}
			index++;
		}
		
		int [] primeExp=new int[primeList.length];
		for(int i=1;i<primeList.length;i++){
			int maxExp=0;
			for(int j=0;j<fractions.size();j++){
				if(allPrimeExp[j][i]>maxExp){
					maxExp=allPrimeExp[j][i];
				}
			}
			primeExp[i]=maxExp;
		}
		
		int result=1;
		for(int i=1;i<primeList.length;i++){
			result*=Math.pow(primeList[i], primeExp[i]);
		}
		
		return result;
		
	}
	
}
