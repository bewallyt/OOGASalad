package authoring;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Data.ImageManager;

public class TileImageEditor extends ImageEditor {
	
	JFrame myWindow;
	public TileImageEditor() {
		super();
		myWindow = new JFrame("Tile Image Editor");
		myWindow.setLayout(new BorderLayout());
		myWindow.setSize(360, 360);
		myWindow.getContentPane().add(scroll, BorderLayout.CENTER);
		
		myWindow.setVisible(true);
	}
	
	public void setVisible(boolean input){
		myWindow.setVisible(input);
	}


}
