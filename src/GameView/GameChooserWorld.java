package GameView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

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
	private int increase;
	private boolean isExpanding = false;

	public GameChooserWorld(int playWidth, int playHeight, Player p,
			String chooseScreen) {
		super(playWidth, playHeight, p, chooseScreen);
		myGames[0][0] = "Zelda";
		myGames[1][0] = "Pokemon";
		myGames[0][1] = "Other";
		myGames[1][1] = "FinalFantasy";
		matrix2 = new InteractionMatrix2x2();
		increase = 0;

	}

	@Override
	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset,
			int yOffset) {
		if (isExpanding) {
			increase += 2;
		}
		PokemonFont.setFont(g, 16f);
		if (!isExpanding) {
			drawSelector(g, 0, 0, 250, 250, 250);
		} else if (increase < 250) {

			expandImage(g, "ImageFiles/" + matrix2.getCurrentNode().getGame()
					+ "Title.png", matrix2.getSelectedNodeLocation()[1],
					matrix2.getSelectedNodeLocation()[0]);

		} else {
			setBackround("ImageFiles/" + matrix2.getCurrentNode().getGame()
					+ "Title.png");
			setGameString();

		}
	}

	private void drawSelector(Graphics2D g2d, int xPos, int yPos, int width,
			int height, int scale) {

		int[] selectedOptionLoc = matrix2.getSelectedNodeLocation();
		if ((selectedOptionLoc[0] == 0) && (selectedOptionLoc[1] == 1)) {
			Image img = new ScaledImage(width + 2, height + 2,
					"ImageFiles/WhiteSelector.png").scaleImage();
			g2d.setColor(changeColor());
			g2d.drawString("Choose Game", selectedOptionLoc[0] * 250 + 45,
					selectedOptionLoc[1] * 250 + 130);
			g2d.drawImage(img, xPos + scale * selectedOptionLoc[0], yPos
					+ scale * selectedOptionLoc[1], null);

		} else {
			Image img = new ScaledImage(width + 2, height + 2,
					"ImageFiles/Selector.png").scaleImage();
			g2d.setColor(changeColor());
			g2d.drawString("Choose Game", selectedOptionLoc[0] * 250 + 45,
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

	}

	public void moveUp() {
		if (isExpanding == false) {
			matrix2.moveUp();
		}
	}

	public void moveDown() {
		if (isExpanding == false) {
			matrix2.moveDown();
		}
	}

	public void moveRight() {
		if (isExpanding == false) {
			matrix2.moveRight();
		}
	}

	public void moveLeft() {
		if (isExpanding == false) {
			matrix2.moveLeft();
		}
	}

	public void setGameString() {
		isExpanding = true;
		if (increase > 250) {
			myGameString = matrix2.getCurrentNode().getGame();
		}
	}

	private void expandImage(Graphics g2d, String imageFile, int xPos, int yPos) {
		if ((xPos == 1) && (yPos == 0)) {
			// Other
			Image img = new ScaledImage(250 + increase, 250 + increase,
					imageFile).scaleImage();
			g2d.drawImage(img, 0, 250 - increase, null);
		}
		else if ((xPos == 0) && (yPos == 1)) {
			// Pokemon
			Image img = new ScaledImage(250 + increase, 250 + increase,
					imageFile).scaleImage();
			g2d.drawImage(img, 250 - increase, 0, null);
		}		
		else if ((xPos == 1) && (yPos == 1)) {
			// Final Fantasy
			Image img = new ScaledImage(250 + increase, 250 + increase,
					imageFile).scaleImage();
			g2d.drawImage(img, 250 - increase, 250 - increase, null);
		}
		else if ((xPos == 0) && (yPos == 0)) {
			// Final Fantasy
			Image img = new ScaledImage(250 + increase, 250 + increase,
					imageFile).scaleImage();
			g2d.drawImage(img, 0, 0, null);
		}
		

	}

	public String getGameString() {
		return myGameString;
	}

}
