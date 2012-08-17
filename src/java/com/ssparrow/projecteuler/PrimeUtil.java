/**
 * 
 */
package com.ssparrow.projecteuler;

/**
 * @author Gao, Fei
 *
 */
public class PrimeUtil {

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

}
