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
import engine.menu.nodes.WeaponInfoNode;

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
		paintFeatureBox(g2d, "ImageFiles/PokemonBox.png", 17, 20, 300, 200);
		paintWeaponData(g2d);
		drawSelector(g2d, 198, 40, 280, 45, 44, myMIM);

	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

	protected void paintFeatureBox(Graphics g2d, String imgPath, int x, int y,
			int width, int height) {
		Image img = new ScaledImage(width, height, imgPath).scaleImage();
		g2d.drawImage(img, x, y, null);
	}

	protected void paintFeatureImage(Graphics g2d, Image feature, int x, int y) {
		g2d.drawImage(feature, x, y, null);
	}

	protected void paintFeatureName(Graphics g2d, String name, int x, int y) {
		g2d.drawString(name, x, y);
	}

	private void paintWeaponData(Graphics g2d) {
		List<Weapon> features = myPlayer.getWeaponList();
		int emptySpace = 0;
		for (int i = 0; i < features.size(); i++) {

			if (i != 0) {
				emptySpace = 1;
			} else {
				emptySpace = 0;
			}

			Image scaledWeaponImage = features.get(i).getImage()
					.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			System.out.println("scaleWeaponImage: " + scaledWeaponImage);
			paintFeatureImage(g2d, scaledWeaponImage, 37, 45 + i * 40 + 5
					* emptySpace);
			paintFeatureName(g2d, features.get(i).toString(), 80, 67 + i * 40 + 5
					* emptySpace);

		}

	}

	public void createWeaponInfoNodes() {
		for (int i = 0; i < myPlayer.getWeaponList().size(); i++) {
			myMIM.setNode(new WeaponInfoNode(myPlayer, this, myMenuManager, i),
					0, i);
		}
	}

}
