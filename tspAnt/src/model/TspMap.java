package model;

import java.util.ArrayList;
import java.util.List;

public class TspMap {

	String [] cityNames;
	int [][] distances;
	double [][] pheromones;
	
	public TspMap(String [] cityNames, int[][] distances, double [][] pheromones){
		this.cityNames = cityNames;
		this.distances = distances;
		this.pheromones = pheromones;
	}
	
	public TspMap(TspMap tm){
		//no need to clone
		this.distances = tm.distances;
		this.cityNames = tm.cityNames;
		//need to clone
		this.pheromones = cloneArray(tm.pheromones);
		
	}
	
	private static double[][] cloneArray(double[][] src) {
	    int length = src.length;
	    double[][] target = new double[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	
	public double[][] getPheromones() {
		return pheromones;
	}

	public double[][] getPheromonesCopy(){
		return cloneArray(pheromones);
	}



	public void setPheromones(double[][] pheromones) {
		this.pheromones = pheromones;
	}




	public String[] getCityNames() {
		return cityNames;
	}




	public int[][] getDistances() {
		return distances;
	}


	public String getCityName(int node){
		if(node >= cityNames.length){
			return "No valid input node";
		}else{
			return cityNames[node];
		}
	}


	public String toStringDistances(){
		String s = "\t ";
		
		for (int i = 0; i < cityNames.length; i++) {
			
			if(cityNames[i].length() > 7){
				s += cityNames[i].substring(0, 7) +  "\t";
			}else{
				s += cityNames[i] + "\t";
			}
		}
				
		s += "\n";
		
		for (int i = 0; i < distances.length; i++) {
			if(cityNames[i].length() > 7){
				s += cityNames[i].substring(0, 7) +  "\t ";
			}else{
				s += cityNames[i] + "\t ";
			}
			for (int j = 0; j < distances[i].length; j++) {
				s += distances[i][j] + "\t";
			}
			s += "\n";
		}
		
		return s;
	}
	
	@Override
	public String toString() {
String s = "\t ";
		
		for (int i = 0; i < cityNames.length; i++) {
			
			if(cityNames[i].length() > 7){
				s += cityNames[i].substring(0, 7) +  "\t";
			}else{
				s += cityNames[i] + "\t";
			}
		}
				
		s += "\n";
		
		for (int i = 0; i < distances.length; i++) {
			if(cityNames[i].length() > 7){
				s += cityNames[i].substring(0, 7) +  "\t ";
			}else{
				s += cityNames[i] + "\t ";
			}
			for (int j = 0; j < pheromones[i].length; j++) {
				if((pheromones[i][j]+"").length() > 6){
					s += (pheromones[i][j]+"").substring(0,6) + "\t";
				}else{
					s += pheromones[i][j] + "\t";
				}
			}
			s += "\n";
		}
		
		return s;
	}
	
	
}
