package engine.menu.managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.item.Weapon;
import engine.menu.MenuInteractionMatrix;
import engine.menu.nodes.MenuNode;

public class WeaponManager extends MenuManager implements InteractionBox {

	private Player myPlayer;
	private MenuInteractionMatrix myMIM;

	public WeaponManager(Player p) {
		super(p, null);
		myPlayer = p;
		myMIM = new MenuInteractionMatrix(1, 2);
		System.out.println(myMIM.getDimension()[1]);
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		paintMenu(g2d, height, width);
		paintWeaponBox(g2d, "ImageFiles/PokemonBox.png", 17, 20, 300, 200);
		paintWeaponData(g2d);
		drawSelector(g2d, 100, 55, width, height, 48);

	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

	private void paintWeaponBox(Graphics g2d, String imgPath, int x, int y,
			int width, int height) {
		Image img = new ScaledImage(width, height, imgPath).scaleImage();
		g2d.drawImage(img, x, y, null);
	}

	private void paintWeaponImage(Graphics g2d, Image feature, int x, int y) {
		g2d.drawImage(feature, x, y, null);
	}

	private void paintWeaponName(Graphics g2d, String name, int x, int y) {
		g2d.drawString(name, x, y);
	}

	private void paintWeaponData(Graphics g2d) {
		List<Weapon> weapons = myPlayer.getWeaponList();
		int emptySpace = 0;
		for (int i = 0; i < weapons.size(); i++) {

			if (i != 0) {
				emptySpace = 1;
			} else {
				emptySpace = 0;
			}

			Image scaledWeaponImage = weapons.get(i).getImage()
					.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			paintWeaponImage(g2d, scaledWeaponImage, 37, 45 + i * 40 + 5
					* emptySpace);
			paintWeaponName(g2d, weapons.get(i).toString(), 80, 67 + i * 40 + 5
					* emptySpace);

		}

	}

	public void moveCursorUp() {
		myMIM.moveUp();
	}

	public void moveCursorDown() {
		myMIM.moveDown();
	}

	public void select() {
		((MenuNode) myMIM.getCurrentNode()).doAction();
	}

	private void drawSelector(Graphics2D g2d, int xPos, int yPos, int width,
			int height, int scale) {

		int[] selectedOptionLoc = myMIM.getSelectedNodeLocation();
		Image img = new ScaledImage(xPos, yPos, "ImageFiles/redrectangle.png")
				.scaleImage();
		g2d.drawImage(img, width - 170, scale * selectedOptionLoc[1], null);

	}

}
