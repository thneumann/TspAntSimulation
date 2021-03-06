package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.Solution;
import model.TspMap;

public class CityPerCity extends AntStrategy{

	public CityPerCity(TspMap map) {
		super(map);
		
	}

	@Override
	public void doIterations(int n) {
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < n; i++) {
			
			System.out.println("-----------------------");
			System.out.println("iteration: " + (i+1) + "/" + n);
			//System.out.println("Start at city with node 0 (" + map.getCityName(0) +").");
			
			List<Integer> visitedNodes = new ArrayList<Integer>();
			visitedNodes.add(0);
			int lastVisitedNode = 0;
			
			Set<Integer> notVisitedNodes = new HashSet<Integer>();
			for (int j = 1; j < map.getCityNames().length; j++) {
				notVisitedNodes.add(j);
			}
			
			Random r = new Random();
			
			//create the tour
			while(visitedNodes.size() < map.getCityNames().length){
				//get all probabilities from the current location
				double [] probability = map.getPheromones()[lastVisitedNode].clone();
				
				//set probability to 0 for all already visited Cities
				for (int j = 0; j < visitedNodes.size(); j++) {	
					probability[visitedNodes.get(j)] = 0;
				}
				//get the sum of all probabilities;
				double probSum = 0;
				for (int j = 0; j < probability.length; j++) {
					probSum += probability[j];
				}
				
				//get random number between 0 and probSum
				double randomNumber = r.nextDouble()*probSum;
				
				//now choose the city with the random number
				for (int j = 0; j < probability.length; j++) {
					randomNumber -= probability[j];
					if(randomNumber < 0){
						lastVisitedNode = j;
						visitedNodes.add(j);
						
						break;
					}
				}
			}
			//add first city as end city to the list
			visitedNodes.add(0);
			
			//create solution for this iteration
			Solution s = new Solution(visitedNodes, map.getDistances());
			System.out.println("Solution of the current iteration: ");
			System.out.println(s);
			
			if(s.getDistance() >= this.currentBestSolution.getDistance()){
				System.out.println("The solution of this iteration is not better than our current best solution");
			}else{
				System.out.println("We found a better solution");
				this.currentBestSolution = s;
				if(firstIterationWithBestResult == -1 && s.getDistance() == 2511){
					firstIterationWithBestResult = iterations+1;
				}
			}
			iterations++;
			// in the end update the pheromone values for the next iteration
			updatePheromoneValues();
			
		}
		
		this.time += System.currentTimeMillis() - startTime;
		
	}

	
	
	
}
