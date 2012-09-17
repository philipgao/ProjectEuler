/**
 * 
 */
package com.ssparrow.projecteuler;

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

}
