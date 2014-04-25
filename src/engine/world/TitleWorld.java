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
	
	int myWidth;
	int myHeight;
	int myX;
	int myY;

	public TitleWorld(int playWidth, int playHeight, Player p) {
		super(playWidth, playHeight, p);
		myWidth = 1000;
		myHeight = 1000;
		myX = 0;
		myY = 0;
	}
	
	public void setBackground(String imageFile){
		BufferedImage bi = null;
		Graphics2D g2d = null;
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource(Constants.IMAGEPATH+imageFile));
			bi = new BufferedImage(myWidth, myHeight, BufferedImage.TYPE_INT_ARGB);
			g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(
					RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, myWidth, myHeight, null);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		g2d.drawImage(bi, myX, myY, null);

	}
	

}
