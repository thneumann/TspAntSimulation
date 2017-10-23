package logic;

import algorithm.AntStrategy;

public class Execution extends Thread {

	private boolean stop = false;
	private AntStrategy strategy;
	private int currentSpeed; // in ants per second
	private Controller controller;
	private int stopCriteria;

	public Execution(AntStrategy strategy, int speed, int stopCriteria, Controller controller) {
		this.strategy = strategy;
		this.currentSpeed = speed;
		this.controller = controller;
		this.stopCriteria = stopCriteria;
	}

	@Override
	public void run() {
		super.run();
		while (!stop) {
			// do iteration
			strategy.doIterations(1);
			// update panel
			controller.updatePanel();
			if(strategy.getCurrentBestSolution().getDistance() <= stopCriteria){
				controller.stopCriteriaReached();
				break;
			}
			// sleep
			try {
				double convert = 1000./currentSpeed;
				double nanoConvert = convert - (int)convert;
				Thread.sleep((int) convert, (int) nanoConvert*1000000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stopExecution() {
		this.stop = true;
	}

	public void setSpeed(int speed) {
		this.currentSpeed = speed;
	}
	
	public void setStrategy(AntStrategy as){
		this.strategy = as;
	}

	public void updateStopCriteria(int stopCriteria){
		this.stopCriteria = stopCriteria;
	}
	
}
