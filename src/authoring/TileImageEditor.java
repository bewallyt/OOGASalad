package authoring;

import java.awt.*;
import javax.swing.*;

public class TileImageEditor {
	
	private JFrame myWindow;
	private JPanel myPanel;
	private JScrollPane images;
	
	public TileImageEditor() {
		myWindow = new JFrame("Tile Image Editor");
		myWindow.setLayout(new BorderLayout());
		myWindow.setSize(360, 360);
		myWindow.setVisible(true);
	}

}
