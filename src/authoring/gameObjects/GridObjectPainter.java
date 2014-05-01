package authoring.gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import authoring.features.FeatureManager;
import authoring.features.GridViewerFeature;
import authoring.view.Grid;
import authoring.view.TilePanel;


/**
 * Class that handles the painting of Grid Objects on the map after their creation. Splits images
 * as necessarry to handle objects that span multiple tiles
 * @author Davis Treybig
 *
 */
public class GridObjectPainter {

	public GridObjectPainter(int x, int y, int width, int height, ImageIcon image) {
		GridViewerFeature gridViewer=(GridViewerFeature)FeatureManager.getFeature("GridViewerFeature");
		Grid grid=gridViewer.getCurrentGrid();
		
		ImageIcon[] images=splitImage(height, width, image);
		int i=0;
		for(int r=x; r<(x+height); r++){
			for(int c=y; c<(y+width); c++){
				TilePanel panel=grid.getTilePanel(r, c);
				panel.addGridObjectImage(images[i]);
				panel.revalidate();
				i++;
			}
		}
	}
	/**
	 * Splits an existing image into a set of smaller images based on input dimensions
	 * @param width How many images wide to make the image
	 * @param height How many images high to make the image
	 * @param icon ImageIcon to be split
	 * @return ImageIcon[] of images to use
	 */
	private ImageIcon[] splitImage(int width, int height, ImageIcon icon){
		int rows = width;  
        int cols = height;  
        int chunks = rows * cols;  
        BufferedImage image=createBufferedImage(icon);
        
        int chunkWidth = image.getWidth() / cols;   
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks]; 
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {    
                imgs[count] = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());  
  
                
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        
       
        ImageIcon images[]=new ImageIcon[chunks];
        for(int i=0; i<chunks; i++){
        	images[i]=new ImageIcon(imgs[i]);
        }
        return images;
	}
	
	/**
	 * Creates a bufferedImage from an ImageIcon
	 * @param image ImageIcon to convert
	 */
	private BufferedImage createBufferedImage(ImageIcon image){
		BufferedImage bi = new BufferedImage(
			    image.getIconWidth(),
			    image.getIconHeight(),
			    BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			image.paintIcon(null, g, 0,0);
			g.dispose();
			return bi;
	}
}
