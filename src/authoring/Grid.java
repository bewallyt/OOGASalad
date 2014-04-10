package authoring;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid extends JPanel implements ActionListener{

	private static final int WORLD_WIDTH = 144;
	private static final int WORLD_HEIGHT = 144;
	private int startRow = 0;
	private int startCol = 0;
	private WorldData wd;
	private TilePanel selectedCell;
	private JPopupMenu popup;
	private String[] popupMenuItems = { "Tile Image Editor", "Set as Player Start Point", "Clear Tile"};
	private JList list;
	private Border defaultBorder;
	private Border selectBorder;

	//temporary lists to test features
	private String[] tileTypes = {"Wall", "Water", "TestTile", "NPC"};
	private String[] tileImageNames;
	private TilePanel[][] world;

	public Grid() {
		this.setLayout(new GridBagLayout());
		worldMaker();		
		this.setOpaque(false);
		popupMenuMaker();
		// Creates the grid of TilePanels
		defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
		selectBorder = new MatteBorder(2, 2, 2, 2, Color.BLUE);
		drawGrid();
	}

	private void worldMaker(){
		//String width = (String) JOptionPane.showInputDialog(grid, "Please input a number");
		//grid.add(inputArea);

		world = new TilePanel[WORLD_HEIGHT][WORLD_WIDTH];

		for (int row = 0; row < WORLD_HEIGHT; row++) {
			for (int col = 0; col < WORLD_WIDTH; col++) {				
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
		popup.show(e.getComponent(), e.getX(), e.getY());
	}

	public void showImageMenu(){
		WorldData wd = FeatureManager.getWorldData(); 
		Object[] imageNames = wd.getImages().keySet().toArray();
		if(imageNames.length == 0){
			JOptionPane.showMessageDialog(this, "Please upload an image first.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String selectedTileImage = (String) JOptionPane.showInputDialog(
				this,
				"Select an image for the tile: ",
				"Tile Image Editor",
				JOptionPane.QUESTION_MESSAGE,
				null, imageNames,
				"");

		/*String selectedTileData = (String) JOptionPane.showInputDialog(
                grid,
                "Select an image for the tile: ",
                "Tile Image Editor",
                JOptionPane.QUESTION_MESSAGE,
                null, tileTypes,
                tileTypes[0]);		
		//selectedCell.setTileDataImage(selectedTileData);*/
		if(selectedTileImage == null){
			return;
		}	
		selectedCell.setTileImage(selectedTileImage);
		wd.getMap(WorldData.DEFAULT_MAP).addTileData(selectedCell.getRow(), selectedCell.getCol(), selectedCell.getTileData());
		this.revalidate();
		this.repaint();
	}

	//need to fix error when user scrolls too far right or too far down
	public void drawGrid(){
		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = startRow; row < 144; row++) {
			for (int col = startCol; col < 144; col++) {
				gbc.gridx = col;
				gbc.gridy = row;
				TilePanel cell = world[row][col];
				cell.setBorder(defaultBorder);
				cell.setMinimumSize(new Dimension(48, 48));
				cell.addMouseListener(new SelectedCellListener());
				this.add(cell, gbc);
			}
		}
	}

	public void redrawGrid(int xChange, int yChange){
		if((xChange == -1 && startCol > 0) || (xChange == 1 && startCol < 143)){
			startCol += xChange;
		}
		if((yChange == -1 && startRow > 0) || (yChange == 1 && startRow < 143)){
			startRow += yChange;
		}
		this.removeAll();
		drawGrid();
		revalidate();
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		showImageMenu();
	}

	public class SelectedCellListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON3)
				showPopupMenu(e);
		}

		public void mouseEntered(MouseEvent e) {
			TilePanel current = (TilePanel) e.getComponent();
			current.setBorder(selectBorder);
		}

		public void mouseExited(MouseEvent e) {
			TilePanel current = (TilePanel) e.getComponent();
			current.setBorder(defaultBorder);
		}


		public void mousePressed(MouseEvent arg0) {
			//showPopupMenu(arg0);
		}

		public void mouseReleased(MouseEvent arg0) {
			//showPopupMenu(arg0);
		}
	}
}