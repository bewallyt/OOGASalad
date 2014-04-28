package authoring.view;
import java.awt.Dimension;
import javax.swing.*;

import authoring.features.FeatureManager;
import authoring.gameObjects.GridObjectData;
import authoring.gameObjects.MapData;
import authoring.gameObjects.TileData;
import Data.ImageFile;
import Data.ImageManager;
import java.util.List;
import java.awt.*;

/**
 * 
 * Class that holds data contained within a grid in the authoring environment
 * @author Richard Cao
 *
 */
public class TilePanel extends JLayeredPane{

	private TileData myData;
	private ImageIcon myTileImage;
	private ImageIcon myGridObjectImage;
	private JLabel myTileLabel;
	private JLabel myGridObjectLabel;
	private int myRow;
	private int myCol;
	private Dimension myDimension;

	public TilePanel(int row, int col){
		myData = FeatureManager.getWorldData().getCurrentMap().getTileData(row, col);
		myRow = row;
		myCol = col;
		myDimension = new Dimension(36, 36);
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
		return myDimension;
	}

	/**
	 * Sets the image on the tile
	 * @param imageFile ImageIcon of the image to be used
	 */
	public void setTileImage(ImageIcon imageFile) {	
		if(myTileLabel != null)
			this.remove(myTileLabel);

		myTileImage = imageFile;
		myTileLabel = new JLabel(myTileImage);
		myTileLabel.setLayout(null);
		myTileLabel.setOpaque(false);
		this.add(myTileLabel, 1);
		saveImage(myTileImage.getDescription());
	}

	/**
	 * Adds a gridObjectImage to the tile. 
	 * @param imageFile ImageIcon to be placed for the GridObject
	 */
	public void addGridObjectImage(ImageIcon imageFile){
		myGridObjectImage = imageFile;
		myGridObjectLabel = new JLabel(myGridObjectImage);
		myGridObjectLabel.setLayout(new BorderLayout());
		myGridObjectLabel.setOpaque(false);
		this.add(myGridObjectLabel, 0);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Adds the images of the given GridObjects on this tile to the tile
	 */
	public void update(){
		List<GridObjectData> myGridObjects = myData.getGridObjectDatas();
		for(GridObjectData g : myGridObjects){
			if(g.getImageName() != null){
				ImageIcon i;
				ImageManager image=new ImageManager();
				ImageFile file=image.loadGridObjectImage(g.getImageName());
				i=new ImageIcon(file.getImage(), g.getImageName());
				addGridObjectImage(i);	
			}
		}

	}
	/**
	 * Saves the image currently stored on this Tile
	 * @param s Name of image
	 */
	public void saveImage(String s){
		myData.setImageName(s);
		MapData md = FeatureManager.getWorldData().getCurrentMap();
		md.addTileData(this.myRow, this.myCol, this.myData);
	}
	/**
	 * @return Returns the row of this tile
	 */
	public int getRow(){
		return myRow;
	}
	/**
	 * @return Returns the column of this tile. 
	 */
	public int getCol(){
		return myCol;
	}
}