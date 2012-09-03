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
public class Problem26To50Test {

	@Test
	public void testP026FindLongestRecurringCircle() {
		assertEquals(7, Problem26To50.p026FindLongestRecurringCircle(10));

		assertEquals(97, Problem26To50.p026FindLongestRecurringCircle(100));

		assertEquals(983, Problem26To50.p026FindLongestRecurringCircle(1000));

	}

	@Test
	public void testP027GetQuadraticEexpressionWithMaxNumberOfPrimes(){
		assertEquals(new Pair(-3, 7), Problem26To50.p027GetQuadraticEexpressionWithMaxNumberOfPrimes(10, 10));

		assertEquals(new Pair(-15, 97), Problem26To50.p027GetQuadraticEexpressionWithMaxNumberOfPrimes(100, 100));

		assertEquals(new Pair(-61, 971), Problem26To50.p027GetQuadraticEexpressionWithMaxNumberOfPrimes(1000, 1000));
	}
}
