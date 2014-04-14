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
	private ImageIcon myTileImage;
	private ImageIcon myGridObjectImage;
	private JLabel myTileLabel;
	private JLabel myGridObjectLabel;
	private int myRow;
	private int myCol;

	public TilePanel(int row, int col){
		myData = new TileData(null);
		myRow = row;
		myCol = col;
		this.setLayout(new BorderLayout());
	}
	
	public TilePanel(int row, int col, TileData existingData){
		this(row, col);
		myData = existingData;
	}
	
	@Override
	public void setPreferredSize(Dimension size){
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(36, 36);
	}

	public void setTileImage(ImageIcon fileName) {	
		if(myTileLabel != null)
			this.remove(myTileLabel);
		
		myTileImage = fileName;
		myTileLabel = new JLabel(myTileImage);
		myTileLabel.setLayout(new BorderLayout());
		myTileLabel.setOpaque(true);
		this.add(myTileLabel);
		updateImage(myTileImage.getDescription());
	}
	
	public void addGridObjectImage(ImageIcon fileName){
		if(myGridObjectLabel != null)
			this.remove(myGridObjectLabel);
		
		myGridObjectImage = fileName;
		myGridObjectLabel = new JLabel(myGridObjectImage);
		myGridObjectLabel.setLayout(new BorderLayout());
		myGridObjectLabel.setOpaque(false);
		this.add(myGridObjectLabel);
	}
	
	public void updateImage(String s){
		myData.setImageName(s);
		FeatureManager.getWorldData().getMap(WorldData.DEFAULT_MAP).addTileData(this.myRow, this.myCol, this.myData);
	}

}