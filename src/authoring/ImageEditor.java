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
		
		addExistingImages();
	}
	
	private void addExistingImages(){
		for(Image image: m.getSavedImageMap().keySet()){
			addImage(image, m.getSavedImageMap().get(m));
		}
	}
	
	public void addImage(Image m, String s){	
		ImageIcon x = new ImageIcon(m, s);
		model.addElement(x);
	}
	
	public ImageIcon selectImage(){
		if(list.getSelectedIndex()!=-1){
			return (ImageIcon) model.get(list.getSelectedIndex());
		}	
		return null;
	}
}
