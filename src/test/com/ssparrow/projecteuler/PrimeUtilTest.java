/**
 * 
 */
package com.ssparrow.projecteuler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void testGetPrimeFactors(){
		assertArrayEquals(new int []{2, 3}, PrimeUtil.getPrimeFactors(6));

		assertArrayEquals(new int []{2,5}, PrimeUtil.getPrimeFactors(10));

		assertArrayEquals(new int []{11}, PrimeUtil.getPrimeFactors(11));

		assertArrayEquals(new int []{2,5}, PrimeUtil.getPrimeFactors(20));

		assertArrayEquals(new int []{2, 7}, PrimeUtil.getPrimeFactors(28));

		assertArrayEquals(new int []{2, 5, 11}, PrimeUtil.getPrimeFactors(220));
		
		assertArrayEquals(new int []{3, 7, 13, 491}, PrimeUtil.getPrimeFactors(134043));
		
	}
	
	@Test
	public void testIsPrime(){
		assertFalse(PrimeUtil.isPrime(1));

		assertTrue(PrimeUtil.isPrime(2));

		assertTrue(PrimeUtil.isPrime(3));

		assertFalse(PrimeUtil.isPrime(4));

		assertTrue(PrimeUtil.isPrime(5));

		assertFalse(PrimeUtil.isPrime(6));
		
		assertTrue(PrimeUtil.isPrime(98765431));

		assertFalse(PrimeUtil.isPrime(987654321));
	}

	
	@Test
	public void testGetLowestCommonDenominator(){
		List<Pair> fractions=new ArrayList<Pair>();
		fractions.add(new Pair(5, 12));
		fractions.add(new Pair(11, 18));
		assertEquals(36, PrimeUtil.getLowestCommonDenominator(fractions));
	}

}
