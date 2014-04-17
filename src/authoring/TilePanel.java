package authoring;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.util.List;
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
		myData = FeatureManager.getWorldData().getMap(WorldData.DEFAULT_MAP).getTileData(row, col);
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

	public void setTileImage(ImageIcon imageFile) {	
		if(myTileLabel != null)
			this.remove(myTileLabel);
		
		myTileImage = imageFile;
		myTileLabel = new JLabel(myTileImage);
		myTileLabel.setLayout(new BorderLayout());
		myTileLabel.setOpaque(true);
		this.add(myTileLabel);
		saveImage(myTileImage.getDescription());
	}
	
	public void addGridObjectImage(ImageIcon imageFile){
		if(myGridObjectLabel != null)
			this.remove(myGridObjectLabel);
		
		myGridObjectImage = imageFile;
		myGridObjectLabel = new JLabel(myGridObjectImage);
		myGridObjectLabel.setLayout(new BorderLayout());
		myGridObjectLabel.setOpaque(false);
		this.add(myGridObjectLabel);
	}
	
	public void update(){
		List<GridObjectData> myGridObjects = myData.getGridObjectDatas();
		for(GridObjectData g : myGridObjects){
			if(g.getImageName() != null){
				BufferedImage temp;
				try {
					System.out.println(FeatureManager.myWorld.getImage(g.getImageName()));
					temp = ImageIO.read(FeatureManager.myWorld.getImage(g.getImageName()));
					Image scaledImage = temp.getScaledInstance(36, 36, Image.SCALE_FAST);
					ImageIcon i = new ImageIcon(scaledImage);
					this.addGridObjectImage(i);
				} catch (IOException e) {
					
				}
			}
		}
	}
	public void saveImage(String s){
		myData.setImageName(s);
		FeatureManager.getWorldData().getMap(WorldData.DEFAULT_MAP).addTileData(this.myRow, this.myCol, this.myData);
	}

}