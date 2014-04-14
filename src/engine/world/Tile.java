package engine.world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import engine.gridobject.GridObject;
import engine.images.ScaledImage;

public class Tile {


	private int mySize;
	private int myX;
	private int myY;
	private GridObject myObject;
	private Image myBackgroundImage;


	/**
	 * Instantiates a new tile with no specific GridObject or background
	 *
	 * @param width the width
	 * @param height the height
	 */
	public Tile(int size, int x, int y) {
		mySize = size;
		myX = x;
		myY=y;
		myBackgroundImage = null;
	}
	
	/**
	 * Instantiates a new tile with a string referencing an image to make as the tiles background image
	 * 
	 * @param size
	 * @param x the width
	 * @param y the height
	 * @param bgImage string referencing file location of background image
	 */
	public Tile(int size, int x, int y, String bgImage) {
		this(size, x, y);
		myBackgroundImage = new ScaledImage(x, y, bgImage).scaleImage();
	}
	
	/**
	 * Instantiates a new tile with an Image object
	 * 
	 * @param size
	 * @param x the width
	 * @param y the height
	 * @param bgImage Image object to be set as the background image 
	 */
	public Tile(int size, int x, int y, Image bgImage) {
		this(size, x, y);
		myBackgroundImage = bgImage;
	}
	
	/**
	 * Sets the background Image of a tile
	 * @param bgImage Image object to be set as the background image 
	 */
	
	public void setBackgroundImage(Image bgImage) {
		myBackgroundImage = bgImage;
	}
	
	/**
	 * Sets background Image of tile
	 * @param bgImage string referencing the file of the image
	 */
	public void setBackgroundImage(String bgImage) {
		myBackgroundImage = new ScaledImage(mySize, mySize, bgImage).scaleImage();
	}
	
	
	
	/**
	 * Sets the tile object.
	 *
	 * @param sprite the new tile object
	 */
	public void setTileObject(GridObject obj){
		myObject = obj;
		obj.setPosition(myX, myY);
		obj.setSize(mySize*obj.getNumTiles()[0], mySize*obj.getNumTiles()[1]);
		obj.setImage(obj.getImageFile());
		
	}
	
	public int[] getPosition(){
		return new int[] {myX,myY};
	}
	
	public void paint(Graphics2D g, int xOff, int yOff) {
		g.drawImage(myBackgroundImage, myX - (xOff), myY - (yOff) , null);
	}

	public Rectangle getBounds() {
		return new Rectangle(myX, myY, myY+(mySize), myX+(mySize));	
	}
	

}
