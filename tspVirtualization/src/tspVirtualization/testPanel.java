package tspVirtualization;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;

public class testPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public testPanel() {
		setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(767, 340, 118, 55);
		add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(895, 340, 118, 55);
		add(btnStop);
		
		JRadioButton rdbtnCityPerCity = new JRadioButton("City per City");
		rdbtnCityPerCity.setBounds(767, 257, 109, 23);
		add(rdbtnCityPerCity);
		
		JRadioButton rdbtnEdgePerEdge = new JRadioButton("Edge per Edge");
		rdbtnEdgePerEdge.setBounds(767, 285, 109, 23);
		add(rdbtnEdgePerEdge);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBounds(767, 561, 118, 55);
		add(btnNewButton);
		
		JLabel lblIterations = new JLabel("Iterations: ");
		lblIterations.setBounds(767, 536, 246, 14);
		add(lblIterations);
		
		JLabel lblBestResult = new JLabel("Best result:");
		lblBestResult.setBounds(767, 513, 246, 14);
		add(lblBestResult);
		
		JSlider slider = new JSlider();
		slider.setBounds(767, 216, 246, 23);
		add(slider);
		
		JLabel lblSpeed = new JLabel("Speed (ants per minutes):");
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
		
		textField = new JTextField();
		textField.setText("0");
		textField.setBounds(767, 482, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("0");
		textField_1.setBounds(832, 510, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText("0");
		textField_2.setBounds(832, 533, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);

	}
}
