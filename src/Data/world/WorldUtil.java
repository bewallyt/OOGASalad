package Data.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import engine.item.Item;
import engine.item.Weapon;

public class WorldUtil {
	public WorldUtil() {
		
	}
	
	/**
	 * 
	 * @param weapons Weapon object list
	 * @return Returns list of weapon names
	 */
	public String[] getWeaponNames(List<Weapon> weapons) {
		if (weapons == null) {
			return new String[0];
		}
		String [] wepNames = new String[weapons.size()];
		int counter = 0;
		for (Weapon wep : weapons) {
			if (wep == null) {
				continue;
			}
			String wepName = wep.toString();
			wepNames[counter] = wepName;
			counter++;
		}
		return wepNames;		
	}
	
	/**
	 * 
	 * @param items Item object list
	 * @return Returns list of item names
	 */
	public String[] getItemNames(List<Item> items) {
		if (items == null) {
			return new String[0];
		}
		String [] itemNames = new String[items.size()];
		int counter = 0;
		for (Item item : items) {
			if (item == null) {
				continue;
			}
			String itemName = item.toString();
			itemNames[counter] = itemName;
			counter++;
		}
		return itemNames;		
	}	
	
	/**
	 * 
	 * @param items Item object list
	 * @return Returns list of item names
	 */
	public List<String> getItemNamesList(Set<Item> items) {		
		List<String> itemNames = new ArrayList<String>();
		if (items == null) {
			return itemNames;
		}
		for (Item item : items) {
			if (item == null) {
				continue;
			}
			String itemName = item.toString();
			itemNames.add(itemName);
		}
		return itemNames;		
	}	
	
	/**
	 * 
	 * @param imagePath location of images
	 * @return Returns sprite associated with image path
	 */
	public String getSpriteName(String imagePath) {
		String spriteName = "Zelda";
		String prefix = "PlayerImages/";
		if (imagePath == null) {
			return spriteName;
		} 
		if (imagePath.startsWith(prefix)) {
			int index = imagePath.indexOf('/');
			if (index != -1) {
				if (imagePath.length() > index+1) {
					String temp1 = imagePath.substring(index+1);	
					int lastIndex = temp1.lastIndexOf('/');
					if (lastIndex != -1) {
						String temp2 = temp1.substring(0, lastIndex);
						spriteName = temp2;
					}
				}
			}			
		}
		return spriteName;
	}	
}
