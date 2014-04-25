package engine.item;

import java.awt.Image;

import engine.battle.BattleExecutable;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public abstract class Item extends Pickupable implements BattleExecutable {

	private String myImage;
	public Item(String image, String name) {
		super(name, image);
		myImage=image;
	}

	public abstract void useItem();
	
	@Override
	public void pickUp(Player player){
		player.addItem(this);
	}
	@Override
	public Image getImage() {
		return new ScaledImage(150,150,myImage).scaleImage();
	}
	
//	public String toString() {
//		return super.getName();
//	}
	
}
