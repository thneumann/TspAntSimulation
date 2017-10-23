package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Solution;
import model.TspMap;

public class ReadInput {

	private String filePath;
	
	public ReadInput(String txtFilePath) {
		this.filePath = txtFilePath;
	}
	
	public TspMap getTspMap() throws IOException, ArrayIndexOutOfBoundsException{
		File f = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String line = br.readLine();
		String [] cityNames = line.split(",");
		int [][] distances = new int [cityNames.length][cityNames.length];
		double pheromones [][] = new double [cityNames.length][cityNames.length];
		
		line = br.readLine();
		int row = 0;
		while(line != null){
			String [] tmpDistanceLine = line.split(",");
			for (int i = 0; i < tmpDistanceLine.length; i++) {
				distances[row][i] = Integer.parseInt(tmpDistanceLine[i]);
				pheromones[row][i] = 1./cityNames.length;
				if(row == i){
					pheromones[row][i] = 0;
				}
			}
			row++;
			line = br.readLine();
		}
		
		Solution.setCityNames(cityNames);
		
		return new TspMap(cityNames, distances, pheromones);
	}
	
}
