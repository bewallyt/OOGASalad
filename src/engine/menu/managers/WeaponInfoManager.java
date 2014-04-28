package engine.menu.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class WeaponInfoManager implements InteractionBox {
	
	private Player myPlayer;
	private Image myWeaponImage;
	private String myWeaponName;
	private ArrayList<Integer> myWeaponStats;
	private ArrayList<Integer> myAttackDamageSpeed;
	private ArrayList<String> myAttackNameEffect;
	

	public WeaponInfoManager(Player p, Image img, String wn, List<Integer> ws, List<Integer> ads, List<String> ane) {
		myPlayer            = p;
		myWeaponImage       = img;
		myWeaponName        = wn;
		myWeaponStats       = new ArrayList<Integer>(ws);
		myAttackDamageSpeed = new ArrayList<Integer>(ads);
		myAttackNameEffect  = new ArrayList<String>(ane);
		
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int xOffset,
			int yOffset) {
		paintInfoBox(g2d, "ImageFiles/PokemonBox.png", -50, -50, 600, 600);
		
	}
	
	private void paintInfoBox(Graphics g2d, String imgPath, int x, int y,
			int width, int height) {
		Image img = new ScaledImage(width, height, imgPath).scaleImage();
		g2d.drawImage(img, x, y, null);
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
