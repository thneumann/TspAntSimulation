package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Edge;
import model.Solution;
import model.TspMap;

public class EdgePerEdge extends AntStrategy {

	public EdgePerEdge(TspMap map) {
		super(map);
	}

	@Override
	public void doIterations(int n) {

		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < n; i++) {
			System.out.println("-----------------------");
			System.out.println("start " + (i + 1) + ". iteration");

			List<Edge> visitedEdgeList = new ArrayList<Edge>();

			// get all probabilities for all edges
			double[][] pheromones = map.getPheromonesCopy();

			// create c edges | c ist the number of citys
			while (visitedEdgeList.size() != map.getCityNames().length) {
				// prevent cycles (set probabilities to 0) and sum up the
				// probabilities
				double probSum = 0.0;
				for (int j = 0; j < pheromones.length; j++) {
					for (int k = 0; k < pheromones.length; k++) {
						if (pheromones[j][k] == 0.0) {
							continue;
						}
						// else
						if(visitedEdgeList.size() == map.getCityNames().length-1){
							//only one edge left, cycle is allowed
							probSum += pheromones[j][k];
						}else if (isCycle(j, k, visitedEdgeList)) {
							pheromones[j][k] = 0.0;
						} else {
							probSum += pheromones[j][k];
						}
					}
				}

				Random r = new Random();
				double randomValue = r.nextDouble() * probSum;
				boolean breakIt = false;
				Edge e = null;

				for (int j = 0; j < pheromones.length; j++) {
					for (int k = 0; k < pheromones[j].length; k++) {
						randomValue -= pheromones[j][k];
						if (randomValue < 0) {
							e = new Edge(j, k);
							visitedEdgeList.add(e);
							breakIt = true;
							break;
						}
					}
					if (breakIt) {
						break;
					}
				}
				// set the probability of all not possible edges to 0
				// prevent a degree of 3
				int from = e.getNodeFrom();
				int to = e.getNodeTo();
				for (int k = 0; k < pheromones.length; k++) {
					pheromones[from][k] = 0;
					pheromones[k][to] = 0;
				}
			}
			//create solution
			Solution s = new Solution(visitedEdgeList, map.getDistances(), true);
			System.out.println("Solution of the current iteration: ");
			System.out.println(s);
			
			if(s.getDistance() < this.currentBestSolution.getDistance()){
				System.out.println("We found a better solution");
				this.currentBestSolution = s;
				if(firstIterationWithBestResult == -1 && s.getDistance() == 2511){
					firstIterationWithBestResult = iterations+1;
				}
			}else{
				System.out.println("The solution of this iteration is not better than our current best solution");
			}
			
			//update pheromones
			updatePheromoneValues();
			iterations++;
		}
		this.time += System.currentTimeMillis() - startTime;
		
	}

	private boolean isCycle(int from, int to, List<Edge> edgeList) {
		// try to go to 'from' with all current edges
		int currentNode = to;
		boolean b = true;
		while (b) {
			b = false;
			for (int i = 0; i < edgeList.size(); i++) {
				if (edgeList.get(i).getNodeFrom() == currentNode) {
					if (edgeList.get(i).getNodeTo() == from) {
						return true;
					}
					// else
					currentNode = edgeList.get(i).getNodeTo();
					b = true;
					break;
				}
			}
		}
		return false;
	}

}
