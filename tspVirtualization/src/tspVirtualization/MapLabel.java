package tspVirtualization;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MapLabel extends JLabel{

	private BufferedImage germanyImage;
	private List<Point> nodeLocationList;
	private List<Integer> currentRoute;
	private double [][] pheromones;
	
	public MapLabel(List<Point> nodeLocationList) {
		this.nodeLocationList = nodeLocationList;
		try {
	          germanyImage = ImageIO.read(new File("resources\\deutschland_umriss_ohne_punkte.JPG"));
	          this.setIcon(new ImageIcon(germanyImage));
		} catch (IOException ex) {
	            // handle exception...
	    }
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		if(currentRoute == null){
			return;
		}
		
		double minValue = 1./(pheromones.length*pheromones.length);
		g2d.setColor(Color.GRAY);		
		for (int i = 0; i < pheromones.length; i++) {
			for (int j = 0; j < pheromones[i].length; j++) {
				Point pointFrom = nodeLocationList.get(i);
				Point pointTo = nodeLocationList.get(j);
				
				g2d.setStroke(new BasicStroke((float) pheromones[i][j]*10f));
				if(pheromones[i][j] == minValue){
					g2d.setStroke(new BasicStroke((float) pheromones[i][j]/1000f));
				}
				g2d.drawLine(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y);
			}
		}
		
		
		int from = 0; //is set at the beginning of the loop
		int to = currentRoute.get(0); 
		
		g2d.setColor(Color.RED);
		for (int i = 1; i < currentRoute.size(); i++) {
			from = to;
			to = currentRoute.get(i);
			
			Point pointFrom = nodeLocationList.get(from);
			Point pointTo = nodeLocationList.get(to);
			
			g2d.drawLine(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y);			
		}
		
		
		
	}

	public void setCurrentRoute(List<Integer> currentRoute, double [][] pheromones) {
		this.currentRoute = currentRoute;
		this.pheromones = pheromones;
		repaint();
	}
	
}
