package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageChooser extends Feature implements ActionListener{
	private JButton myChooseImageButton;
	private String fileName;
	private String filePath;
	private JFrame frame;
	private WorldData myWorldData;

	
	public ImageChooser(){
		myChooseImageButton = new JButton("Choose Tile Image");
		myChooseImageButton.addActionListener(this);
		myChooseImageButton.setActionCommand("choose");
		myComponents.put(myChooseImageButton, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if("choose".equals(e.getActionCommand())){
			fileName = JOptionPane.showInputDialog("Name your tile image:");
			if(fileName.equals("")){
				JOptionPane.showMessageDialog(frame, "Must name image.", "Error Message", JOptionPane.ERROR_MESSAGE);
				fileName = JOptionPane.showInputDialog("Please name the image:");
				chooseImage();
			} else{
				chooseImage();
			}
		}
		
	}
	
	private void chooseImage(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG, PNG, GIF", "jpg","gif","png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getAbsolutePath());
		}
		filePath = chooser.getSelectedFile().getAbsolutePath();
		addToWorldData();
	}
	
	private void addToWorldData(){
		//myWorldData.saveImage(s, f);
		//myWorldData.saveImage(fileName, filePath);
	}
	
	
	
	

}
