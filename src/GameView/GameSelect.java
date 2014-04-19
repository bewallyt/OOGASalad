package GameView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameSelect{
	private GameFrame myGame;
	private String gameName;
	private String[] games;
	private JComboBox comboBox;
	
	public GameSelect (GameFrame game) {
		myGame = game;
		
		try {
			games = getGameNames(getListOfGames());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JFrame f =  new JFrame("Select");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.getContentPane().add(makeCombo(games), BorderLayout.NORTH);
		f.getContentPane().add(makeButtons(), BorderLayout.SOUTH);
		
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		
	}
	
	public JPanel makeCombo(String[] games) {
		JPanel p = new JPanel();
		
		comboBox = new JComboBox(games);
		
		p.add(comboBox);
		
		return p;
	}
	
	public JPanel makeButtons() {
		JPanel p = new JPanel();
		
		JButton goButton = new JButton("Go");
		goButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				myGame.initialize((String) comboBox.getSelectedItem());
				
			}
		});
		JButton cancelButton = new JButton("Cancel");
		goButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		p.add(goButton);
		p.add(cancelButton);
		
		return p;
	}
	
	
	
	public String getGameName() {
		return gameName;
	}
	
	private static String[] getGameNames (List<File> gameFiles) {
		String[] gameNames = new String[gameFiles.size()];
		for (int i = 0; i < gameFiles.size(); i++) {
			gameNames[i] = gameFiles.get(i).getName();
		}
		
		return gameNames;
	}
	
	private static List<File> getListOfGames () throws IOException {
		String path = new File(".").getCanonicalPath() + "/src/SavedGames";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List<File> games = new ArrayList<File>();
		
		for (File file : listOfFiles) {
				games.add(file);
		}
		return games;
	}
	
	
}