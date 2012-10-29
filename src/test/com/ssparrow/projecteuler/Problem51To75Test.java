/**
 * 
 */
package com.ssparrow.projecteuler;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * @author Gao, Fei
 *
 */
public class Problem51To75Test {
	
	@Test
	public void testP051FindSmallest8PrimeValueFamily(){
		assertEquals(13, Problem51To75.p051FindSmallest8PrimeValueFamily(6));

		assertEquals(56003, Problem51To75.p051FindSmallest8PrimeValueFamily(7));
		
		assertEquals(121313, Problem51To75.p051FindSmallest8PrimeValueFamily(8));
	}
	
	@Test
	public void testP067FindFindMaxPathInMatrix() throws IOException {
		assertEquals(23, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-4.txt",4));

		assertEquals(1074, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-15.txt",15));

		assertEquals(7273, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-100.txt",100));
	}
	
	@Test
	public void testP070FIndMinRatioPermutedTotient(){
		assertTrue(NumberUtil.isPermute(87109, 79180));
		
		assertEquals(783169,  Problem51To75.p070FIndMinRatioPermutedTotient(1000000));
		
		assertEquals(8319823, Problem51To75.p070FIndMinRatioPermutedTotient(10000000));

	}

}
