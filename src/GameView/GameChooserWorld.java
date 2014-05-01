package GameView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import util.Constants;
import util.PokemonFont;
import engine.dialogue.InteractionBox;
import engine.dialogue.InteractionMatrix2x2;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.world.TitleWorld;

public class GameChooserWorld extends TitleWorld implements InteractionBox {

	private int colorMod = 0;
	private InteractionMatrix2x2 matrix2;
	private String[][] myGames = new String[2][2];
	private String myGameString;

	public GameChooserWorld(int playWidth, int playHeight, Player p,
			String chooseScreen) {
		super(playWidth, playHeight, p, chooseScreen);
		myGames[0][0] = Constants.TITLE_1;
		myGames[1][0] = Constants.TITLE_2;
		myGames[0][1] = Constants.TITLE_3;
		myGames[1][1] = Constants.TITLE_4;
		matrix2 = new InteractionMatrix2x2();

	}

	@Override
	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset,
			int yOffset) {
		PokemonFont.setFont(g, 16f);
		drawSelector(g, 0, 0, 250, 250, 250);

	}

	private void drawSelector(Graphics2D g2d, int xPos, int yPos, int width,
			int height, int scale) {

		int[] selectedOptionLoc = matrix2.getSelectedNodeLocation();
		if ((selectedOptionLoc[0] == 0) && (selectedOptionLoc[1] == 1)) {
			Image img = new ScaledImage(width + 2, height + 2,
					"ImageFiles/WhiteSelector.png").scaleImage();
			g2d.setColor(changeColor());
			g2d.drawString(Constants.CHOOSE_GAME, selectedOptionLoc[0] * 250 + 45,
					selectedOptionLoc[1] * 250 + 130);
			g2d.drawImage(img, xPos + scale * selectedOptionLoc[0], yPos
					+ scale * selectedOptionLoc[1], null);

		} else {
			Image img = new ScaledImage(width + 2, height + 2,
					"ImageFiles/Selector.png").scaleImage();
			g2d.setColor(changeColor());
			g2d.drawString(Constants.CHOOSE_GAME, selectedOptionLoc[0] * 250 + 45,
					selectedOptionLoc[1] * 250 + 130);
			g2d.drawImage(img, xPos + scale * selectedOptionLoc[0], yPos
					+ scale * selectedOptionLoc[1], null);
		}

	}

	public void createTitleNodes() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				matrix2.setNode(new GameChooserNode(myGames[i][j]), i, j);
			}
		}
	}

	private Color changeColor() {
		colorMod = colorMod % 60;
		Color startColor;
		if (colorMod < 30) {
			startColor = Color.black;
		} else {
			startColor = Color.white;
		}
		colorMod++;
		return startColor;
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

	public void moveUp() {
		matrix2.moveUp();
	}

	public void moveDown() {
		matrix2.moveDown();
	}

	public void moveRight() {
		matrix2.moveRight();
	}

	public void moveLeft() {
		matrix2.moveLeft();
	}

	public void setGameString() {
		myGameString = matrix2.getCurrentNode().getGame();
	}

	public String getGameString() {
		return myGameString;
	}

}
