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
public class Problem401To425Test {

	@Test
	public void testP411GetMaxUphillPathLength() {
		assertEquals(5, Problem401To425.p411GetMaxUphillPathLength(22));

		assertEquals(11, Problem401To425.p411GetMaxUphillPathLength(50));

//		assertEquals(14, Problem401To425.p411GetMaxUphillPathLength(123));

//		assertEquals(48, Problem401To425.p411GetMaxUphillPathLength(10000));
	}

}
