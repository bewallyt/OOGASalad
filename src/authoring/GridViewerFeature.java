package authoring;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridViewerFeature extends Feature{
	
	private JScrollPane myViewer;
	private Grid myGrid;
	
	public GridViewerFeature() {
		TileImageEditor x = new TileImageEditor();
		myGrid = new Grid();
		myViewer = new JScrollPane(myGrid);
		myViewer.setPreferredSize(new Dimension(592, 590));
		myComponents.put(myViewer, BorderLayout.CENTER);
	}
}
