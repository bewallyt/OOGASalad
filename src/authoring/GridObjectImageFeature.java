package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GridObjectImageFeature extends Feature {

	private JButton addImageButton;
	private TilePanel myTilePanel;
	private GridObjectCreation mySuperFeature;
	public GridObjectImageFeature(GridObjectCreation gridObjectCreation) {
		mySuperFeature = gridObjectCreation;
		myTilePanel = new TilePanel(1,1);
		addImageButton = new JButton("Add GridObject Image");
		addImageButton.addActionListener(new GridImageListener());
		myComponents.put(addImageButton, BorderLayout.SOUTH);
		myComponents.put(myTilePanel, BorderLayout.SOUTH);
	}
	
	private class GridImageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			showImageList();
		}
	}
	public void showImageList(){
		WorldData wd = FeatureManager.getWorldData(); 
		Object[] imageNames = wd.getImages().keySet().toArray();
		if(imageNames.length == 0){
			JOptionPane.showMessageDialog(mySuperFeature.getView().getFrame(), "Please upload an image first.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String selectedTileImage = (String) JOptionPane.showInputDialog(
				mySuperFeature.getView().getFrame(),
                "Select an image for the tile: ",
                "Tile Image Editor",
                JOptionPane.QUESTION_MESSAGE,
                null, imageNames,
                "");
		if(selectedTileImage == null){
			return;
		}	
		myTilePanel.setTileImage(selectedTileImage);
	}
}
