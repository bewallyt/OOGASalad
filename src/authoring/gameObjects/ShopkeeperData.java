package authoring.gameObjects;

import java.util.List;

import util.Constants;
import authoring.SpriteImageChooser;

public class ShopkeeperData extends GridObjectData{
	private List<String> myItems;

	public ShopkeeperData(int x, int y, int width, int height, String image,
			List<String> items) {
		super(x, y, width, height, image, items, Constants.SHOPKEEPER);
		myItems = items;
		SpriteImageChooser imageChoose = new SpriteImageChooser();
		setHeight(height);
		setWidth(width);
	}

	/**
	 * Returns the NPCResponseData of this NPC
	 */
	public List<String> getItems() {
		return myItems;
	}
}
