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
public class NumberUtilTest {

	@Test
	public void testGetNumberDigits() {
		int number=123456;
		
		assertArrayEquals(new int[]{6,5,4,3,2,1}, NumberUtil.getNumberDigits(number, Endian.LITTLE));

		assertArrayEquals(new int[]{1,2,3,4,5,6}, NumberUtil.getNumberDigits(number, Endian.BIG));
	}
	
	@Test
	public void testGetBigIntegerNumberDigits(){
		BigInteger number=BigInteger.valueOf(123456);
		
		assertArrayEquals(new int[]{6,5,4,3,2,1}, NumberUtil.getBigIntegerNumberDigits(number, Endian.LITTLE));

		assertArrayEquals(new int[]{1,2,3,4,5,6}, NumberUtil.getBigIntegerNumberDigits(number, Endian.BIG));
	}
	
	@Test
	public void testGetNumber() {
		int [] digits=new int[]{6,5,4,3,2,1};
		
		assertEquals(654321, NumberUtil.getNumber(digits, 0, digits.length));
	}

	@Test
	public void testGetBigIntegerNumber(){
		int [] digits=new int[]{6,5,4,3,2,1};
		
		assertEquals(BigInteger.valueOf(654321), NumberUtil.getBigIntegerNumber(digits, 0, digits.length));
	}
	
	@Test
	public void testIsPalindrome(){
		assertTrue(NumberUtil.isPalindrome(BigInteger.ONE));

		assertTrue(NumberUtil.isPalindrome(BigInteger.valueOf(11)));

		assertFalse(NumberUtil.isPalindrome(BigInteger.valueOf(12)));

		assertTrue(NumberUtil.isPalindrome(BigInteger.valueOf(121)));
	}
}
