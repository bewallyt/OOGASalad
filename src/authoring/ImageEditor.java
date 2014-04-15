package authoring;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Data.ImageManager;

public class ImageEditor {
	
	protected JList list;
	protected JScrollPane scroll;
	protected DefaultListModel model;
	ImageManager m=new ImageManager();
	
	public ImageEditor() {
		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		scroll = new JScrollPane(list);
		
		for(String image: m.getSavedImageList()){
			addImage(m.loadImage(image), image);
		}
	}

	public void addImage(File fileName, String s){
		BufferedImage temp;
		try {
			temp = ImageIO.read(fileName);
		} catch (IOException e) {
			temp = null;
		}
		Image scaledImage = temp.getScaledInstance(36, 36, Image.SCALE_FAST);
		ImageIcon x = new ImageIcon(scaledImage, s);
		model.addElement(x);
	}
	
	public ImageIcon selectImage(){
		if(list.getSelectedIndex()!=-1){
			return (ImageIcon) model.get(list.getSelectedIndex());
		}	
		return null;
	}
}
