import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid extends JPanel implements MouseListener, ActionListener{
	
	private CellPanel selectedCell;
	private JPopupMenu popup;
	private String[] popupMenuItems = {"Tile Editor", "Set as Player Start Point", "Clear Tile"};
	private JList list;
	private Border defaultBorder;
	private Border selectBorder;
	//temporary lists to test features
	private String[] tileTypes = {"Wall", "Water", "TestTile", "NPC"};
	private String[] tileImageNames = {"Wall", "Water", "TestTile", "NPC", "Grey"};
	
	public Grid() {
		setLayout(new GridBagLayout());
		this.setOpaque(false);
		popupMenuMaker();
		GridBagConstraints gbc = new GridBagConstraints();
		// Creates the grid of CellPanels
		defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
		selectBorder = new MatteBorder(2, 2, 2, 2, Color.BLUE);
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 12; col++) {
				gbc.gridx = col;
				gbc.gridy = row;
				CellPanel cell = new CellPanel(row, col);
				cell.setBorder(defaultBorder);
				cell.addMouseListener(this);
				add(cell, gbc);
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
		selectedCell = (CellPanel) e.getComponent();
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
		
		/*String selectedTileType = ListDialog.showDialog(
                this,
                null,
                "Select a tile type: ",
                "Tile Creator",
                tileTypes,
                null,
                null);*/
		
		String selectedTileImage= ListDialog.showDialog(
                this,
                null,
                "Select an image for the tile: ",
                "Tile Creator",
                tileImageNames,
                null,
                null);
		
		selectedCell.setImage(selectedTileImage);
		this.revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		showImageList();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton() == MouseEvent.BUTTON3)
			showPopupMenu(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		CellPanel current = (CellPanel) arg0.getComponent();
		current.setBorder(selectBorder);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		CellPanel current = (CellPanel) arg0.getComponent();
		current.setBorder(defaultBorder);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//showPopupMenu(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//showPopupMenu(arg0);
	}
}