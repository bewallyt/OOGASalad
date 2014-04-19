package authoring;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridViewerFeature extends Feature{
	
	private WorldData wd;
	private JScrollPane myViewer;
	private JTabbedPane tabs;
	private List<Grid> myGrids;
	private String mapName;
	private int row;
	private int col;
	
	public GridViewerFeature() {
		wd = FeatureManager.getWorldData();
		myGrids = new ArrayList<Grid>();
		tabs = new JTabbedPane();
		tabs.addChangeListener(new TabbedPaneListener());
		myComponents.put(tabs, BorderLayout.CENTER);
		mapName = JOptionPane.showInputDialog("Name your map:");
		if(mapName.equals("")){
			JOptionPane.showMessageDialog(null, "Must name map. Please try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return;				
		}
		
		this.addMap(mapName);
	}
	
	public void tileRepaint(){
		for(Grid g : myGrids)
			g.tileRepaint();
	}
	
	public void addMap(String s){
		mapSize();		
		tabs.addTab(s, myViewer);
	}
	
	public void gridMaker(int height, int width){
		Grid g = new Grid(height, width);
		myGrids.add(g);
		myViewer = new JScrollPane(myGrids.get(myGrids.size() - 1));
		myViewer.setPreferredSize(new Dimension(592, 590));
	}
	
	public void mapSize(){
		JPanel mapSizer = new JPanel();
		JTextField rowEntry = new JTextField(5);
		JTextField colEntry = new JTextField(5);
		mapSizer.add(new JLabel("Rows:"));
		mapSizer.add(rowEntry);
		mapSizer.add(new JLabel("Columns:"));
		mapSizer.add(colEntry);

		int result = JOptionPane.showConfirmDialog(null, mapSizer, "Please enter the size of your map", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			row = Integer.parseInt(rowEntry.getText());
			col = Integer.parseInt(colEntry.getText());
		}
		MapData md = new MapData(row, col);
		wd.addLevel(mapName, md);
		wd.setCurrentMap(md, mapName);
		gridMaker(row, col);
	}
	
	public class TabbedPaneListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			wd.setCurrentMap(wd.getMap(tabs.getTitleAt(tabs.getSelectedIndex())), tabs.getTitleAt(tabs.getSelectedIndex()));
		}
		
	}
}
