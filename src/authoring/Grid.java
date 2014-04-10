package authoring;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid extends JPanel{

	private static final int WORLD_WIDTH = 144;
	private static final int WORLD_HEIGHT = 144;
	private int startRow = 0;
	private int startCol = 0;
	private WorldData wd;
	private TilePanel selectedCell;
	private JPopupMenu popup;
	private TileImageEditor imageEditor;
	private String[] popupMenuItems = { "Tile Image Editor", "Set as Player Start Point", "Clear Tile"};
	private Border defaultBorder;
	private Border selectBorder;
	private TilePanel[][] world;

	public Grid() {
		this.setLayout(new GridBagLayout());
		mapMaker();		
		this.setOpaque(false);
		imageEditor = FeatureManager.imageEditor;
		popupMenuMaker();
		// Creates the grid of TilePanels
		defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
		selectBorder = new MatteBorder(2, 2, 2, 2, Color.BLUE);
		drawGrid();
	}

	private void mapMaker(){
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
			menuItem.addActionListener(new PopupMenuListener());
			popup.add(menuItem);
		}
	}

	private void showPopupMenu(MouseEvent e){
		selectedCell = (TilePanel) e.getComponent();
		popup.show(e.getComponent(), e.getX(), e.getY());
	}

	public void showImageMenu(){
		
		/*if(selectedTileImage == null){
			return;
		}	
		selectedCell.setTileImage(selectedTileImage);
		wd.getMap(WorldData.DEFAULT_MAP).addTileData(selectedCell.getRow(), selectedCell.getCol(), selectedCell.getTileData());*/

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

	public class PopupMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showImageMenu();
		}
	}


	public class SelectedCellListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				TilePanel selected = (TilePanel) e.getComponent();
				selected.setTileImage(imageEditor.selectImage());
				repaint();
			}
			if(e.getButton() == MouseEvent.BUTTON3)
				showPopupMenu(e);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			TilePanel current = (TilePanel) e.getComponent();
			current.setBorder(selectBorder);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			TilePanel current = (TilePanel) e.getComponent();
			current.setBorder(defaultBorder);
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {

		}
		
		@Override
		public void mouseDragged(MouseEvent e){

		}
	}
}