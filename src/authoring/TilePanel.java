package authoring;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import Data.ImageFile;
import Data.ImageManager;

import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JLayeredPane{
	
	private TileData myData;
	private ImageIcon myTileImage;
	private ImageIcon myGridObjectImage;
	private JLabel myTileLabel;
	private JLabel myGridObjectLabel;
	private int myRow;
	private int myCol;

	public TilePanel(int row, int col){
		myData = FeatureManager.getWorldData().getMap(WorldData.DEFAULT_MAP).getTileData(row, col);
		myRow = row;
		myCol = col;
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	public TilePanel(int row, int col, ImageIcon bg){
		this(row, col);
		this.setTileImage(bg);
	}
	
	@Override
	public void setPreferredSize(Dimension size){
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(36, 36);
	}

	public void setTileImage(ImageIcon imageFile) {	
		if(myTileLabel != null)
			this.remove(myTileLabel);
		
		myTileImage = imageFile;
		myTileLabel = new JLabel(myTileImage);
		myTileLabel.setLayout(null);
		myTileLabel.setOpaque(true);
		this.add(myTileLabel, 1);
		saveImage(myTileImage.getDescription());
	}
	
	public void addGridObjectImage(ImageIcon imageFile){
		myGridObjectImage = imageFile;
		myGridObjectLabel = new JLabel(myGridObjectImage);
		myGridObjectLabel.setLayout(new BorderLayout());
		myGridObjectLabel.setOpaque(false);
		this.add(myGridObjectLabel, 0);
		this.revalidate();
		this.repaint();
	}
	
	public void update(){
		List<GridObjectData> myGridObjects = myData.getGridObjectDatas();
		for(GridObjectData g : myGridObjects){
			if(g.getImageName() != null){
				ImageIcon i;
				ImageManager image=new ImageManager();
				ImageFile file=image.loadGridObjectImage(g.getImageName());
				i=new ImageIcon(file.getImage(), g.getImageName());
				this.addGridObjectImage(i);	
			}
		}
		
	}
	public void saveImage(String s){
		myData.setImageName(s);
		FeatureManager.getWorldData().getMap(WorldData.DEFAULT_MAP).addTileData(this.myRow, this.myCol, this.myData);
		
	}

}