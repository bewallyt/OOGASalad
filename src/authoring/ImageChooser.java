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
	private String fileName;
	private File imageFile;
	private JFrame frame;
	private ImageResizer myImResizer;

	
	public ImageChooser(){
		myChooseImageButton = new JButton("New Tile Image");
		myChooseImageButton.addActionListener(this);
		myChooseImageButton.setActionCommand("choose");
		myComponents.put(myChooseImageButton, BorderLayout.SOUTH);
		myImResizer = new ImageResizer();
	}

	public void actionPerformed(ActionEvent e) {
		if("choose".equals(e.getActionCommand())){
			fileName = JOptionPane.showInputDialog("Name your tile image:");
			if(fileName.equals("")){
				JOptionPane.showMessageDialog(frame, "Must name image.", "Error Message", JOptionPane.ERROR_MESSAGE);
				fileName = JOptionPane.showInputDialog("Please name the image:");
				try {
					chooseImage();
				} catch (IOException e1) {}
			} else{
				try {
					chooseImage();
				} catch (IOException e1) {}
			}
		}
		
	}
	
	private void chooseImage() throws IOException{
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
		myImResizer.squareImage(fileName, imageFile);
		
	}		

}
