package Data.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Constants;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.ShopkeeperData;
import authoring.gameObjects.WorldData;
import engine.gridobject.GridObject;
import engine.gridobject.person.ShopKeeper;
import engine.item.Item;

public class ShopkeeperTransformer  implements Transformer {
	ShopKeeper myShopkeeper;
	ShopkeeperData myShopkeeperData;
	WorldUtil myWorldUtil;
	WorldData myWorldData;
	
	public ShopkeeperTransformer(GridObject g) {
		myShopkeeper = (ShopKeeper) g;
		myShopkeeperData = null;
		myWorldUtil = new WorldUtil();
	}
	
	@Override
	public void transform() {
		String[] sAnimImages = myShopkeeper.getAnimImages();	
		Map<String,Integer> attributeValues1 = new HashMap<String,Integer>();
		for (String key :  myShopkeeper.getStatsMap().keySet()) {
			attributeValues1.put(key, myShopkeeper.getStatsMap().get(key).getValue());
		}
		
		Set<Item> items =  myShopkeeper.getItemSet();
		List<String> itemNames = myWorldUtil.getItemNamesList(items);
		
		String spriteName = myWorldUtil.getSpriteName( myShopkeeper.getImageFile());
		
		myShopkeeperData = new ShopkeeperData(myShopkeeper.getX()/Constants.TILE_SIZE,
				myShopkeeper.getY()/Constants.TILE_SIZE,
				myShopkeeper.getWidth()/Constants.TILE_SIZE,
				myShopkeeper.getHeight()/Constants.TILE_SIZE,
				spriteName,
				itemNames);
		
		for (Item i : items) {
			ItemTransformer itemTrans = new ItemTransformer(i);
			itemTrans.transform();
			ItemData itemData = itemTrans.getTransformedData();
			myWorldData.saveItem(itemData.getItemName(), itemData);			
		}				
	}
	
	public ShopkeeperData getTransformedData() {
		return myShopkeeperData;
	}
}
