package engine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.JPanel;

import engine.gridobject.Player;
import engine.world.World;



public class Main extends JPanel{
	Player p = new Player(40, 40, 5);
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		p.paint(g2d);
		System.out.println("hi");
		
	}
	public static void main(String[] args) throws InterruptedException {
		Main main = new Main();
		World c = new World(20,20,20,20);
		c.makeCanvas();
		c.initCanvas();
		main.repaint();
		System.out.println(c.getSize());
		Player p = new Player(40, 40, 5);
		p.setImage("sam4.jpg");
		
		while (true) {
			
			p.move();
			main.repaint();
			System.out.println("h");
			Thread.sleep(10);
		}
	}
}