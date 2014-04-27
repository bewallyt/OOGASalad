package authoring;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Class that handles the painting of Grid Objects on the map after their creation. Splits images
 * as necesarry to handle objects that span multiple tiles
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
		//TilePanel panel=grid.getTilePanel(x, y);
		//panel.addGridObjectImage(image);
		//panel.revalidate();
	}
	private ImageIcon[] splitImage(int width, int height, ImageIcon icon){
		int rows = width; //You should decide the values for rows and cols variables  
        int cols = height;  
        int chunks = rows * cols;  
        BufferedImage image=createBufferedImage(icon);
        
        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks  
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //Initialize the image array with image chunks  
                imgs[count] = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());  
  
                // draws the image chunk  
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        
       // System.out.println(imgs[0].getHeight());
        ImageIcon images[]=new ImageIcon[chunks];
        for(int i=0; i<chunks; i++){
        	images[i]=new ImageIcon(imgs[i]);
        }
        return images;
	}
	
	private BufferedImage createBufferedImage(ImageIcon image){
		BufferedImage bi = new BufferedImage(
			    image.getIconWidth(),
			    image.getIconHeight(),
			    BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			// paint the Icon to the BufferedImage.
			image.paintIcon(null, g, 0,0);
			g.dispose();
			return bi;
	}
}
