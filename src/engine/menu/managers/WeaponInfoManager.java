package engine.menu.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import util.PokemonFont;
import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class WeaponInfoManager implements InteractionBox {

	private Player myPlayer;
	private Image myWeaponImage;
	private String myWeaponName;
	private List<Integer> myWeaponStats;
	private List<String> allAttackStats;
	private static final int[] ATTACKNAMEINDICES = new int[] { 0, 4, 8, 12 };
	private static final int[] ATTACKDAMAGEINDICES = new int[] { 1, 5, 9, 13 };
	private static final int[] ATTACKSPEEDINDICES = new int[] { 2, 6, 10, 14 };
	private static final int[] ATTACKEFFECTINDICES = new int[] { 3, 7, 11, 15 };

	public WeaponInfoManager(Player p, Image img, String wn, List<Integer> ws) {
		myPlayer = p;
		myWeaponImage = img;
		myWeaponName = wn;
		myWeaponStats = new ArrayList<Integer>(ws);

	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int xOffset,
			int yOffset) {
		paintInfoBox(g2d, "ImageFiles/PokemonBox.png", -50, -50, 600, 600);
		paintFeatureImage(g2d, myWeaponImage, 30, 30);
		displayFeatureName(g2d, myWeaponName, 150, 55);
		displayFeatureStats(g2d, myWeaponStats, 155, 95);
		displayAttacks(g2d, 30, 160);

	}

	protected void paintInfoBox(Graphics g2d, String imgPath, int x, int y,
			int width, int height) {
		Image img = new ScaledImage(width, height, imgPath).scaleImage();
		g2d.drawImage(img, x, y, null);
	}

	protected void paintFeatureImage(Graphics g2d, Image FeatureImage, int x, int y) {
		Image scaledFeatureImage = FeatureImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);
		g2d.drawImage(scaledFeatureImage, x, y, null);
	}

	protected void displayFeatureName(Graphics g2d, String FeatureName, int x, int y) {
		PokemonFont.setFont(g2d, 32f);
		g2d.drawString(FeatureName, x, y);
	}

	private void displayFeatureStats(Graphics g2d, List<Integer> FeatureStats,
			int x, int y) {
		PokemonFont.setFont(g2d, 16f);
		String damageRatio = "Damage: " + Integer.toString(FeatureStats.get(0))
				+ "/" + Integer.toString(FeatureStats.get(1));
		g2d.drawString(damageRatio, x, y);
		String speedRatio = "Speed:  " + Integer.toString(FeatureStats.get(2))
				+ "/" + Integer.toString(FeatureStats.get(3));

		g2d.drawString(speedRatio, x, y + 30);
	}

	private void displayAttacks(Graphics g2d, int x, int y) {
		// 4 Attacks

		for (int i = 0; i < allAttackStats.size(); i++) {
			if (contains(ATTACKNAMEINDICES, i)) {
				PokemonFont.setFont(g2d, 14f);
				g2d.drawString(allAttackStats.get(i), x, y + (i / 4) * 80 + 20);
			} else if (contains(ATTACKDAMAGEINDICES, i)) {
				PokemonFont.setFont(g2d, 10f);
				g2d.drawString(allAttackStats.get(i), x, y + (i / 4) * 80 + 40);
			} else if (contains(ATTACKSPEEDINDICES, i)) {
				PokemonFont.setFont(g2d, 10f);
				g2d.drawString(allAttackStats.get(i), x + 150, y + (i / 4) * 80
						+ 40);
			} else if (contains(ATTACKEFFECTINDICES, i)) {
				PokemonFont.setFont(g2d, 10f);
				g2d.drawString(allAttackStats.get(i), x, y + (i / 4) * 80 + 60);
			}
		}

	}

	public void extractAttackStats(List<Integer> ads, List<String> ane) {

		allAttackStats = new ArrayList<String>();
		for (int i = 0; i < ane.size(); i++) {

			String attackName = "Attack " + Integer.toString(i / 2 + 1) + ": "
					+ ane.get(i);
			String attackDamage = "Damage: " + Integer.toString(ads.get(i));
			String attackEffect = "Effect: " + ane.get(i += 1);
			String attackSpeed = "Speed: " + Integer.toString(ads.get(i));

			allAttackStats.add(attackName);
			allAttackStats.add(attackDamage);
			allAttackStats.add(attackSpeed);
			allAttackStats.add(attackEffect);

		}

	}

	private boolean contains(int[] array, int key) {
		for (int i : array) {
			if (i == key) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

}
