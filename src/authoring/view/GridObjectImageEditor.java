package authoring.view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import Data.ImageFile;

/**
 * ImageEditor which handles the use of GridObjectImages. Allows the user to easily select from
 * available gridObjectImages to use them for certain GridObjects
 * @author Davis Treybig, Richard Cao
 *
 */
public class GridObjectImageEditor extends ImageEditor {

	public static final String IMAGE_TYPE="GridObject";
	private TilePanel myPanel;
	private ImageIcon currentIcon;
	public GridObjectImageEditor(TilePanel panel){
		super();
		myPanel=panel;
		myWindow = new JFrame("GridObject Image Editor");
		myWindow.setLocationRelativeTo(null);
		myWindow.setLayout(new BorderLayout());
		myWindow.setBounds(0, 300, 360, 360);
		list.addListSelectionListener(new IconListener());
		myWindow.getContentPane().add(scroll, BorderLayout.CENTER);
		myWindow.setVisible(true);
	}
	private class IconListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			ImageIcon image = model.get(list.getSelectedIndex());
			currentIcon=image;
			myPanel.addGridObjectImage(image);
			myPanel.revalidate();
		}
		
	}
	/**
	 * @return Returns the currently selected image in the model
	 */
	public ImageIcon getSelectedImage(){
		return currentIcon;
	}
	/**
	 * Destroys the ImageEditor
	 */
	public void dispose(){
		myWindow.dispose();
	}
	/**
	 * Adds existing GridObjectImages to the model
	 */
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
