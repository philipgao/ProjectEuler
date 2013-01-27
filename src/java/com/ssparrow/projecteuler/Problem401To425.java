/**
 * 
 */
package com.ssparrow.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.ssparrow.projecteuler.Point.UnComparableException;

/**
 * @author Gao, Fei
 *
 */
public class Problem401To425 {

	/**
	 *  Let n be a positive integer. Suppose there are stations at the coordinates (x, y) = (2^i mod n, 3^i mod n) for 0<=i<=2n. 
	 *  We will consider stations with the same coordinates as the same station.

		We wish to form a path from (0, 0) to (n, n) such that the x and y coordinates never decrease.
		Let S(n) be the maximum number of stations such a path can pass through.
		
		For example, if n = 22, there are 11 distinct stations, and a valid path can pass through at most 5 stations. 
		Therefore, S(22) = 5. The case is illustrated below, with an example of an optimal path:
		
		It can also be verified that S(123) = 14 and S(10000) = 48.
		
		Find  S(k5) for 1  k  30.
	 * @param n
	 * @return
	 */
	public static int p411GetMaxUphillPathLength(int n){
		Comparator<Point> yComparator=new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.getY()-o2.getY();
			}
		};
		
		SortedMap<Integer, SortedSet<Point>> pointMap=new TreeMap<Integer, SortedSet<Point>>();
		for(int i=0;i<=2*n;i++){
			int x = BigInteger.valueOf(2).pow(i).divideAndRemainder(BigInteger.valueOf(n))[1].intValue();
			int y = BigInteger.valueOf(3).pow(i).divideAndRemainder(BigInteger.valueOf(n))[1].intValue();
			
			SortedSet<Point> xSet=pointMap.get(x);
			if(xSet==null){
				xSet=new TreeSet<Point>(yComparator);
				pointMap.put(x, xSet);
			}
			
			xSet.add(new Point(x, y));
		}
		
		List<Point> pointList=new ArrayList<Point>();
		for(Integer x:pointMap.keySet()){
			pointList.addAll(pointMap.get(x));
		}
		
		Map<Integer, List<Point>> results=new HashMap<Integer, List<Point>>();
		GetMaxUphillPathLength(results, pointList, pointList.size()-1);
		
		int maxPathLength=Integer.MIN_VALUE;
		for(Integer key: results.keySet()){
			int pathLength = results.get(key).size();
			if(pathLength>maxPathLength){
				maxPathLength=pathLength;
			}
		}
		
		return maxPathLength;
	}
	
	private static List<Point> GetMaxUphillPathLength(Map<Integer, List<Point>> results, List<Point> pointList, int index){
		if(results.get(index)!=null){
			return results.get(index);
		}
		
		List<Point> maxPath=new ArrayList<Point>();
		Point current = pointList.get(index);
		maxPath.add(current);
		
		for(int i=index-1;i>=0;i--){
			List<Point> subResult = GetMaxUphillPathLength(results, pointList, i);
			
			try{
				int compare=current.compareTo(subResult.get(subResult.size()-1));
				
				if(compare>0 && subResult.size()+1>=maxPath.size()){
					maxPath=new ArrayList<Point>();
					maxPath.addAll(subResult);
					maxPath.add(current);
				}
			}catch(UnComparableException ex){
			}
		}
		
		results.put(index, maxPath);
		return maxPath;
	}
	
}
