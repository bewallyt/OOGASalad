package authoring;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridViewerFeature extends Feature{
	
	private JScrollPane myViewer;
	private JTabbedPane tabs;
	private List<Grid> myGrids;
	
	public GridViewerFeature() {
		myGrids = new ArrayList<Grid>();
		gridMaker();
		tabs = new JTabbedPane();
		tabs.addTab("Default Map", myViewer);
		myComponents.put(tabs, BorderLayout.CENTER);
		
		//FeatureManager.imageEditor.setLocation(myGrid.getX() + 650, myGrid.getY());
		//FeatureManager.imageEditor.setVisible(true);
	}
	
	public void tileRepaint(){
		for(Grid g : myGrids)
			g.tileRepaint();
	}
	
	public void addMap(String s){
		gridMaker();
		tabs.addTab(s, myViewer);
		
		
	}
	
	public void gridMaker(){
		Grid g = new Grid();
		myGrids.add(g);
		myViewer = new JScrollPane(myGrids.get(myGrids.size() - 1));
		myViewer.setPreferredSize(new Dimension(592, 590));
	}
}
