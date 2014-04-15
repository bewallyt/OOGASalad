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

public class UploadedGridObjectImagesFeature extends Feature {

	private GridObjectCreation mySuperFeature;
	private JList list;
	private JScrollPane scroll;
	DefaultListModel model;
	
	public UploadedGridObjectImagesFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
		model = new DefaultListModel();
		
		ImageManager m=new ImageManager();
	
		/*BufferedImage temp;
		try {
			temp = ImageIO.read(new File("C:/Users/Richard Cao/Desktop/Spring2014/Tlaksdjf.png"));
		} catch (IOException e) {
			temp = null;
		}
		Image scaledImage = temp.getScaledInstance(36, 36, Image.SCALE_FAST);
		Icon x = new ImageIcon(scaledImage);
		model.addElement(x);*/

		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//list.addListSelectionListener(new SelectionListener());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new IconListener());
		scroll = new JScrollPane(list);
		myComponents.put(scroll, BorderLayout.NORTH);
	}
	
	public void addImage(File fileName){
		BufferedImage temp;
		try {
			temp = ImageIO.read(fileName);
		} catch (IOException e) {
			temp = null;
		}
		Image scaledImage = temp.getScaledInstance(36, 36, Image.SCALE_FAST);
		Icon x = new ImageIcon(scaledImage);
		model.addElement(x);
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
