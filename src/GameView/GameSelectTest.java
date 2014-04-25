package GameView;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;
import engine.images.ScaledImage;
import util.Constants;

public class GameSelectTest implements InteractionBox {

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

		g2d.setColor(Color.white);
		Image img = new ScaledImage(200, height + 50, "startmenu.png")
				.scaleImage();
		g2d.drawImage(img, width - 200, 0, null);
		g2d.setColor(Color.black);

		//drawSelector(g2d, xSize, ySize, width, height);
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