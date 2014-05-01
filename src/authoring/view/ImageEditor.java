package authoring.view;

import java.awt.Image;
import javax.swing.*;
import Data.ImageManager;

/**
 * Abstract class used to create image editors for Tiles and GridObjects. Allows one 
 * to easily add multiple images in a grid format to a JFrame so that those images may be clicked
 * and used. 
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public abstract class ImageEditor {
	
	protected JList<ImageIcon> list;
	protected JScrollPane scroll;
	protected DefaultListModel<ImageIcon> model;
	protected JFrame myWindow;
	protected ImageManager m=new ImageManager();
	
	public ImageEditor() {
		model = new DefaultListModel<ImageIcon>();
		list = new JList<ImageIcon>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		scroll = new JScrollPane(list);
		this.addExistingImages();
	}
	
	public abstract void addExistingImages();
	
	/**
	 * Adds an image to the ImageEditor model
	 * @param m Image to be added
	 * @param s Description of the image to be added
	 */
	public void addImage(Image m, String s){	
		ImageIcon x = new ImageIcon(m, s);
		model.addElement(x);
	}
	
	/**
	 * Selects the image being clicked
	 * @return ImageIcon corresponding to the clicked image
	 */
	public ImageIcon selectImage(){
		if(list.getSelectedIndex()!=-1){
			return model.get(list.getSelectedIndex());
		}	
		return null;
	}
	
	/**
	 * Sets the ImageEditor's visibility
	 * @param input Boolean value to determine whether the ImageEditor is visible or not
	 */
	public void setVisibility(boolean input){
		myWindow.setVisible(input);
	}
}
