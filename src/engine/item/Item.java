package engine.item;

import java.awt.Image;

import util.Constants;
import engine.battle.BattleExecutable;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public abstract class Item extends Pickupable implements BattleExecutable {

	private String myImage;
	private int myPrice;
	public Item(String image, String name) {
		super(name, image);
		myImage=image;
		myPrice=0;
	}

	public abstract void useItem();
	
	@Override
	public void pickUp(Player player){
		player.addItem(this);
	}
	@Override
	public Image getImage() {
		return new ScaledImage(Constants.BATTLE_IMAGE_SIZE,Constants.BATTLE_IMAGE_SIZE,myImage).scaleImage();
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(int price){
		myPrice=price;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice(){
		return myPrice;
	}
	
}
