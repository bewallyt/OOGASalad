package authoring;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.View;

import Data.ImageManager;

public class TileImageEditor extends ImageEditor {
	
	private JFrame myWindow;
	private JList list;
	private JScrollPane scroll;
	private DefaultListModel model;
	public static final String DEFAULT_IMAGE_SAVE_EXTENSION=".jpg";
	ImageManager m=new ImageManager();

	public TileImageEditor() {
		super();
		myWindow = new JFrame("Tile Image Editor");
		myWindow.setLayout(new BorderLayout());
		//myWindow.setSize(360, 360);
		myWindow.getContentPane().add(scroll, BorderLayout.CENTER);
		myWindow.setBounds(620, 0, 360, 360);
		
		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		scroll = new JScrollPane(list);
		myWindow.getContentPane().add(scroll, BorderLayout.CENTER);
		
		myWindow.setVisible(true);
	}
	
	public void addImage(Image m, String s){	
		ImageIcon x = new ImageIcon(m, s);
		model.addElement(x);
	}
	
	public void setVisible(boolean input){
		myWindow.setVisible(input);
	}


}
