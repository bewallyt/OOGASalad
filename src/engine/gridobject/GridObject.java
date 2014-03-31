package engine.gridobject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import engine.Statistic;

public abstract class GridObject{

	private static final int WIDTH = 6;
	private static final int HEIGHT = 5;
	protected int myX;
	protected int myY;
	protected CollisionHandler myCollisionHandler;
	protected Image myImage;
	private Map<String,Statistic> myStatsMap;
	
	public GridObject(int x, int y) {
		myX = x;
		myY = y;
		myStatsMap = null;
		myCollisionHandler = null;
	}
	
	public void setImage(String file) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		myImage = img;
		
	}
	
	public void paint(Graphics2D g) {
		System.out.println("paitned");
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
	
	public void move() {}; // default is to do nothing
	public void doCollision(GridObject o){};
	
}
