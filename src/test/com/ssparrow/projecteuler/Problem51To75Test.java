/**
 * 
 */
package com.ssparrow.projecteuler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigInteger;

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
	public void testP052FindSmallestNumberWithSameDigitsAsItsTimes(){
		assertEquals(142857, Problem51To75.p052FindSmallestNumberWithSameDigitsAsItsTimes());
	}
	
	@Test
	public void testP053FindCountOfCnrLargerThanTarget(){
		assertEquals(4075, Problem51To75.p053FindCountOfCnrLargerThanTarget(100, 1000000));
	}
	
	@Test
	public void testP055GetNumberOfLychrelNumber(){
		assertEquals(249, Problem51To75.p055GetNumberOfLychrelNumber(10000));
	}

	@Test
	public void testP057GetCountOfSpecificSquareRootFraction(){
		assertEquals(0, Problem51To75.p057GetCountOfSpecificSquareRootFraction(5));

		assertEquals(1, Problem51To75.p057GetCountOfSpecificSquareRootFraction(10));

		assertEquals(153, Problem51To75.p057GetCountOfSpecificSquareRootFraction(1000));
	}
	
	@Test
	public void testP058GetAnticlockSpiralMatrixWithDiagPrimeRateBelowLimit(){
//		assertEquals(3, Problem51To75.p058GetAnticlockSpiralMatrixWithDiagPrimeRateBelowLimit(0.7));
//
//		assertEquals(309, Problem51To75.p058GetAnticlockSpiralMatrixWithDiagPrimeRateBelowLimit(0.2));
//
//		assertEquals(981, Problem51To75.p058GetAnticlockSpiralMatrixWithDiagPrimeRateBelowLimit(0.15));

		assertEquals(26241, Problem51To75.p058GetAnticlockSpiralMatrixWithDiagPrimeRateBelowLimit(0.1));
	}
	
	@Test
	public void testP067FindFindMaxPathInMatrix() throws IOException {
		assertEquals(23, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-4.txt",4));

		assertEquals(1074, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-15.txt",15));

		assertEquals(7273, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-100.txt",100));
	}
	
	@Test
	public void testp069GetTotientMaximum(){
		assertEquals(1, Problem51To75.getTotient(2));
		assertEquals(2, Problem51To75.getTotient(3));
		assertEquals(2, Problem51To75.getTotient(4));
		assertEquals(4, Problem51To75.getTotient(5));
		assertEquals(2, Problem51To75.getTotient(6));
		assertEquals(6, Problem51To75.getTotient(7));
		assertEquals(4, Problem51To75.getTotient(8));
		assertEquals(6, Problem51To75.getTotient(9));
		assertEquals(4, Problem51To75.getTotient(10));
		
		assertEquals(6, Problem51To75.p069GetTotientMaximum(10));

		assertEquals(210, Problem51To75.p069GetTotientMaximum(1000));

		assertEquals(2310, Problem51To75.p069GetTotientMaximum(10000));

//		assertEquals(210, Problem51To75.p069GetTotientMaximum(100000));
	}
	
	@Test
	public void testP070FIndMinRatioPermutedTotient(){
		assertTrue(NumberUtil.isPermute(87109, 79180));
		
		assertEquals(783169,  Problem51To75.p070FIndMinRatioPermutedTotient(1000000));
		
		assertEquals(8319823, Problem51To75.p070FIndMinRatioPermutedTotient(10000000));

	}
	
	@Test
	public void testP072GetReducedProperFractionCount(){
		assertEquals(BigInteger.valueOf(21), Problem51To75.p072GetReducedProperFractionCount(8));

		assertEquals(BigInteger.valueOf(304191), Problem51To75.p072GetReducedProperFractionCount(1000));

		assertEquals(BigInteger.valueOf(30397485), Problem51To75.p072GetReducedProperFractionCount(10000));

		assertEquals(new BigInteger("3039650753",10), Problem51To75.p072GetReducedProperFractionCount(100000));
	}
}
