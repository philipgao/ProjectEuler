/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author Gao, Fei
 *
 */
public class NumberUtil {

	/**
	 * @param n
	 * @return
	 */
	public static boolean isPalindrome(int n){
		return isPalindrome(n, 10);
	}
	
	/**
	 * @param number
	 * @return
	 */
	public static boolean isPalindrome(BigInteger number){
		int [] digits=getBigIntegerNumberDigits(number, Endian.BIG);
		int [] reverseDigits=getBigIntegerNumberDigits(number, Endian.LITTLE);
		
		return Arrays.equals(digits, reverseDigits);
	}
	
	public static boolean isPalindrome(int n, int radix){
		String str = null;
		switch (radix) {
			case 10:
				str=String.valueOf(n);
				break;
			case 2:
				str=Integer.toBinaryString(n);
				break;
			case 8:
				str=Integer.toOctalString(n);
				break;
			case 16:
				str=Integer.toHexString(n);
				break;
			default:
				break;
		}
		
		
		if(str!=null){
			return isPalindrome(str);
		}
		
		return false;
	}
	
	public static boolean isPalindrome(String str){
		int forwardIndex=0;
		int backIndex=0;
		int fastIndex=0;
		
		for(;forwardIndex<str.length();forwardIndex++,fastIndex+=2){
			if(fastIndex==str.length()-1){
				backIndex=forwardIndex-1;
			}else if(fastIndex==str.length()){
				backIndex=forwardIndex-1;
				forwardIndex--;
			}else if(fastIndex>str.length()){
				if(str.charAt(backIndex)!=str.charAt(forwardIndex)){
					return false;
				}
				backIndex--;
			}
		}
		
		return true;
	}

	public static boolean isPermute(int source, int target){
		char[] sourceArray = String.valueOf(source).toCharArray();
		Arrays.sort(sourceArray);
		String sortedSource=new String(sourceArray);
		
		char[] targetArray = String.valueOf(target).toCharArray();
		Arrays.sort(targetArray);
		String sortedTarget=new String(targetArray);
		
		return sortedSource.equals(sortedTarget);
	}

	public static int getNumber(int [] digits, int start, int length){
		int end=start+length-1;
		
		int index=start;
		int number=0;
		while(index<=end){
			number=number*10+digits[index];
			index++;
		}
		
		return number;
	}

	public static int [] getNumberDigits(int number, Endian endian){
		int [] temp=new int [10];
		int index=0;
		
		while(number>0){
			temp[index++]=number%10;
			number=number/10;
		}
		
		int [] result=new int[index];
		
		if(endian==Endian.LITTLE){
			System.arraycopy(temp, 0, result, 0, index);
		}else if(endian==Endian.BIG){
			for(int i=0;i<index;i++){
				result[index-1-i]=temp[i];
			}
		}
		
		return result;
	}

	/**
	 * @param number
	 * 
	 * @param endian
	 * @return
	 */
	public static int [] getBigIntegerNumberDigits(BigInteger number, Endian endian){
		int [] temp=new int[100];
		
		int index=0;
		while(number.compareTo(BigInteger.ZERO)>0){
			BigInteger[] divideAndRemainder = number.divideAndRemainder(BigInteger.TEN);
			
			temp[index++]=divideAndRemainder[1].intValue();
			
			number=divideAndRemainder[0];
		}
		
		int [] result=new int[index];
		
		if(endian==Endian.LITTLE){
			System.arraycopy(temp, 0, result, 0, index);
		}else{
			for(int i=0;i<index;i++){
				result[index-1-i]=temp[i];
			}
		}
		
		return result;
	}
	
	public static BigInteger getBigIntegerNumber(int []digits, int start, int length){
		BigInteger number = BigInteger.ZERO;
		
		for(int i=start;i<start+length;i++){
			number = number.multiply(BigInteger.TEN).add(BigInteger.valueOf(digits[i]));
		}
		
		return number;
	}
}
