package authoring;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class GridObjectImageFeature extends Feature {

	private TilePanel myTilePanel;
	private Icon myImage;
	private GridObjectCreation mySuperFeature;
	private String myImageName;
	
	public GridObjectImageFeature(GridObjectCreation gridObjectCreation) {
		mySuperFeature = gridObjectCreation;
		Border defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
		myTilePanel = new TilePanel(1,1);
		myTilePanel.setBorder(defaultBorder);
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
		myImageName = selectedTileImage;
		//myTilePanel.setTileImage(selectedTileImage);
		mySuperFeature.getView().getFrame().revalidate();
	}
	
	public String getImageName() {
		return myImageName;
	}
	
	public void setImage(Icon name){
		myTilePanel.addGridObjectImage(name);
		myImage = name;
		myTilePanel.revalidate();
	}
	
	public Icon getImage(){
		return myImage;
	}
}
