package authoring;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 * 
 * @author Richard Cao
 *
 */
public class GridViewerFeature extends Feature{

	private WorldData wd;
	private Grid g;
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
		
		JPanel mapNamer = new JPanel();
		JTextField nameEntry = new JTextField(20);
		mapNamer.add(new JLabel("Name: "));
		mapNamer.add(nameEntry);
		String name = "";
		int result = JOptionPane.showConfirmDialog(null, mapNamer, "Name your map:", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			name = nameEntry.getText();
			//JOptionPane.showMessageDialog(null, "Must name map. Please try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
			//return;				
		}

		this.addMap(name);
	}

	public void tileRepaint(){
		for(Grid g : myGrids)
			g.tileRepaint();
	}

	public void addMap(String s){
		mapName = s;
		mapSize();		
		tabs.addTab(s, myViewer);
	}
	public Grid getGrid(){
		return g;
	}
	public void gridMaker(int height, int width){
		g= new Grid(height, width);
		myGrids.add(g);
		myViewer = new JScrollPane(myGrids.get(myGrids.size() - 1));
		myViewer.setPreferredSize(new Dimension(592, 590));
	}

	public void mapSize(){
		JPanel mapSizer = new JPanel();
		JTextField rowEntry = new JTextField(5);
		JTextField colEntry = new JTextField(5);
		mapSizer.add(new JLabel("Rows: (>15)"));
		mapSizer.add(rowEntry);
		mapSizer.add(new JLabel("Columns: (>15)"));
		mapSizer.add(colEntry);

		int result = JOptionPane.showConfirmDialog(null, mapSizer, "Please enter the size of your map", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			row = Integer.parseInt(rowEntry.getText());
			col = Integer.parseInt(colEntry.getText());
			
			if(row < 15 || col < 15){
				JOptionPane.showMessageDialog(null, "Number of rows or columns was not at least 15", "Map Sizing Error", JOptionPane.ERROR_MESSAGE);
				mapSize();
				return;
			}
		}
		MapData md = new MapData(row, col);
		wd.addLevel(mapName, md);
		wd.setCurrentMap(mapName);
		gridMaker(row, col);
	}

	public class TabbedPaneListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			wd.setCurrentMap(tabs.getTitleAt(tabs.getSelectedIndex()));
		}
	}

}
