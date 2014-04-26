package GameView;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;

public class GameSelectManager implements InteractionBox {
	
	private int colorMod = 0;

	public GameSelectManager() {

	}

	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font = null;

		try {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
			Font sizedFont = font.deriveFont(16f);
			g2d.setFont(sizedFont);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g2d.setColor(changeColor());
		g2d.drawString("Press Start", 178, 238);
	}
	
	public Color changeColor(){
		colorMod = colorMod % 60;
		Color startColor;
		if(colorMod < 30){
			startColor = Color.black;
		}
		else{
			startColor = Color.white;
		}
		colorMod++;
		return startColor;
	}

	//
	// private String[] games;
	// public GameSelectTest() {
	// try {
	// games = getGameNames(getListOfGames());
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// public String getSelectedGame() {
	// JFrame select = new JFrame();
	// return (String) JOptionPane.showInputDialog(select,
	// Constants.SELECTGAMETEXT, "", JOptionPane.PLAIN_MESSAGE, null, games,
	// "");
	// }
	//
	// private static String[] getGameNames (List<File> gameFiles) {
	// String[] gameNames = new String[gameFiles.size()];
	// for (int i = 0; i < gameFiles.size(); i++) {
	// gameNames[i] = gameFiles.get(i).getName();
	// }
	// return gameNames;
	// }
	//
	// private static List<File> getListOfGames () throws IOException {
	// String path = new File(".").getCanonicalPath() +
	// Constants.SAVEDGAMESPATH;
	// File folder = new File(path);
	// List<File> games = new ArrayList<File>();
	//
	// for (File file : folder.listFiles()) {
	// games.add(file);
	// }
	// return games;
	// }

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

}