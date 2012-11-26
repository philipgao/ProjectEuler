/**
 * 
 */
package com.ssparrow.projecteuler;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

/**
 * @author Gao, Fei
 *
 */
public class Problem201To225Test {

	@Test
	public void testP201FindSumOfAllGivenSizeSubset() {
		int [] array=new int[] {1,3,6,8,10,11};
		assertEquals(BigInteger.valueOf(156), Problem201To225.p201FindSumOfAllGivenSizeSubset(array, 3));
		
		array=new int[100];
		for(int i=1;i<=100;i++){
			array[i-1]=i*i;
		}
//		assertEquals(BigInteger.valueOf(156), Problem201To225.p201FindSumOfAllGivenSizeSubset(array, 50));
	}

}
