package tspVirtualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.Controller;

public class MapPanel extends JPanel {

	

	
	private BufferedImage nodeImage;
	private MapLabel lblMap;
	final private Controller controller;
	
	private JLabel lblIterations;
	private JLabel lblBestResult;

	private JButton btnStart;
	private JButton btnStop;
	
	private JRadioButton rdbtnCityPerCity;
	private JRadioButton rdbtnEdgePerEdge;
	

	private JTextField textField_distance;
	private JTextField textField_iterations;
	/**
	 * Create the this.
	 */
	public MapPanel(final Controller controller) {
		this.controller = controller;
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		
		try {
	          nodeImage = ImageIO.read(new File("resources\\node.png"));
		} catch (IOException ex) {
	            // handle exception...
	    }
		
		
		//map and nodes
		List<Point> nodeLocationList = new ArrayList<>();
		
		JLabel lblAachen_1 = new JLabel("Aachen");
		lblAachen_1.setBounds(60, 295, 80, 315);
		this.add(lblAachen_1);
		
		JLabel lblAachen = new JLabel(new ImageIcon(nodeImage));
		lblAachen.setBounds(72, 295, 92, 315);
		this.add(lblAachen);
		nodeLocationList.add(transformBoundsToCenteredPoint(lblAachen.getBounds()));
		
		JLabel label = new JLabel("Essen");
		label.setBounds(120, 220, 130, 240);
		this.add(label);
		
		JLabel labelEssen = new JLabel(new ImageIcon(nodeImage));
		labelEssen.setBounds(107, 235, 127, 255);
		this.add(labelEssen);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelEssen.getBounds()));
		
		JLabel label_1 = new JLabel("Hamburg");
		label_1.setBounds(270, 105, 237, 115);
		this.add(label_1);
		
		JLabel labelHamburg = new JLabel(new ImageIcon(nodeImage));
		labelHamburg.setBounds(217, 115, 237, 135);
		this.add(labelHamburg);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelHamburg.getBounds()));
		
		JLabel label_2 = new JLabel("Kiel");
		label_2.setBounds(339, 60, 230, 50);
		this.add(label_2);
		
		JLabel labelKiel = new JLabel(new ImageIcon(nodeImage));
		labelKiel.setBounds(220, 65, 240, 85);
		this.add(labelKiel);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelKiel.getBounds()));
		
		JLabel label_3 = new JLabel("Rostock");
		label_3.setBounds(413, 60, 470, 80);
		this.add(label_3);
		
		JLabel labelRostock = new JLabel(new ImageIcon(nodeImage));
		labelRostock.setBounds(290, 75, 310, 95);
		this.add(labelRostock);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelRostock.getBounds()));
		
		JLabel label_4 = new JLabel("Berlin");
		label_4.setBounds(530, 185, 450, 205);
		this.add(label_4);
		
		JLabel labelBerlin = new JLabel(new ImageIcon(nodeImage));
		labelBerlin.setBounds(330, 185, 350, 205);
		this.add(labelBerlin);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelBerlin.getBounds()));
		
		JLabel label_5 = new JLabel("Kassel");
		label_5.setBounds(280, 226, 320, 270);
		this.add(label_5);
		
		JLabel labelKassel = new JLabel(new ImageIcon(nodeImage));
		labelKassel.setBounds(210, 250, 230, 270);
		this.add(labelKassel);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelKassel.getBounds()));
		
		JLabel label_6 = new JLabel("Darmstadt");
		label_6.setBounds(290, 320, 220, 370);
		this.add(label_6);
		
		JLabel labelDarmstadt = new JLabel(new ImageIcon(nodeImage));
		labelDarmstadt.setBounds(160, 335, 180, 355);
		this.add(labelDarmstadt);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelDarmstadt.getBounds()));
		
		JLabel label_8 = new JLabel("Trier");
		label_8.setBounds(84, 340, 104, 350);
		this.add(label_8);
		
		JLabel labelTrier = new JLabel(new ImageIcon(nodeImage));
		labelTrier.setBounds(84, 340, 104, 350);
		this.add(labelTrier);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelTrier.getBounds()));
		
		JLabel label_7 = new JLabel("Muenchen");
		label_7.setBounds(440, 440, 500, 500);
		this.add(label_7);
		
		JLabel labelMuenchen = new JLabel(new ImageIcon(nodeImage));
		labelMuenchen.setBounds(290, 440, 310, 460);
		this.add(labelMuenchen);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelMuenchen.getBounds()));
		
		JLabel label_9 = new JLabel("Stuttgart");
		label_9.setBounds(210, 400, 220, 475);
		this.add(label_9);
		
		JLabel labelStuttgart = new JLabel(new ImageIcon(nodeImage));
		labelStuttgart.setBounds(180, 405, 200, 425);
		this.add(labelStuttgart);
		nodeLocationList.add(transformBoundsToCenteredPoint(labelStuttgart.getBounds()));
		
		lblMap = new MapLabel(nodeLocationList);
		lblMap.setText("map");
		lblMap.setBounds(0, 0, 687, 785);
		this.add(lblMap);
		
		
		//buttons etc
		btnStart = new JButton("Start");
		btnStart.setBounds(767, 340, 118, 55);
		add(btnStart);
		
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(895, 340, 118, 55);
		btnStop.setEnabled(false);
		add(btnStop);
		
		
		rdbtnCityPerCity = new JRadioButton("City by City");
		rdbtnCityPerCity.setBounds(767, 257, 109, 23);
		add(rdbtnCityPerCity);
		
		rdbtnEdgePerEdge = new JRadioButton("Edge by Edge");
		rdbtnEdgePerEdge.setBounds(767, 285, 109, 23);
		add(rdbtnEdgePerEdge);
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(767, 561, 118, 55);
		add(btnDelete);
		
		lblIterations = new JLabel("Iterations: ");
		lblIterations.setBounds(767, 536, 246, 14);
		add(lblIterations);
		
		lblBestResult = new JLabel("Best result: ");
		lblBestResult.setBounds(767, 513, 246, 14);
		add(lblBestResult);
		
		final JSlider slider = new JSlider();
		slider.setBounds(767, 216, 246, 23);
		slider.setMinimum(1);
		slider.setMaximum(1000);
		add(slider);
		
		final JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setBounds(767, 191, 246, 14);
		add(lblSpeed);
		
		JTextArea txtrStopCriteriaIn = new JTextArea();
		txtrStopCriteriaIn.setBackground(Color.WHITE);
		txtrStopCriteriaIn.setWrapStyleWord(true);
		txtrStopCriteriaIn.setLineWrap(true);
		txtrStopCriteriaIn.setEditable(false);
		txtrStopCriteriaIn.setEnabled(false);
		txtrStopCriteriaIn.setText("Stop criteria in km:\r\n(Stops if the value is less or equals to)");
		txtrStopCriteriaIn.setBounds(767, 406, 246, 65);
		add(txtrStopCriteriaIn);
		
		final JTextField textField_stopCriteria = new JTextField();
		textField_stopCriteria.setText("0");
		textField_stopCriteria.setBounds(767, 482, 86, 20);
		add(textField_stopCriteria);
		textField_stopCriteria.setColumns(10);
		
		textField_distance = new JTextField();
		textField_distance.setEditable(false);
		textField_distance.setText("0");
		textField_distance.setBounds(832, 510, 86, 20);
		add(textField_distance);
		textField_distance.setColumns(10);
		
		textField_iterations = new JTextField();
		textField_iterations.setEditable(false);
		textField_iterations.setText("0");
		textField_iterations.setBounds(832, 533, 86, 20);
		add(textField_iterations);
		textField_iterations.setColumns(10);
		
		
		
		//listeners
		
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnCityPerCity.setEnabled(true);
				rdbtnEdgePerEdge.setEnabled(true);
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				controller.stopStrategy();
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnCityPerCity.setEnabled(false);
				rdbtnEdgePerEdge.setEnabled(false);
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
				int stopCriteria = 0;
				try{
					stopCriteria = Integer.parseInt(textField_stopCriteria.getText());
				}catch(NumberFormatException nfe){
					System.out.println("Please enter an Integer");
				}
				controller.startStrategy(slider.getValue(), stopCriteria);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteCurrentStrategy();
			}
		});
		
		rdbtnCityPerCity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnEdgePerEdge.setSelected(false);
				rdbtnCityPerCity.setSelected(true);
				controller.selectCityStrategy();
			}
		});
		
		rdbtnEdgePerEdge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnCityPerCity.setSelected(false);
				rdbtnEdgePerEdge.setSelected(true);
				controller.selectEdgeStrategy();
				}
		});
		
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.updateSpeed(slider.getValue());
			}
		});
		
		textField_stopCriteria.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				try{
					controller.updateStopCriteria(Integer.parseInt(textField_stopCriteria.getText()));
				}catch(NumberFormatException nfe){
					System.out.println("Please enter an Integer");
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					controller.updateStopCriteria(Integer.parseInt(textField_stopCriteria.getText()));
				}catch(NumberFormatException nfe){
					System.out.println("Please enter an Integer");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private Point transformBoundsToCenteredPoint(Rectangle r){
		return new Point(r.x+(r.width/2), r.y+(r.height/2));	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
			return new Dimension(1100, 800);
	}
	
	public void executionStopped(){
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		rdbtnCityPerCity.setEnabled(true);
		rdbtnEdgePerEdge.setEnabled(true);
	}
	
	public void updatePanel(int iterations, int bestResult, List<Integer> path, double [][] pheromones){
		textField_distance.setText(bestResult+"");
		//lblIterations.setText("Iterations: " + iterations);
		//lblIterations.repaint();
		textField_iterations.setText(iterations+"");
		//lblBestResult.setText("Best result: " + bestResult);
		//lblBestResult.repaint();
		if(path != null){
			lblMap.setCurrentRoute(path, pheromones);
		}
	}
	
	
}
