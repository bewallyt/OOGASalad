package engine.world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import util.Constants;
import engine.gridobject.person.Player;

public class TitleWorld extends World {
	
	private int myWidth = 1000;
	private int myHeight = 1000;
	private int myX = 0;
	private int myY = 0;
	

	public TitleWorld(int playWidth, int playHeight, Player p) {
		super(playWidth, playHeight, p);
	}
	
	public void scaleImage(String imageFile, int xOff, int yOff) {
		BufferedImage bi = null;
		Graphics2D g2d = null;
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource(Constants.IMAGEPATH+imageFile));
//			ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource(imageFile));
			bi = new BufferedImage(myWidth, myHeight, BufferedImage.TYPE_INT_ARGB);
			g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(
					RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, myWidth, myHeight, null);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		g2d.drawImage(bi, myX - (xOff), myY - (yOff) , null);

	}

}
