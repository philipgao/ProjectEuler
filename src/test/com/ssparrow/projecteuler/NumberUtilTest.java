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
public class NumberUtilTest {

	@Test
	public void testGetNumberDigits() {
		int number=123456;
		
		assertArrayEquals(new int[]{6,5,4,3,2,1}, NumberUtil.getNumberDigits(number, Endian.LITTLE));

		assertArrayEquals(new int[]{1,2,3,4,5,6}, NumberUtil.getNumberDigits(number, Endian.BIG));
	}
	
	public void testGetNumber() {
		int [] digits=new int[]{6,5,4,3,2,1};
		
		assertEquals(654321, NumberUtil.getNumber(digits, 0, digits.length));
	}

}
