/**
 * 
 */
package com.ssparrow.projecteuler;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Gao, Fei
 *
 */
public class PrimeUtilTest {

	@Test
	public void testGetAllDivisors() {
		assertArrayEquals(new int []{1,3,2}, PrimeUtil.getAllDivisors(6));

		assertArrayEquals(new int []{1,5,2}, PrimeUtil.getAllDivisors(10));

		assertArrayEquals(new int []{1,5,2,10,4}, PrimeUtil.getAllDivisors(20));

		assertArrayEquals(new int []{1, 7, 2, 14, 4}, PrimeUtil.getAllDivisors(28));

		int[] allDivisors = PrimeUtil.getAllDivisors(220);
		assertArrayEquals(new int []{1, 11, 5, 55, 2, 22, 10, 110, 4, 44, 20}, allDivisors);
		
		int sum=0;
		for(int i=0;i<allDivisors.length;i++){
			sum+=allDivisors[i];
		}
		assertEquals(284, sum);
		
		allDivisors = PrimeUtil.getAllDivisors(284);
		assertArrayEquals(new int []{1, 71, 2, 142, 4}, allDivisors);
		
		sum=0;
		for(int i=0;i<allDivisors.length;i++){
			sum+=allDivisors[i];
		}
		assertEquals(220, sum);
	}

}
