package engine.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.AbstractGameState;
import engine.WalkAroundState;
import engine.images.ScaledImage;

public class Canvas extends JPanel{
	
	private JFrame myFrame;
	private int myHeight;
	private int myWidth;
	private World myWorld;
	
	/**
	 * Instantiates a new canvas.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public Canvas(int width, int height){
		JFrame frame = new JFrame("Pokemon");
		myFrame = frame;
		myHeight=height;
		myWidth=width;
		frame.setSize((int) width, (int) height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.requestFocus();
	}
	

	public void setWorld(World world){
		myFrame.add(this);
		myFrame.addKeyListener(new WalkAroundState(this, world));
		myWorld = world;
	}
	
	public void setState(AbstractGameState state){
		myFrame.addKeyListener(state);
	}
	
	public int getHeight(){
		return myHeight;
	}
	
	public int getWidth(){
		return myWidth;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		setOpaque(false);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		int height = myWorld.getTileGridHeight() * myWorld.getTileSize();
		int width = myWorld.getTileGridWidth() * myWorld.getTileSize();
		
//		Image background = new ScaledImage(width, height, myWorld.getBackgroundString()).scaleImage();
//  		g2d.drawImage(background, 0, 0,null);
		
		// paints background of each tile
		for (int i = 0; i < myWorld.getTileGridWidth(); i++) {
			for (int j = 0; j < myWorld.getTileGridHeight(); j++) {
				myWorld.getTileMatrix()[i][j].paint(g2d);
			}
		}
		
		// paints objects on tiles
		for(int i=0; i<myWorld.getGridObjectList().size(); i++){
			myWorld.getGridObjectList().get(i).paint(g2d);
		}
	}
}
