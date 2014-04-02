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

import engine.Control;
import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.Player;
import engine.gridobject.RuleFollower;

public abstract class World extends JPanel{
	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileSize;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;

	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public World(int tileSize) {
		System.out.println("width " + tileSize);
		myTileSize=tileSize;
		myGridObjectList = new ArrayList<GridObject>();
	}
	

	
	public void setDimensions(int width, int height){
		myNumTileWidth = width/myTileSize;
		myNumTileHeight = height/myTileSize;
		makeTileMatrix();
	}
	public int[] getTileSize(){
		return new int[] {myNumTileWidth, myNumTileHeight};
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



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		setOpaque(false);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		int height = myNumTileHeight * myTileSize;
		int width = myNumTileWidth * myTileSize;

		g2d.drawImage(scaleImage(width, height, "grass.jpg"), 0, 0,
				null);
		
		for(int i=0; i<myGridObjectList.size(); i++){
			myGridObjectList.get(i).paint(g2d);
		}
	}

	/**
	 * Scales an image to the desired dimensions.
	 * 
	 * @param WIDTH
	 * @param HEIGHT
	 * @param filename
	 * @return
	 */
	public Image scaleImage(int WIDTH, int HEIGHT, String filename) {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(filename));
			bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(
					RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;

	}

}
