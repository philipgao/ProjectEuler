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
public class Problem176To200Test {

	@Test
	public void testP189GetPossibleColoringPlans() {
		assertEquals(BigInteger.valueOf(528), Problem176To200.p189GetPossibleColoringPlans(3));

		assertEquals(BigInteger.valueOf(5332224), Problem176To200.p189GetPossibleColoringPlans(5));

//		assertEquals(5332224, Problem176To200.p189GetPossibleColoringPlans(6));
		
//		assertEquals(528, Problem176To200.p189GetPossibleColoringPlans(8));
	}

}
