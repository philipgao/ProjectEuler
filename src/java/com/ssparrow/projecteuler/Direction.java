/**
 * 
 */
package com.ssparrow.projecteuler;

enum Direction{
	
	UP("^"),DOWN("v"),LEFT("<"),RIGHT(">"),DIAGONAL_RIGHT("\\"),DIAGONAL_LEFT("/");
	

	String expression;

	/**
	 * @param expression
	 */
	private Direction(String expression) {
		this.expression = expression;
	}

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}
	
	
}