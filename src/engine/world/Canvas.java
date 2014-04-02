package engine.world;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Control;

public class Canvas extends JPanel{
	private JFrame myFrame;
	private int myHeight;
	private int myWidth;
	
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
		this.setFocusable(true);
	}
	
	public void setWorld(World world){
		myFrame.add(world);
		myFrame.addKeyListener(new Control(world));
	}
	
	public int getHeight(){
		return myHeight;
	}
	
	public int getWidth(){
		return myWidth;
	}
}
