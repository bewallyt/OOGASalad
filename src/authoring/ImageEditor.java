package authoring;

import java.awt.Image;
import javax.swing.*;
import Data.ImageManager;

public abstract class ImageEditor {
	
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
	
	public abstract void addExistingImages();
	
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
