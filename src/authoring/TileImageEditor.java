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

import Data.ImageManager;

public class TileImageEditor extends JFrame {

	private JList list;
	private JScrollPane scroll;
	private DefaultListModel model;
	public static final String DEFAULT_IMAGE_SAVE_EXTENSION=".jpg";
	ImageManager m=new ImageManager();

	public TileImageEditor(String s) {
		super(s);
		this.setLayout(new BorderLayout());
		this.setSize(360, 360);
		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		scroll = new JScrollPane(list);
		this.getContentPane().add(scroll, BorderLayout.CENTER);
		
		//for(String image: m.getSavedImageList()){
		//	addImage(m.loadImage(image), image);
		//}
	}
	
	
	public void addImage(Image m, String s){	
		ImageIcon x = new ImageIcon(m, s);
		model.addElement(x);
	}
	
	public ImageIcon selectImage(){
		if(list.getSelectedIndex()!=-1){
			return (ImageIcon) model.get(list.getSelectedIndex());
		}	
		return null;
	}
	
	public class SelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			//selectImage();
		}
		
	}

}
