package authoring;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel{
	
	private TileData myData;
	private Icon myTileImage;
	private JLabel myTileLabel;
	private int myRow;
	private int myCol;

	public TilePanel(){
		this.setLayout(new BorderLayout());
		this.setBorder(new MatteBorder(1, 1, 1, 1, Color.GREEN));
	}
	
	public TilePanel(int row, int col){
		myData = new TileData(null);
		myRow = row;
		myCol = col;
		this.setLayout(new BorderLayout());
	}
	
	@Override
	public void setPreferredSize(Dimension size){
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(36, 36);
	}

	public void setTileImage(String fileName) {	
		if(myTileLabel != null)
			this.remove(myTileLabel);
		
		BufferedImage temp;
		try {
			temp = ImageIO.read(FeatureManager.getWorldData().getImage(fileName));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "File could not be loaded.", "Error Message", JOptionPane.ERROR_MESSAGE);
			temp = null;
		}
        Image scaledImage = temp.getScaledInstance(36, 36, Image.SCALE_FAST);
		myTileImage = new ImageIcon(scaledImage);
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

}