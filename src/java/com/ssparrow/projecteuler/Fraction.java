/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;

/**
 * @author Gao, Fei
 *
 */
public class Fraction {
	private BigInteger numerator;
	private BigInteger denominator;
	/**
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(BigInteger numerator, BigInteger denominator) {
		super();
		this.numerator = numerator;
		this.denominator = denominator;
	}
	/**
	 * @return the numerator
	 */
	public BigInteger getNumerator() {
		return numerator;
	}
	/**
	 * @param numerator the numerator to set
	 */
	public void setNumerator(BigInteger numerator) {
		this.numerator = numerator;
	}
	/**
	 * @return the denominator
	 */
	public BigInteger getDenominator() {
		return denominator;
	}
	/**
	 * @param denominator the denominator to set
	 */
	public void setDenominator(BigInteger denominator) {
		this.denominator = denominator;
	}
	
	
}
