package engine.gridobject.person;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.gameObjects.ItemData;
import util.Constants;
import engine.Statistic;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;
import engine.item.Item;

public class ShopKeeper extends NPC {
	Set<Item> myItemSet;

	/**
	 * Instantiates a new shop keeper.
	 * 
	 * @param animImages
	 *            the anim images
	 * @param name
	 *            the name
	 * @param speed
	 *            the speed
	 * @param numTilesWidth
	 *            the num tiles width
	 * @param numTilesHeight
	 *            the num tiles height
	 * @param movementType
	 *            the movement type
	 * @param player
	 *            the player
	 * @param itemSet
	 *            the item set
	 */
	public ShopKeeper(String[] animImages, String name, double speed,
			int numTilesWidth, int numTilesHeight, int movementType,
			Player player, Set<Item> itemSet) {
		super(animImages, name, speed, numTilesWidth, numTilesHeight,
				movementType, player);
		myItemSet = itemSet;
		makeDialogue();
	}

	@SuppressWarnings("unchecked")
	public ShopKeeper(List<Object> list) {
		super(new String[]{"gridobject/FatBoy.png", "gridobject/FatBoy.png", "gridobject/FatBoy.png", "gridobject/FatBoy.png"}, (String) list
				.get(Constants.NAME_CONST), 1, (int) ((Double) list
				.get(Constants.WIDTH_CONST)).intValue(), (int) ((Double) list
				.get(Constants.HEIGHT_CONST)).intValue(), 3, (Player) list
				.get(Constants.SHOP_PLAYER_CONST));
		myItemSet = makeItemSet(
				(List<String>) list.get(Constants.SHOP_ITEMS_CONST),
				(Map<String, ItemData>) list.get(Constants.SHOP_ITEMSMAP_CONST));
		makeDialogue();
	}

	public Set<Item> makeItemSet(List<String> list, Map<String, ItemData> map) {
		Set<Item> itemset = new HashSet<Item>();
		for (String s : list) {
			ItemData i = map.get(s);
			Item myItem;
			if (("KeyItem").equals(i.getMyIdentity())) {
				myItem = (Item) Reflection.createInstance(
						"engine.item." + i.getMyIdentity(), i.getItemImage(),
						i.getItemName());
			} else {
				Map<String, Integer> valuesMap = i.getMyItemValues();
				String key = "health";
				Integer value = 10;
				Statistic stats = null;
				if ((valuesMap != null) && (valuesMap.size() > 0)) {
					for (String k : valuesMap.keySet()) {
						stats = new Statistic(k, valuesMap.get(k), 100);
						break;
					}
				} else {
					stats = new Statistic(key, value, 100);
				}				
				myItem = (Item) Reflection.createInstance(
						"engine.item." + i.getMyIdentity(), i.getItemImage(),
						i.getItemName(), stats, 10);
			}
			myItem.setPrice(i.getMyPrice());
			itemset.add(myItem);
		}
		return itemset;
	}

	/**
	 * Make dialogue.
	 */
	private void makeDialogue() {
		NPCResponseNode n = new NPCResponseNode(
				"What do you want to purchase?", null);

		for (Item item : myItemSet) {
			NPCResponseNode n0 = new NPCResponseNode("You purchased a "
					+ item.toString(), item);
			getPlayer().changeMoney(item.getPrice());
			UserQueryNode q0 = new UserQueryNode(getPlayer(), null,
					item.toString(), n0);
			n.addResponseNode(q0);
			

		}
		this.setResponseNode(n);
	}
	
	public Set<Item> getItemSet() {
		return myItemSet;
	}

}
