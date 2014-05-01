package Data.world;

import java.util.HashMap;
import java.util.Map;

import authoring.gameObjects.ItemData;
import engine.Statistic;
import engine.item.Item;
import engine.item.KeyItem;
import engine.item.StatBuffer;

public class ItemTransformer implements Transformer {
	Item myItem;
	ItemData myItemData;
	
	public ItemTransformer(Item item) {
		myItem = item;
		myItemData = null;
	}
	
	@Override
	public void transform() {
		if (myItem instanceof KeyItem ) {
			myItemData = new ItemData(myItem.toString(), myItem.getImageName(), myItem.getPrice(), "KeyItem");
			myItemData.setItemImage(myItem.getImageName());
		} else if (myItem instanceof StatBuffer) {
			StatBuffer stbuffer = (StatBuffer)myItem;
			Statistic stats = stbuffer.getStatistic();
			Map<String, Integer> values = new HashMap<String, Integer>();
			if (stats != null) {			
				values.put(stats.getName(), stats.getValue());
			} else {
			}
			myItemData = new ItemData(myItem.toString(),myItem.getImageName(), values, myItem.getPrice(), "StatBuffer");
		} else {

		}
	}
	
	public ItemData getTransformedData() {
		return myItemData;
	}

}
