package authoring;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridViewerFeature extends Feature{
	
	private JScrollPane myViewer;
	private Grid myGrid;
	
	public GridViewerFeature() {
		myGrid = new Grid();
		myViewer = new JScrollPane(myGrid);
		myViewer.setPreferredSize(new Dimension(592, 590));
		myComponents.put(myViewer, BorderLayout.CENTER);
		//FeatureManager.imageEditor.setLocation(myGrid.getX() + 650, myGrid.getY());
		FeatureManager.imageEditor.setVisible(true);
	}
	
	public void tileRepaint(){
		myGrid.tileRepaint();
	}
}
