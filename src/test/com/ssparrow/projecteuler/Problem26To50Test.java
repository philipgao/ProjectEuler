/**
 * 
 */
package com.ssparrow.projecteuler;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	@Test
	public void testP028GetDiagonalSumOfSpiralArray(){
		assertEquals(BigInteger.valueOf(101), Problem26To50.p028GetDiagonalSumOfSpiralArray(5));

		assertEquals(BigInteger.valueOf(692101), Problem26To50.p028GetDiagonalSumOfSpiralArray(101));
		
		assertEquals(BigInteger.valueOf(669171001), Problem26To50.p028GetDiagonalSumOfSpiralArray(1001));
	}
	
	@Test
	public void testP029CalculateDistinctTerms(){
		assertEquals(15, Problem26To50.p029CalculateDistinctTerms(5, 5));

		assertEquals(69, Problem26To50.p029CalculateDistinctTerms(10, 10));
		
		assertEquals(9183, Problem26To50.p029CalculateDistinctTerms(100, 100));
	}
	
	@Test
	public void testP030FindNumberOfNPowerSum(){
		List<Integer> result = Problem26To50.p030FindNumberOfNPowerSum(4);
		assertEquals(3	, result.size());
		assertEquals(Integer.valueOf(1634)	, result.get(0));
		assertEquals(Integer.valueOf(8208)	, result.get(1));
		assertEquals(Integer.valueOf(9474)	, result.get(2));
		int sum=0;
		for(Integer number:result){
			sum+=number;
		}
		assertEquals(19316, sum);
		
		result = Problem26To50.p030FindNumberOfNPowerSum(5);
		assertEquals(6	, result.size());
		sum=0;
		for(Integer number:result){
			sum+=number;
		}
		assertEquals(443839, sum);
	}
	
	
	@Test
	public void testP031FindCoinCombinations(){
		//5, 2 2 1, 2 1 1 1, 11111
		assertEquals(4, Problem26To50.p031FindCoinCombinations(5, 200));

		assertEquals(11, Problem26To50.p031FindCoinCombinations(10, 200));
		
		assertEquals(73682, Problem26To50.p031FindCoinCombinations(200, 200));
	}
	
	@Test
	public void testP032FindAllPandigitalNumber(){
		Map<Integer, Pair> allPandigitalNumber = Problem26To50.p032FindAllPandigitalNumber();
		Set<Integer> keySet = allPandigitalNumber.keySet();
		int sum=0;
		for(Integer number:keySet){
			sum+=number;
		}
		assertEquals(45228, sum);
	}
	
	@Test
	public void testP033GetLowestCommonDenominatorForCuriousFractions(){
		assertEquals(100, Problem26To50.p033GetLowestCommonDenominatorForCuriousFractions());
	}
	
	@Test
	public void testp034FindAllNumbersSumToDigitsFactorials(){
		List<Integer> result = Problem26To50.p034FindAllNumbersSumToDigitsFactorials();
		assertEquals(2, result.size());
		
		int sum=0;
		for(Integer number:result){
			sum+=number;
		}
		assertEquals(40730, sum);
	}
}
