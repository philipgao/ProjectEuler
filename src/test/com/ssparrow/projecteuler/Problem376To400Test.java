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
public class Problem376To400Test {

	@Test
	public void testP393GetAntRoutes() {
		assertEquals(BigInteger.valueOf(2), Problem376To400.p393GetAntRoutes(2));

		assertEquals(BigInteger.valueOf(88), Problem376To400.p393GetAntRoutes(4));

		assertEquals(BigInteger.valueOf(207408), Problem376To400.p393GetAntRoutes(6));

//		assertEquals(BigInteger.valueOf(207408), Problem376To400.p393GetAntRoutes(7));
//
//		assertEquals(BigInteger.valueOf(88), Problem376To400.p393GetAntRoutes(10));
	}

}
