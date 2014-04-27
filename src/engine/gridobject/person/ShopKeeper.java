package engine.gridobject.person;

import java.util.Map;

import engine.item.Item;

public class ShopKeeper extends NPC {

	public ShopKeeper(String[] animImages, String name, double speed,
			int numTilesWidth, int numTilesHeight, int movementType,
			Player player, Map<Item,Integer> itemMap) {
		super(animImages, name, speed, numTilesWidth, numTilesHeight, movementType,
				player);
		
	}



}
