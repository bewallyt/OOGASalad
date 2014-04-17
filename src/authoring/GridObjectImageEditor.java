package authoring;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Data.ImageFile;
import Data.ImageManager;

public class GridObjectImageEditor extends ImageEditor {

	private GridObjectCreation mySuperFeature;
	public static final String IMAGE_TYPE="GridObject";
	
	public GridObjectImageEditor(){
		super();
		myWindow = new JFrame("GridObject Image Editor");
		myWindow.setLayout(new BorderLayout());
		myWindow.setBounds(0, 300, 360, 360);
		list.addListSelectionListener(new IconListener());
		myWindow.getContentPane().add(scroll, BorderLayout.CENTER);
	}
	public void setSuperFeature(GridObjectCreation grid){
		mySuperFeature=grid;
	}
	public class IconListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			GridObjectImageFeature imageControl = (GridObjectImageFeature) (mySuperFeature.getFeature("GridObjectImageFeature"));
			ImageIcon image = (ImageIcon) model.get(list.getSelectedIndex());
			imageControl.setImage(image);
		}
		
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
