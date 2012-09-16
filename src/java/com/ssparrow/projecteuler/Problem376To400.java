/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * @author Gao, Fei
 *
 */
public class Problem376To400 {
	/**
	 * Let n be an integer and S(n) be the set of factors of n.
	 * 
	 * A subset A of S(n) is called an antichain of S(n) if A contains only one element or if none of the elements of A divides any of the other elements of A.
	 * 
	 * For example: S(30) = {1, 2, 3, 5, 6, 10, 15, 30} 
	 * {2, 5, 6} is not an antichain of S(30). 
	 * {2, 3, 5} is an antichain of S(30).
	 * 
	 * Let N(n) be the maximum length of an antichain of S(n).
	 * 
	 * Find ΣN(n) for 1  n  108
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger p386GetSumOfAntichainChainlength(int n){
		int [] count = new int[n+1];
		count[0]=0;
		count[1]=1;
		
		int lastPrime=2;
		while(lastPrime<n){
			for(int i=2;i*lastPrime<=n;i++){
				count[i*lastPrime]=count[i*lastPrime]+1;
			}
			
			boolean found=false;
			for(int i=lastPrime+1;i<=n;i++){
				if(count[i]==0){
					found=true;
					lastPrime=i;
					count[i]=1;
					break;
				}
			}
			
			if(!found){
				break;
			}
		}
		
		BigInteger sum=BigInteger.ONE;
		for(int i=1;i<count.length;i++){
			sum=sum.add(BigInteger.valueOf(count[i]));
		}
		
		return sum;
		
//		result=result.add(BigInteger.valueOf(primeList.length-1));
//		for(int number=2; number<=limit;number++){
//			if(!primeFlags.get(number)){
//				int temp=number;
//				
//				int count=0;
//				for(int i=1;i<primeList.length && primeList[i]<=temp;i++){
//					if(temp>0 && temp%primeList[i]==0){
//						count++;
//						while(temp>0 && temp%primeList[i]==0){
//							temp=temp/primeList[i];
//						}
//					}
//				}
//				
//				result=result.add(BigInteger.valueOf(count));
//			}
//		}
		
	}
	
	private static BigInteger getSumOfAntichainChainlength(int limit, int[] primeList, int primeIndex, int baseCount, int baseNumber){
		System.out.println(primeIndex);
		if(primeIndex==primeList.length){
			return BigInteger.ZERO;
		}
		
		int number = baseNumber;
		BigInteger result=BigInteger.ZERO;
		
		BigInteger count=getSumOfAntichainChainlength(limit, primeList, primeIndex+1, baseCount, number);
		result=result.add(count);
		
		for(int exp=1;baseNumber*Math.pow(primeList[primeIndex], exp)<=limit;exp++){
			number = (int) (baseNumber*Math.pow(primeList[primeIndex], exp));
			result = result.add(BigInteger.valueOf(baseCount+1));
					
			count=getSumOfAntichainChainlength(limit, primeList, primeIndex+1, baseCount+1, number);
			result=result.add(count);
		}
		
		return result;
	}
	
	/**
	 * An nn grid of squares contains n2 ants, one ant per square.
	 * All ants decide to move simultaneously to an adjacent square (usually 4 possibilities, except for ants on the edge of the grid or at the corners).
	 * We define f(n) to be the number of ways this can happen without any ants ending on the same square and without any two ants crossing the same edge between two squares.
	 * 
	 * You are given that f(4) = 88.
	 * Find f(10).
	 * 
	 * @return
	 */
	public static BigInteger p393GetAntRoutes(int n){
		Direction [][] directions=new Direction[n][n];
		
		return findAllAntRoutes(directions, 0, 0);
	}
	
	private static BigInteger findAllAntRoutes(Direction [][] directions, int i, int j){
		int n=directions.length;
		
		if(i==n){
//			for(int r=0;r<n;r++){
//				for(int c=0;c<n;c++){
//					System.out.print(directions[r][c].getExpression()+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
			return BigInteger.ONE;
		}
		
		BigInteger count=BigInteger.ZERO;
		
		int nextRow=(j==n-1?i+1:i);
		int nextColumn=(j==n-1?0:j+1);
		
		if(j>0 && directions[i][j-1]!=Direction.RIGHT &&
				(j==1 || directions[i][j-2]!=Direction.RIGHT)&&
				(i==0 || directions[i-1][j-1]!=Direction.DOWN)){
			directions[i][j]=Direction.LEFT;
			
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		if(i>0 && directions[i-1][j]!=Direction.DOWN &&
				(i==1 || directions[i-2][j]!=Direction.DOWN)&&
				(j==0 || directions[i-1][j-1]!=Direction.RIGHT)&&
				(j==n-1 || directions[i-1][j+1]!=Direction.LEFT)){
			directions[i][j]=Direction.UP;
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		if(j<n-1 && 
				(i==0 || directions[i-1][j+1]!=Direction.DOWN)){
			directions[i][j]=Direction.RIGHT;
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		if(i<n-1){
			directions[i][j]=Direction.DOWN;
			count = count.add(findAllAntRoutes(directions, nextRow, nextColumn));
		}
		
		return count;
		
	}
}
