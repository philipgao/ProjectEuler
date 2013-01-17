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
public class Problem76To100Test {
	
	@Test
	public void testP076GetPossibleCombinationCount(){
		assertEquals(6, Problem76To100.p076GetPossibleCombinationCount(5));
		
//		assertEquals(41, Problem76To100.p076GetPossibleCombinationCount(50));

//		assertEquals(626, Problem76To100.p076GetPossibleCombinationCount(20));

//		assertEquals(1957, Problem76To100.p076GetPossibleCombinationCount(25));
		
//		assertEquals(6, Problem76To100.p076GetPossibleCombinationCount(100));
	}

	@Test
	public void testP083FindMinimalPath() throws IOException {
		int [][] matrix= new int[][]{
								{131,673,234,103,18},
								{201,96,342,965,150},
								{630,803,746,422,111},
								{537,699,497,121,956},
								{805,732,524,37,331}};
		assertEquals(2297, Problem76To100.p083FindMinimalPath(matrix));
		
		assertEquals(425185, Problem76To100.p083FindMinimalPath("test/data/p083.txt",80));
	}

}
