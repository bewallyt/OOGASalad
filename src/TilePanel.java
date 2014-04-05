import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel{

	private ImageIcon myTileImage;
	private ImageIcon myTileDataImage;
	private JLabel myTileLabel;
	private JLabel myTileDataLabel;
	protected int myRow;
	protected int myCol;

	public TilePanel(int row, int col){
		myRow = row;
		myCol = col;
		this.setLayout(new BorderLayout());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(48, 48);
	}
	
	public void setTileImage(String fileName){	
		if(myTileLabel != null)
			this.remove(myTileLabel);
		myTileImage = new ImageIcon("C:/Users/Richard Cao/Desktop/Spring2014/" + fileName + ".jpg"); 
		myTileLabel = new JLabel(myTileImage);
		myTileLabel.setLayout(new BorderLayout());
		myTileLabel.setOpaque(false);
		this.add(myTileLabel);
	}
	
	//The way I'm putting two images on one tile is that I'm adding the TileData image label to the
	//tile image label. Does this work when the game actually runs?
	public void setTileDataImage(String fileName){
		if(myTileDataLabel != null)
			this.remove(myTileDataLabel);
		myTileDataImage = new ImageIcon("C:/Users/Richard Cao/Desktop/Spring2014/" + fileName + ".jpg"); 
		myTileDataLabel = new JLabel(myTileDataImage);
		myTileLabel.add(myTileDataLabel);
	}
}