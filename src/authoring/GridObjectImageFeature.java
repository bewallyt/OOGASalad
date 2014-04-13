package authoring;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class GridObjectImageFeature extends Feature {

	private TilePanel myTilePanel;
	private Icon myImage;
	private GridObjectCreation mySuperFeature;
	private String myImageName;
	
	public GridObjectImageFeature(GridObjectCreation gridObjectCreation) {
		mySuperFeature = gridObjectCreation;
		Border defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
		myTilePanel = new TilePanel(1,1);
		myTilePanel.setBorder(defaultBorder);
		myComponents.put(myTilePanel, BorderLayout.SOUTH);
	}
	
	public String getImageName() {
		return myImageName;
	}
	
	public void setImage(Icon name){
		myTilePanel.addGridObjectImage(name);
		myImage = name;
		myTilePanel.revalidate();
	}
	
	public Icon getImage(){
		return myImage;
	}
}
