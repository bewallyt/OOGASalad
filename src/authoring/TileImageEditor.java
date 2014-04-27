package authoring;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.View;

import Data.ImageFile;
import Data.ImageManager;

public class TileImageEditor extends ImageEditor {
	
	public static final String DEFAULT_IMAGE_SAVE_EXTENSION=".jpg";
	public static final String IMAGE_TYPE="TileImage";

	public TileImageEditor() {
		super();
		myWindow = new JFrame("Tile Image Editor");
		myWindow.setLayout(new BorderLayout());
		myWindow.setBounds(900, 0, 360, 360);
		myWindow.getContentPane().add(scroll, BorderLayout.CENTER);

		myWindow.setVisible(true);
	}

	@Override
	public void addExistingImages() {
		List<ImageFile> imageList=m.getSavedImageMap();
		for(ImageFile image: imageList){
			if(image.getType().equalsIgnoreCase(IMAGE_TYPE)){
				addImage(image.getImage(), image.getName());
			}
		}
		
	}

}
