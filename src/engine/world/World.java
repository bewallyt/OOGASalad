package engine.world;

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
import engine.gridobject.GridObject;
import engine.gridobject.Player;

public class World extends JPanel{
	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileWidth;
	private int myTileHeight;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;
	

	// private BufferedImage backgroundImage;

	/**
	 * Instantiates a new canvas.
	 * 
	 * @param numTiles
	 *            the number of tiles
	 */
	public World(int numTileWidth, int numTileHeight, int tileWidth,
			int tileHeight) {
		myNumTileWidth = numTileWidth;
		myNumTileHeight = numTileHeight;
		myTileHeight = tileHeight;
		myTileWidth = tileWidth;
		myGridObjectList = new ArrayList<GridObject>();
		
		
	}

	/**
	 * Make empty matrix of tiles.
	 * 
	 * @return the tile matrix
	 */
	private Tile[][] makeTileMatrix() {
		System.out.println(myNumTileWidth + " " + myNumTileHeight);
		Tile[][] tileMatrix = new Tile[myNumTileWidth][myNumTileHeight];
		for (int i = 0; i < myNumTileWidth; i++) {
			for (int j = 0; j < myNumTileHeight; j++) {
				tileMatrix[i][j] = new Tile(myTileWidth, myTileHeight,i*myTileWidth,j*myTileHeight);
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

	// static?
	/**
	 * Dislays the canvas.
	 */
	public void initCanvas() {
		JFrame frame = new JFrame("SuPeR Heads");
		double height = myNumTileHeight * myTileHeight;
		double width = myNumTileWidth * myTileWidth;
		frame.add(this);
		frame.setSize((int) width, (int) height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.addKeyListener(new Control(this));
		this.setFocusable(true);
		
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int height = myNumTileHeight * myTileHeight;
		int width = myNumTileWidth * myTileWidth;

		g2d.drawImage(scaleImage(width, height, "background_earth.jpg"), 0, 0,
				null);
		
		for(GridObject go : myGridObjectList){
			go.paint(g2d);
		}
	}

	public static void main(String[] args) {
		World world = new World(40, 40, 20, 20);
		world.initCanvas();
		world.makeTileMatrix();
		System.out.println(world.myTileMatrix.length);
		world.setTileObject(new Player("grass.jpg", 2),10,10);
		while (true) {
			world.repaint();
			for(GridObject go : world.myGridObjectList){
				go.move();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
