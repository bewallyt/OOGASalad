package engine.world;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class World extends JFrame{
	private int myNumTileWidth;
	private int myNumTileHeight;
	private double myTileWidth;
	private double myTileHeight;
	private Tile[][] myTileMatrix;
	//private BufferedImage backgroundImage;

	/**
	 * Instantiates a new canvas.
	 * 
	 * @param numTiles
	 *            the number of tiles
	 */
	public World(int numTileWidth, int numTileHeight, double tileWidth,
			double tileHeight) {
		myNumTileWidth = numTileWidth;
		myNumTileHeight = numTileHeight;
		myTileHeight = tileHeight;
		myTileWidth = tileWidth;
	}

	/**
	 * Make empty matrix of tiles.
	 * 
	 * @return the tile matrix
	 */
	private Tile[][] makeTileMatrix() {
		Tile[][] tileMatrix = new Tile[myNumTileWidth][myNumTileHeight];
		for (int i = 0; i < myNumTileWidth; i++) {
			for (int j = 0; j < myNumTileHeight; i++) {
				tileMatrix[i][j] = new Tile(myTileWidth, myTileHeight);
			}
		}
		myTileMatrix = tileMatrix;
		return tileMatrix;
	}

	/**
	 * Gets the background image.
	 * 
	 * @param imageName
	 *            the image name
	 * @return the background image
	 */
//	private Image getBackgroundImage(String imageName) {
//
//	}

//	public void setTileSprite(Sprite sprite, int xTile, int yTile) {
//		myTileMatrix[xTile][yTile].setTileObject(sprite);
//	}

	/**
	 * Puts everything onto the content pane of a JFrame.
	 * Preparation for display.
	 */
	public void makeCanvas(){
		double height = myNumTileHeight * myTileHeight;
		double width = myNumTileWidth * myTileWidth;
		//Image background = getBackgroundImage();
		Image background;
		//this.getClass().getResource("black_bg.jpg")
		background = scaleImage((int)width,(int)height, "background_earth.jpg");
		BackgroundPanel bgPanel = new BackgroundPanel(background);
		bgPanel.setSize(new Dimension((int)width,(int)height));
		getContentPane().add(bgPanel);
		System.out.println(bgPanel.getPreferredSize());
		getContentPane().setSize(new Dimension((int)width,(int)height));
		System.out.println(getContentPane().getSize());
	}
	
	//static?
	/**
	 * Dislays the canvas.
	 */
	public void initCanvas(){
		setSize(getContentPane().getSize());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Canvas");
		pack();
		validate();
	}
	
	public static void main(String [ ] args)
	{
		World c = new World(20,20,20,20);
		c.makeCanvas();
		c.initCanvas();
		System.out.println(c.getSize());
	}
	
	/**
	 * Scales an image to the desired dimensions.
	 * 
	 * @param WIDTH
	 * @param HEIGHT
	 * @param filename
	 * @return
	 */
	public BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
	    BufferedImage bi = null;
	    try {
	        ImageIcon ii = new ImageIcon(filename);//path to image
	        bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = (Graphics2D) bi.createGraphics();
	        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	        g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return bi;
	}

	
}
