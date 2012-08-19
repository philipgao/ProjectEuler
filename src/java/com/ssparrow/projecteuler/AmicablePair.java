package com.ssparrow.projecteuler;



public class AmicablePair{
	private int a;
	private int b;
	
	public AmicablePair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int small = a<b?a:b;
		int large = a<b?b:a;
		
		final int prime = 31;
		int result = 1;
		result = prime * result + small;
		result = prime * result + large;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AmicablePair){
			AmicablePair target=(AmicablePair)obj;
			
			return (a==target.getA()&&b==target.getB()) ||
						(b==target.getA()&&a==target.getB());
		}
		return false;
	}
	
	
}
