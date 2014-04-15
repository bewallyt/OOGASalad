package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageChooser extends Feature implements ActionListener{
	private JButton myChooseImageButton;
	private JButton myChooseGridImageButton;
	private String fileName;
    private String determineImage;
    private String identifier;
    private JFrame frame;
	private File imageFile;
	private ImageResizer myImResizer;
    private Object[] choices = {"Grid Object","Tile Image"};

	
	public ImageChooser(){
		myChooseImageButton = new JButton("New Tile Image");
		myChooseImageButton.addActionListener(this);
		myChooseImageButton.setActionCommand("choose");
		myChooseGridImageButton = new JButton("New Grid Object Image");
		myChooseGridImageButton.addActionListener(this);
		myChooseGridImageButton.setActionCommand("grid");
		myComponents.put(myChooseImageButton, BorderLayout.SOUTH);
		myImResizer = new ImageResizer();


	}

	public void actionPerformed(ActionEvent e) {
		if("choose".equals(e.getActionCommand()) || "grid".equals(e.getActionCommand())){

			fileName = JOptionPane.showInputDialog("Name your image:");
            determineImage = (String)JOptionPane.showInputDialog(frame,"What type of image is this?","Image determination.",JOptionPane.PLAIN_MESSAGE,null,choices,"Grid Object");
            identifier = determineImage.replaceAll("\\s","").toLowerCase();
			if(fileName.equals("")){
				JOptionPane.showMessageDialog(frame, "Must name image.", "Error Message", JOptionPane.ERROR_MESSAGE);
				fileName = JOptionPane.showInputDialog("Please name the image:");
				try {
					chooseImage(e);
				} catch (IOException e1) {}
			} else{
				try {
					chooseImage(e);
				} catch (IOException e1) {}
			}
		}
		
	}
	
	private void chooseImage(ActionEvent e) throws IOException{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG, PNG, GIF", "jpg","gif","png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getAbsolutePath());
		}
		imageFile = chooser.getSelectedFile();
		myImResizer.storeImage(fileName, imageFile, identifier);
	}		

}
