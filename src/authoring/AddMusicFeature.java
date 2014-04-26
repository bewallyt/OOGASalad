package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Richard Cao
 *	Class that allows the user to upload music and choose which maps
 *	will play what song when the player is on that map
 */
public class AddMusicFeature extends Feature implements ActionListener{

	private JButton musicButton;

	public AddMusicFeature() {
		musicButton = new JButton("Add Music");
		musicButton.addActionListener(this);
		myComponents.put(musicButton, BorderLayout.SOUTH);
	}
	
	private void musicSetter(){
		
	}
	
	private void musicUploader(){
		JTextField name = new JTextField(10);
		JPanel panel = new JPanel();
		panel.add(name);
		int result = JOptionPane.showOptionDialog(null, panel, "Name Your Song", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if(result == JOptionPane.OK_OPTION){
			if(name.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Complete required fields.", "Error Message", JOptionPane.ERROR_MESSAGE);
				musicUploader();
			}
		}
		chooseMusic();
	}

	private void chooseMusic() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"wav", "WAV");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getAbsolutePath());
		}
		File songFile = chooser.getSelectedFile();
		MapData cm = FeatureManager.getWorldData().getCurrentMap();
		//cm.addMusic(songFile);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.musicSetter();
	}

}
