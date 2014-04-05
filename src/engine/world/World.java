package engine.world;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.gridobject.person.RuleFollower;
import engine.images.ScaledImage;

public abstract class World {
	
	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileSize;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;
	private String myBackground;

	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public World(int tileSize, String background) {
		myTileSize=tileSize;
		myGridObjectList = new ArrayList<GridObject>();
		myBackground = background;
//		Image myBackground = new ScaledImage(width, height,myBackground).scaleImage();
	}
	
	public void setDimensions(int width, int height){
		myNumTileWidth = width/myTileSize;
		myNumTileHeight = height/myTileSize;
		makeTileMatrix();
	}
	
	public int getTileSize(){
		return myTileSize;
	}
	
	public int getTileGridHeight() {
		return myNumTileHeight;
	}
	
	public int getTileGridWidth() {
		return myNumTileWidth;
	}
	
	public String getBackgroundString() {
		return myBackground;

	}

	/**
	 * Make empty matrix of tiles.
	 * 
	 * @return the tile matrix
	 */
	public Tile[][] makeTileMatrix() {
		System.out.println(myNumTileWidth + " " + myNumTileHeight);
		Tile[][] tileMatrix = new Tile[myNumTileWidth][myNumTileHeight];
		for (int i = 0; i < myNumTileWidth; i++) {
			for (int j = 0; j < myNumTileHeight; j++) {
				tileMatrix[i][j] = new Tile(myTileSize,i*myTileSize,j*myTileSize);
			}
		}
		
		myTileMatrix = tileMatrix;
		
		return tileMatrix;
	}

	public List<GridObject> getGridObjectList(){
		return myGridObjectList;
	}
	
	public void setTileObject(GridObject obj, int xTile, int yTile) {
		myTileMatrix[xTile][yTile].setTileObject(obj);
		myGridObjectList.add(obj);
	}



//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D) g;
//		setOpaque(false);
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		
//		int height = myNumTileHeight * myTileSize;
//		int width = myNumTileWidth * myTileSize;
//		Image background = new ScaledImage(width, height,myBackground).scaleImage();
//		g2d.drawImage(background, 0, 0,null);
//		
//		for(int i=0; i<myGridObjectList.size(); i++){
//			myGridObjectList.get(i).paint(g2d);
//		}
//	}

	
	
	public void setViewSize(){
		
	}

}
