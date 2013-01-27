/**
 * 
 */
package com.ssparrow.projecteuler;

/**
 * @author Gao, Fei
 *
 */
public class Point{
	private int x;
	private int y;
	
	/**
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param target
	 * @return
	 * @throws UnComparableException
	 */
	public int compareTo(Point target) throws UnComparableException{
		if(this.getX()==target.getX() && this.getY()==target.getY()){
			return 0;
		}else if((this.getX()<target.getX() && this.getY()<target.getY())||
				(this.getX()==target.getX() && this.getY()<target.getY())||
				(this.getX()<target.getX() && this.getY()==target.getY())){
			return -1;
		}else if((this.getX()>target.getX() && this.getY()>target.getY())||
				(this.getX()==target.getX() && this.getY()>target.getY())||
				(this.getX()>target.getX() && this.getY()==target.getY())){
			return 1;
		}else{
			throw new UnComparableException();
		}
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}



	public static class UnComparableException extends Exception{
		
	}
}

