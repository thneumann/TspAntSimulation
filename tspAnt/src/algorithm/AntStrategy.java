package algorithm;

import java.util.ArrayList;
import java.util.List;

import model.Solution;
import model.TspMap;

public abstract class AntStrategy {

	private final double evaporationFactor = 0.02;
	private final double pheromoneMin;
	private final double pheromoneMax;
	
	protected TspMap map;
	protected Solution currentBestSolution;
	protected int iterations = 0;
	protected int firstIterationWithBestResult = -1;
	protected long time;
	
	protected AntStrategy(TspMap map){
		this.map = map;
		pheromoneMin = 1./(map.getCityNames().length*map.getCityNames().length);
		pheromoneMax = 1-(1./map.getCityNames().length);
		List<Integer> emptyList = new ArrayList<Integer>();
		this.currentBestSolution = new Solution(emptyList, map.getDistances());
	}
	
	public abstract void doIterations(int n);
	
	
	protected void updatePheromoneValues(){
		List<Integer> solution = currentBestSolution.getOrderedNodeList();
		//array is given by reference, so just change values directly
		double [][] pheromones = map.getPheromones();
		for (int i = 0; i < pheromones.length; i++) {
			for (int j = i+1; j < pheromones[i].length; j++) {
				if(nodesAreNeighbours(solution, i, j)){
					double newPheromoneValue = ((1.-evaporationFactor)*pheromones[i][j])+evaporationFactor;
					if(newPheromoneValue > pheromoneMax){
						pheromones[i][j] = pheromoneMax;
						pheromones[j][i] = pheromoneMax;
					}else{
						pheromones[i][j] = newPheromoneValue;
						pheromones[j][i] = newPheromoneValue;
					}
				}else{
					double newPheromoneValue = (1.-evaporationFactor)*pheromones[i][j];
					if(newPheromoneValue < pheromoneMin){
						pheromones[i][j] = pheromoneMin;
						pheromones[j][i] = pheromoneMin;
					}else{
						pheromones[i][j] = newPheromoneValue;
						pheromones[j][i] = newPheromoneValue;
					}
				}
			}
		}
	}
	
	private boolean nodesAreNeighbours(List<Integer> solution, int a, int b){
		
		for (int i = 0; i < solution.size()-1; i++) {
			if(solution.get(i) == a && solution.get(i+1) == b){
				return true;
			}
			if(solution.get(i) == b && solution.get(i+1) == a){
				return true;
			}
		}
		
		return false;
	}
	
	public Solution getCurrentBestSolution(){
		return this.currentBestSolution;
	}
	
	public TspMap getTspMap(){
		return this.map;
	}
	
	public int getIterations(){
		return iterations;
	}

	public int getFirstIterationWithBestResult() {
		return firstIterationWithBestResult;
	}

	public long getTime() {
		return time;
	}
	
	
	
}
