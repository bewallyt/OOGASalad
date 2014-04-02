import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;

public class CellPanel extends JPanel{

	private ImageIcon myImage;
	protected int myRow;
	protected int myCol;

	public CellPanel(int row, int col){
		myRow = row;
		myCol = col;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(48, 48);
	}
	
	public void setImage(String fileName){	
		myImage = new ImageIcon("C:/Users/Richard Cao/Desktop/Spring2014/" + fileName + ".jpg"); 
		JLabel label = new JLabel(myImage);
		this.add(label);
	}
}