package engine.menu.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.menu.MenuInteractionMatrix;

public class BagManager extends WeaponManager implements InteractionBox {

	private Player myPlayer;
	private MenuInteractionMatrix myMIM;
	private MenuManager myMenuManager;

	public BagManager(Player p, MenuManager mm, MenuInteractionMatrix mim) {
		super(p, mm, mim);
		myPlayer = p;
		myPlayer = p;
		myMIM = mim;
		myMenuManager = mm;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {
		paintMenu(g2d, height, width);
		paintFeatureBox(g2d, "ImageFiles/PokemonBox.png", 17, 20, 300, 200);
		paintItemData(g2d);

	}
	
	private void paintItemData(Graphics g2d) {
		List<Item> features = myPlayer.getItems();
		int emptySpace = 0;
		for (int i = 0; i < features.size(); i++) {

			if (i != 0) {
				emptySpace = 1;
			} else {
				emptySpace = 0;
			}

			Image scaledItemImage = features.get(i).getImage()
					.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			paintFeatureImage(g2d, scaledItemImage, 37, 45 + i * 40 + 5
					* emptySpace);
			paintFeatureName(g2d, features.get(i).toString(), 80, 67 + i * 40 + 5
					* emptySpace);

		}

	}

	@Override
	public void getNextText() {

	}
	

}
