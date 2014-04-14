package authoring;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid extends JPanel{

	private static final int WORLD_WIDTH = 100;
	private static final int WORLD_HEIGHT = 100;
	private int startRow = 0;
	private int startCol = 0;
	private TilePanel selectedCell;
	private JPopupMenu popup;
	private TileImageEditor imageEditor;
	private String[] popupMenuItems = { "Tile Image Editor", "Set as Player Start Point", "Clear Tile"};
	private Border defaultBorder;
	private Border selectBorder;
	//temporarily static until I can figure out a workaround with the other authoring folks
	protected static TilePanel[][] currentMap;
	private TilePanel[][] world;
	private JPanel panel;

	public Grid() {
		this.setLayout(new GridBagLayout());
		panel=this;
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

		currentMap = new TilePanel[WORLD_HEIGHT][WORLD_WIDTH];

		for (int row = 0; row < WORLD_HEIGHT; row++) {
			for (int col = 0; col < WORLD_WIDTH; col++) {				
				currentMap[row][col] = new TilePanel(row, col);
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
		imageEditor.setVisible(true);
	}

	public void drawGrid(){
		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = startRow; row < WORLD_HEIGHT; row++) {
			for (int col = startCol; col < WORLD_WIDTH; col++) {
				gbc.gridx = col;
				gbc.gridy = row;
				TilePanel cell = currentMap[row][col];
				cell.setBorder(defaultBorder);
				cell.setMinimumSize(new Dimension(36, 36));
				cell.addMouseListener(new SelectedCellListener());
				cell.addMouseMotionListener(new SelectedCellListener());
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
			TilePanel selected = (TilePanel) e.getComponent();
			placeImage(selected);
		}

		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e){
			TilePanel currentPanel=(TilePanel) panel.getComponentAt(getMousePosition());
			placeImage(currentPanel);
		}

		private void placeImage(TilePanel currentPanel){
			Icon i=imageEditor.selectImage();
			if(i!=null){
				currentPanel.setTileImage(imageEditor.selectImage());
				currentPanel.setBorder(defaultBorder);
				repaint();
			}	
		}
	}
}