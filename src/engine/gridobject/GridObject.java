package engine.gridobject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import engine.Statistic;
import engine.collision.CollisionHandler;

public abstract class GridObject{

	private static final int WIDTH = 6;
	private static final int HEIGHT = 5;
	protected int myX;
	protected int myY;
	protected CollisionHandler myCollisionHandler;
	protected Image myImage;
	private Map<String,Statistic> myStatsMap;
	private boolean doesHarm = false;
	
	public GridObject(int x, int y, String image) {
		myX = x;
		myY = y;
		myStatsMap = null;
		myCollisionHandler = null;
		setImage(image);
	}
	
	public void setImage(String file) {
		Image img = scaleImage(10,10,file);
		myImage = img;
		
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(myImage, myX, myY, null);
	}
	
	public void setCollisionHandler(CollisionHandler handler) {
		myCollisionHandler = handler;
	}
	public void addStatistic(Statistic stat) {
		myStatsMap.put(stat.getName(), stat);
	}
	
	public void addStatistic(String name, int value, int maxValue){
		myStatsMap.put(name,new Statistic(name,value,maxValue));
	}
	
	public Map<String,Statistic> getStatsMap(){
		return myStatsMap;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(myX, myY-HEIGHT, WIDTH, HEIGHT);	
	}
	public boolean getDoesHarm(){
		return doesHarm;
	}
	public void setDoesHarm(boolean harm){
		doesHarm=harm;
	}
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
	
	public void move() {}; // default is to do nothing
	public void doCollision(GridObject o){};
	
}
