package engine.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Constants;
import engine.gridobject.person.Player;

public class TitleWorld extends World {

	public TitleScreen(int playWidth, int playHeight, Player p) {
		super(playWidth, playHeight, p);
		// TODO Auto-generated constructor stub
	}
	
	public void setBackground(String imageFile){
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("Constants.IMAGEPATH" + imageFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.drawImage(bi, 0, 0, null);
	}

}
