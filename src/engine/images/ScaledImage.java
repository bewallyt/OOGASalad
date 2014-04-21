package engine.images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import util.Constants;

public class ScaledImage {
	private int myWidth;
	private int myHeight;
	private String myFile;
	public ScaledImage(int width, int height, String filename){
//		myWidth = width;
//		myHeight = height;
		myWidth = Constants.TILE_SIZE;
		myHeight = Constants.TILE_SIZE;
		myFile = filename;
	}
	
	/**
	 * Scale image to a specified size.
	 *
	 * @return the image
	 */
	public Image scaleImage() {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource(Constants.IMAGEPATH+myFile));
			bi = new BufferedImage(myWidth, myHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(
					RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, myWidth, myHeight, null);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return bi;

	}
}
