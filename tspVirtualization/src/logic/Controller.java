package logic;

import input.ReadInput;

import java.io.IOException;

import model.TspMap;
import algorithm.AntStrategy;
import algorithm.CityPerCity;
import algorithm.EdgePerEdge;
import tspVirtualization.MapPanel;

public class Controller{

	private MapPanel mapPanel;
	private AntStrategy currentStrategy;
	private CityPerCity cityStrategy;
	private EdgePerEdge edgeStrategy;
	private TspMap tspMap;
	private Execution strategyExecution;
	
	private boolean isStarted = false;
	
	
	public Controller() {
		
		ReadInput ri = new ReadInput("citydistances.csv");
		try {
			tspMap = ri.getTspMap();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out
					.println("Something with the input file is Wrong! (Array Index Out of Bounds");
			return;
		} catch (IOException e) {
			System.out.println("Input Output Exception");
			return;
		}
		
		cityStrategy = new CityPerCity(new TspMap(tspMap));
		edgeStrategy = new EdgePerEdge(new TspMap(tspMap));
		strategyExecution = new Execution(currentStrategy, 500, 0, this);
	}

	public void setMapPanel(MapPanel mapPanel){
		this.mapPanel = mapPanel;
	}
	
	public void selectCityStrategy(){
		currentStrategy = cityStrategy;
		updatePanel();
	}
	
	public void selectEdgeStrategy(){
		currentStrategy = edgeStrategy;
		updatePanel();
	}
	
	public void startStrategy(int speed, int stopCriteria){
		if(!isStarted && currentStrategy != null){
			isStarted = true;
			strategyExecution = new Execution(currentStrategy, 0, stopCriteria, this);
			strategyExecution.setSpeed(speed);
			strategyExecution.start();
		}else if(currentStrategy == null){
			stopCriteriaReached();
		}
	}
	
	public void stopStrategy(){
		if(isStarted){
			strategyExecution.stopExecution();
			isStarted = false;
		}
	}
	
	public void updateSpeed(int speed){
		strategyExecution.setSpeed(speed);
	}
	
	public void updatePanel(){
		if(currentStrategy.getCurrentBestSolution().getDistance() != Integer.MAX_VALUE){
			mapPanel.updatePanel(currentStrategy.getIterations(), currentStrategy.getCurrentBestSolution().getDistance(), currentStrategy.getCurrentBestSolution().getOrderedNodeList(), currentStrategy.getTspMap().getPheromones());
		}else{
			mapPanel.updatePanel(0, Integer.MAX_VALUE, null, currentStrategy.getTspMap().getPheromones());
		}
	}
	
	public void stopCriteriaReached(){
		isStarted = false;
		mapPanel.executionStopped();
	}
	
	public void updateStopCriteria(int stopCriteria){
		strategyExecution.updateStopCriteria(stopCriteria);
	}
	
	public void deleteCurrentStrategy(){
		if(isStarted){
			strategyExecution.stopExecution();
			stopCriteriaReached();
		}
		if(currentStrategy == edgeStrategy){
			edgeStrategy = new EdgePerEdge(new TspMap(tspMap));
			currentStrategy = edgeStrategy;
		}else{
			cityStrategy = new CityPerCity(new TspMap(tspMap));
			currentStrategy = cityStrategy;
		}
		updatePanel();
	}
	
}
