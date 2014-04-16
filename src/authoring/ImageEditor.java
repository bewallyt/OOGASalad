package authoring;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import Data.ImageManager;

public class ImageEditor {
	
	protected JList list;
	protected JScrollPane scroll;
	protected DefaultListModel model;
	protected JFrame myWindow;
	ImageManager m=new ImageManager();
	
	public ImageEditor() {
		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		scroll = new JScrollPane(list);
		this.addExistingImages();
	}
	
	public void addExistingImages(){
		Map<Image, String> imageMap = m.getSavedImageMap();
		for(Image image:imageMap.keySet()){
			//if(!(FeatureManager.getWorldData().getImages().containsValue(image)))
				//FeatureManager.getWorldData().saveImage(imageMap.get(image), m.loadImage(imageMap.get(image), ));
			addImage(image, imageMap.get(image));
		}
	}
	
	public void addImage(Image m, String s){	
		ImageIcon x = new ImageIcon(m, s);
		model.addElement(x);
	}
	
	public void imageRefresh(){
		model.clear();
		this.addExistingImages();
	}
	
	public ImageIcon selectImage(){
		if(list.getSelectedIndex()!=-1){
			return (ImageIcon) model.get(list.getSelectedIndex());
		}	
		return null;
	}
	
	public void setVisible(boolean input){
		myWindow.setVisible(input);
	}
}
