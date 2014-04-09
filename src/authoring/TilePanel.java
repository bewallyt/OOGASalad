package authoring;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel{
	
	private TileData myData;
	private Icon myTileImage;
	//private ImageIcon myTileDataImage;
	private JLabel myTileLabel;
	private JLabel myTileDataLabel;
	private int myRow;
	private int myCol;

	public TilePanel(int row, int col){
		myData = new TileData(null);
		myRow = row;
		myCol = col;
		this.setLayout(new BorderLayout());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(48, 48);
	}

	public void setTileImage(String fileName) {	
		if(myTileLabel != null)
			this.remove(myTileLabel);
		
		Image temp;
		try {
			temp = ImageIO.read(FeatureManager.getWorldData().getImage(fileName));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "File could not be loaded.", "Error Message", JOptionPane.ERROR_MESSAGE);
			temp = null;
		}
		myTileImage = new ImageIcon(temp);
		//new ImageIcon("C:/Users/Richard Cao/Desktop/Spring2014/" + fileName + ".jpg"); 
		myTileLabel = new JLabel(myTileImage);
		myTileLabel.setLayout(new BorderLayout());
		myTileLabel.setOpaque(false);
		this.add(myTileLabel);
		updateImage(fileName);
	}
	
	public void updateImage(String s){
		myData.setImageName(s);
	}
	
	public TileData getTileData(){
		return myData;
	}
	
	public int getRow(){
		return myRow;
	}
	
	public int getCol(){
		return myCol;
	}
	//The way I'm putting two images on one tile is that I'm adding the TileData image label to the
	//tile image label. Does this work when the game actually runs?
	/*public void setTileDataImage(String fileName){
		if(myTileDataLabel != null)
			this.remove(myTileDataLabel);
		myTileDataImage = new ImageIcon("C:/Users/Richard Cao/Desktop/Spring2014/" + fileName + ".jpg"); 
		myTileDataLabel = new JLabel(myTileDataImage);
		myTileLabel.add(myTileDataLabel);
	}*/

}