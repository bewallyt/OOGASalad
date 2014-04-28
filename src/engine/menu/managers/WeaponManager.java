package engine.menu.managers;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.item.Weapon;
import engine.menu.MenuInteractionMatrix;

public class WeaponManager extends MenuManager implements InteractionBox {

	private Player myPlayer;
	private MenuInteractionMatrix myMIM;
	private MenuManager myMenuManager;

	public WeaponManager(Player p, MenuManager mm, MenuInteractionMatrix mim) {
		super(p, null, mim);
		myPlayer = p;
		myMIM = mim;
		myMenuManager = mm;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		paintMenu(g2d, height, width);
		paintWeaponBox(g2d, "ImageFiles/PokemonBox.png", 17, 20, 300, 200);
		paintWeaponData(g2d);
		drawSelector(g2d, 198, 40, 280, 45, 44, myMIM);

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
	
	protected void paintAllWeaponData(Graphics g2d, int height, int width){
		paintMenu(g2d, height, width);
		paintWeaponBox(g2d, "ImageFiles/PokemonBox.png", 17, 20, 300, 200);
		paintWeaponData(g2d);
	}

	public void createWeaponInfoNodes() {
		for (int i = 0; i < myPlayer.getWeaponList().size(); i++) {
			myMIM.setNode(new WeaponInfoNode(myPlayer, this, myMenuManager, i), 0,
					i);
		}
	}

}
