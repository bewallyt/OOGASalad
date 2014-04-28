package engine.gridobject.person;

import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Constants;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;
import engine.item.Item;

public class ShopKeeper extends NPC {
	Set<Item> myItemSet;

	/**
	 * Instantiates a new shop keeper.
	 *
	 * @param animImages the anim images
	 * @param name the name
	 * @param speed the speed
	 * @param numTilesWidth the num tiles width
	 * @param numTilesHeight the num tiles height
	 * @param movementType the movement type
	 * @param player the player
	 * @param itemSet the item set
	 */
	public ShopKeeper(String[] animImages, String name, double speed,
			int numTilesWidth, int numTilesHeight, int movementType,
			Player player, Set<Item> itemSet) {
		super(animImages, name, speed, numTilesWidth, numTilesHeight, movementType,
				player);
		myItemSet=itemSet;
		makeDialogue();

	}
	
	public ShopKeeper(List<Object> list) {
		super((String[]) ((List<String>) list.get(Constants.IMAGE_CONST)).toArray(new String[12]),
				(String) list.get(Constants.NAME_CONST),
				1, 
				(int) ((Double) list.get(Constants.WIDTH_CONST)).intValue(), 
				(int) ((Double) list.get(Constants.HEIGHT_CONST)).intValue(), 
				1, 
				(Player) list.get(Constants.SHOP_PLAYER_CONST));
		myItemSet=itemSet;
		makeDialogue();
	}

	/**
	 * Make dialogue.
	 */
	private void makeDialogue(){
		NPCResponseNode n = new NPCResponseNode("What do you want to purchase?", null);
		
		for(Item item : myItemSet){
			NPCResponseNode n0 = new NPCResponseNode("You purchased a " + item.toString(), item);
			UserQueryNode q0 = new UserQueryNode(getPlayer(), null, item.toString(), n0);
			n.addResponseNode(q0);
			
		}
		this.setResponseNode(n);
	}


}
