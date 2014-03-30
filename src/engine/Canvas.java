package engine;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Canvas extends JFrame{

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
	public Canvas(int numTileWidth, int numTileHeight, double tileWidth,
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
		try {
			background = ImageIO.read(this.getClass().getResource("/black_bg.jpg"));
			BackgroundPanel bgPanel = new BackgroundPanel(background);
			bgPanel.setPreferredSize(new Dimension((int)width,(int)height));
			bgPanel.repaint();
			getContentPane().add(bgPanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//static?
	/**
	 * Dislays the canvas.
	 */
	public void initCanvas(){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Canvas");
		pack();
		validate();
	}
	
	public static void main(String [ ] args)
	{
		Canvas c = new Canvas(1,1,1,1);
		c.makeCanvas();
		c.initCanvas();
	}
	
}
