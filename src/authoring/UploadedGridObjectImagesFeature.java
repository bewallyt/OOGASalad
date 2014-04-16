package authoring;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Data.ImageManager;

public class UploadedGridObjectImagesFeature extends ImageEditor {

	private GridObjectCreation mySuperFeature;
	
	public UploadedGridObjectImagesFeature(GridObjectCreation gridObjectCreation){
		super();
		myWindow = new JFrame("GridObject Image Editor");
		myWindow.setLayout(new BorderLayout());
		mySuperFeature = gridObjectCreation;
		list.addListSelectionListener(new IconListener());
		myWindow.getContentPane().add(scroll, BorderLayout.CENTER);
	}
	
	public class IconListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			GridObjectImageFeature imageControl = (GridObjectImageFeature) (mySuperFeature.getFeature("GridObjectImageFeature"));
			ImageIcon image = (ImageIcon) model.get(list.getSelectedIndex());
			imageControl.setImage(image);
		}
		
	}
}
