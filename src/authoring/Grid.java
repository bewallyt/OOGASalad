package authoring;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid extends Feature implements MouseListener, ActionListener{
	
	private static final int VIEW_WIDTH = 12;
	private static final int VIEW_HEIGHT = 12;
	private static final int WORLD_WIDTH = 144;
	private static final int WORLD_HEIGHT = 144;
	private JPanel grid;
	private TilePanel selectedCell;
	private JPopupMenu popup;
	private String[] popupMenuItems = {"Tile Editor", "NPC Editor", "Set as Player Start Point", "Clear Tile", "Tile Image"};
	private JList list;
	private Border defaultBorder;
	private Border selectBorder;
	
	//temporary lists to test features
	private String[] tileTypes = {"Wall", "Water", "TestTile", "NPC"};
	private String[] tileImageNames = {"Wall", "Water", "TestTile", "NPC", "Grey"};
	private TilePanel[][] world;
	
	public Grid() {
		grid = new JPanel(new GridBagLayout());
		worldMaker();		
		//grid.setLayout(new GridBagLayout());
		grid.setOpaque(false);
		popupMenuMaker();
		GridBagConstraints gbc = new GridBagConstraints();
		// Creates the grid of TilePanels
		defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
		selectBorder = new MatteBorder(2, 2, 2, 2, Color.BLUE);
		for (int row = 0; row < VIEW_HEIGHT; row++) {
			for (int col = 0; col < VIEW_WIDTH; col++) {
				gbc.gridx = col;
				gbc.gridy = row;
				TilePanel cell = new TilePanel(row, col);
				cell.setBorder(defaultBorder);
				cell.setMinimumSize(new Dimension(48, 48));
				cell.addMouseListener(this);
				grid.add(cell, gbc);
			}
		}
		myComponents.put(grid, BorderLayout.CENTER);
	}
	
	private void worldMaker(){
		int numRows = 0;
		int numCols = 0;
		
		//String width = (String) JOptionPane.showInputDialog(grid, "Please input a number");
		//grid.add(inputArea);
		
		world = new TilePanel[numRows][numCols];
		
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {				
				world[row][col] = new TilePanel(row, col);
			}
		}
	}
	private void popupMenuMaker(){
		popup = new JPopupMenu();
		for(int i = 0; i < popupMenuItems.length; i++){
			JMenuItem menuItem = new JMenuItem(popupMenuItems[i]);
			menuItem.addActionListener(this);
		    popup.add(menuItem);
		}
	}
	
	private void showPopupMenu(MouseEvent e){
		selectedCell = (TilePanel) e.getComponent();
		System.out.println(selectedCell.myRow + " " + selectedCell.myCol);
		popup.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public void showImageList(){
		list = new JList(tileTypes);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 800));
		
		String selectedTileImage= ListDialog.showDialog(
                grid,
                null,
                "Select an image for the tile: ",
                "Tile Creator",
                tileImageNames,
                null,
                null);
		
		String selectedTileData = ListDialog.showDialog(
                grid,
                null,
                "Select what goes on this tile: ",
                "Tile Creator",
                tileTypes,
                null,
                null);
		selectedCell.setTileImage(selectedTileImage);
		selectedCell.setTileDataImage(selectedTileData);
		grid.revalidate();
		grid.repaint();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		showImageList();
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton() == MouseEvent.BUTTON3)
			showPopupMenu(arg0);
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		TilePanel current = (TilePanel) arg0.getComponent();
		current.setBorder(selectBorder);
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		TilePanel current = (TilePanel) arg0.getComponent();
		current.setBorder(defaultBorder);
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//showPopupMenu(arg0);
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//showPopupMenu(arg0);
	}
}