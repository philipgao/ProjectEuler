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
	public void testP067FindFindMaxPathInMatrix() throws IOException {
		assertEquals(23, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-4.txt",4));

		assertEquals(1074, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-15.txt",15));

		assertEquals(7273, Problem51To75.p067FindFindMaxPathInMatrix("test/data/p067-100.txt",100));
	}

}
