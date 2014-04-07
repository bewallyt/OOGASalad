package authoring;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GridObjectCoordinateFeature extends Feature {
	private JLabel coordinatesLabel;
	
	private JTextArea xTileCo;
	private JScrollPane xTileCoWrapper;
	
	private JTextArea yTileCo;
	private JScrollPane yTileCoWrapper;
	
	private GridObjectCreation mySuperFeature;
	public GridObjectCoordinateFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
		coordinatesLabel = new JLabel("Input X and Y coordinates of GridObject");
		myComponents.put(coordinatesLabel, BorderLayout.WEST);
		xTileCo = new JTextArea(1,3);
		xTileCoWrapper = new JScrollPane(xTileCo);
		myComponents.put(xTileCoWrapper, BorderLayout.WEST);
		yTileCo = new JTextArea(1,3);
		yTileCoWrapper = new JScrollPane(yTileCo);
		myComponents.put(yTileCoWrapper, BorderLayout.WEST);
	}
	public int getX() {
		try{
			return Integer.parseInt(xTileCo.getText());
		}
		catch (NumberFormatException e){
			return -1;
		}
	}
	public int getY() {
		try{
			return Integer.parseInt(yTileCo.getText());
		}
		catch (NumberFormatException e){
			return -1;
		}
	}
}
