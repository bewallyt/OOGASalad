package authoring.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import authoring.features.FeatureManager;
import authoring.gameObjects.WeaponItemViewer;


import Data.ImageFile;
import Data.ImageManager;

/**
 * Class that contains information relavent to every Grid in the authoring environment
 * @author Richard Cao
 *
 */
public class Grid extends JPanel{

	private JPopupMenu popup;
	private TileImageEditor imageEditor;
	private WeaponItemViewer weaponItemViewer;
	private TilePanel currentPanel;
	private String[] popupMenuItems = {"Tile Image Editor", "Coordinates", "Weapon/Item Viewer"};
	private JMenuItem myCoordinates;
	private Border defaultBorder;
	private Border selectBorder;
	private TilePanel[][] world;
	private JPanel panel;
	private final static String DEFAULT_TILE_IMAGE = "DefaultTileImage.jpg";
	private ImageIcon defaultBackground;
	private int myNumRows;
	private int myNumCols;

	public Grid(int row, int col) {

		ImageManager m = new ImageManager();
		ImageFile i = m.loadTileImage(DEFAULT_TILE_IMAGE);
		defaultBackground = new ImageIcon(i.getImage(), DEFAULT_TILE_IMAGE);
		this.setLayout(new GridBagLayout());
		panel=this;
		myNumRows = row;
		myNumCols = col;
		mapMaker();		
		this.setOpaque(false);
		imageEditor = FeatureManager.tileEditor;
		weaponItemViewer = FeatureManager.weaponItemViewer;
		popupMenuMaker();
		defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
		selectBorder = new MatteBorder(2, 2, 2, 2, Color.BLUE);
		drawGrid();
	}

	/**
	 * @param x Row of the tile 
	 * @param y Column of the tile
	 * @return Returns the Tile at the specified (x,y) location
	 */
	public TilePanel getTilePanel(int x, int y){
		return world[x][y];
	}
	
	/**
	 * Creates the array of Tiles on the authoring view
	 */
	private void mapMaker(){
		world = new TilePanel[myNumRows][myNumCols];

		for (int row = 0; row < myNumRows; row++) {
			for (int col = 0; col < myNumCols; col++) {				
				world[row][col] = new TilePanel(row, col, defaultBackground);
			}
		}
	}

	/**
	 * Repaints the tiles within this Grid
	 */
	public void tileRepaint(){
		for(int row = 0; row < world.length; row++)
			for(int col = 0; col < world.length; col++){
				TilePanel temp = world[row][col];
				temp.update();
				world[row][col] = temp;
			}
	}

	/**
	 * Creates all of the popup menus used in authoring (listed in popupMenuItems)
	 */
	private void popupMenuMaker(){
		popup = new JPopupMenu();
		for(int i = 0; i < popupMenuItems.length; i++){
			JMenuItem menuItem = new JMenuItem(popupMenuItems[i]);
			if(i==1) myCoordinates = menuItem;
			menuItem.setActionCommand(popupMenuItems[i]);
			menuItem.addActionListener(new PopupMenuListener());
			popup.add(menuItem);
		}
	}

	/**
	 * Displays a given popup menu
	 */
	private void showPopupMenu(MouseEvent e){
		popup.show(e.getComponent(), e.getX(), e.getY());
	}

	/**
	 * Displays the image menu
	 */
	public void showImageMenu(){
		imageEditor.setVisibility(true);
	}

	/**
	 * Draws the grid of Tiles
	 */
	public void drawGrid(){
		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < myNumRows; row++) {
			for (int col = 0; col < myNumCols; col++) {
				gbc.gridx = col;
				gbc.gridy = row;
				TilePanel cell = world[row][col];
				cell.setBorder(defaultBorder);
				cell.addMouseListener(new SelectedCellListener());
				cell.addMouseMotionListener(new SelectedCellListener());
				this.add(cell, gbc);
			}
		}
	}

	private class PopupMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if("Tile Image Editor".equals(e.getActionCommand())){
				showImageMenu();
			} else if("Weapon/Item Viewer".equals(e.getActionCommand())){
				showWIViewer();
			}
		}
	}

	private void showWIViewer() {
		weaponItemViewer.getWeaponFrame().setVisible(true);
	}


	public class SelectedCellListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON3){
				myCoordinates.setText("Coordinates: " + currentPanel.getRow() + ", " + currentPanel.getCol());
				showPopupMenu(e);
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currentPanel = (TilePanel) e.getComponent();
			currentPanel.setBorder(selectBorder);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			currentPanel = (TilePanel) e.getComponent();
			currentPanel.setBorder(defaultBorder);
		}

		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				TilePanel selected = (TilePanel) e.getComponent();
				placeImage(selected);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e){
			currentPanel=(TilePanel) panel.getComponentAt(getMousePosition());
			placeImage(currentPanel);
		}

		private void placeImage(TilePanel currentPanel){
			Icon i=imageEditor.selectImage();
			if(i!=null){
				currentPanel.setTileImage(imageEditor.selectImage());	
			}	
		}
	}
}