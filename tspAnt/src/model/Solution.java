package model;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	private static String[] CITYNAMES;
	private static boolean CITYNAMESARESET = false;

	private List<Integer> orderedNodeList;
	private int distance;

	public Solution(List<Integer> orderedNodeList, int[][] distances) {
		super();
		this.orderedNodeList = orderedNodeList;
		int distance = calculateDistance(orderedNodeList, distances);
		this.distance = distance;
	}

	public Solution(List<Edge> edgeList, int[][] distances, boolean dummy) {
		// dummy is just needed to overload the constructor
		List<Integer> orderedNodeList = new ArrayList<Integer>();
		orderedNodeList.add(0);
		int initialFrom = 0;
		int from = initialFrom;

		boolean b = true;
		while (b) {
			for (int i = 0; i < edgeList.size(); i++) {
				if (edgeList.get(i).getNodeFrom() == from) {
					orderedNodeList.add(edgeList.get(i).getNodeTo());
					from = edgeList.get(i).getNodeTo();
					if (from == initialFrom) {
						b = false;
					}
					break;
				}
			}
		}
		
		this.orderedNodeList = orderedNodeList;
		this.distance = calculateDistance(orderedNodeList, distances);
		
	}

	private int calculateDistance(List<Integer> orderedNodeList,
			int[][] distances) {
		if (orderedNodeList.size() <= 1) {
			return Integer.MAX_VALUE;
		}
		int distance = 0;

		for (int i = 0; i < orderedNodeList.size() - 1; i++) {
			distance += distances[orderedNodeList.get(i)][orderedNodeList
					.get(i + 1)];
		}

		return distance;
	}

	public List<Integer> getOrderedNodeList() {
		return orderedNodeList;
	}

	public int getDistance() {
		return distance;
	}

	public static void setCityNames(String[] cityNames) {
		if (CITYNAMESARESET) {
			System.out
					.println("The city names can only be set once per run time");
		} else {
			CITYNAMES = cityNames;
			CITYNAMESARESET = true;
		}
	}

	@Override
	public String toString() {
		String s = "Solution: \n";

		for (int i = 0; i < orderedNodeList.size(); i++) {
			if (i + 1 == orderedNodeList.size()) {
				s += orderedNodeList.get(i) + "\n";
			} else {
				s += orderedNodeList.get(i) + " --> ";
			}
		}
		for (int i = 0; i < orderedNodeList.size(); i++) {
			if (i + 1 == orderedNodeList.size()) {
				s += CITYNAMES[orderedNodeList.get(i)] + "\n";
			} else {
				s += CITYNAMES[orderedNodeList.get(i)] + " --> ";
			}
		}

		s += "Distance: " + distance + " km";

		return s;
	}

}
