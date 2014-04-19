package GameView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameSelect{
	
	private String[] games;
	public GameSelect() {
		try {
			games = getGameNames(getListOfGames());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSelectedGame() {
		JFrame select = new JFrame();
		return (String) JOptionPane.showInputDialog(select, "Select a Game", "", JOptionPane.PLAIN_MESSAGE, null, games, "");
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