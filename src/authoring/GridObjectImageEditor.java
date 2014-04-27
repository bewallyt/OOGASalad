package authoring;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Data.ImageFile;

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
	public class IconListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			ImageIcon image = model.get(list.getSelectedIndex());
			currentIcon=image;
			myPanel.addGridObjectImage(image);
			myPanel.revalidate();
		}
		
	}
	public ImageIcon getSelectedImage(){
		return currentIcon;
	}
	public void dispose(){
		myWindow.dispose();
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
