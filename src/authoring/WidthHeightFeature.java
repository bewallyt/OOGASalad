package authoring;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
public class WidthHeightFeature extends Feature{
	private JLabel coordinatesLabel;
	
	private JTextArea width;
	private JScrollPane widthWrapper;
	
	private JTextArea height;
	private JScrollPane heightWrapper;
	
	private GridObjectCreation mySuperFeature;
	public WidthHeightFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
		coordinatesLabel = new JLabel("Input Length and Width (in tiles) of GridObject");
		myComponents.put(coordinatesLabel, BorderLayout.WEST);
		width = new JTextArea(1,3);
		widthWrapper = new JScrollPane(width);
		myComponents.put(widthWrapper, BorderLayout.CENTER);
		height = new JTextArea(1,3);
		heightWrapper = new JScrollPane(height);
		myComponents.put(heightWrapper, BorderLayout.CENTER);
	}
	public int getWidth() {
		try{
			return Integer.parseInt(width.getText());
		}
		catch (NumberFormatException e){
			return -1;
		}
	}
	public int getHeight() {
		try{
			return Integer.parseInt(height.getText());
		}
		catch (NumberFormatException e){
			return -1;
		}
	}
}
*/
