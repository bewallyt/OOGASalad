package engine.dialogue;

import engine.gridobject.person.Player;

/**
 * This is the other part of our conversation design. These nodes handle the different options that the 
 * Player can select if prompted to do so. These nodes can also be item seeking, meaning that the node is 
 * automatically taken in the conversation tree if the Player has a special keyItem that the NPC is seeking.
 *
 */
public class UserQueryNode implements MatrixNode {

		private Player myPlayer;
		private String myItemName;
		private String myDialogue;
		private NPCResponseNode myNPCResponseNode;
		
		/**
		 * Constructor for UQN, if you want it to be an item-seeking node, place the name of the item in the
		 * item string parameter. If not, then put a <code>null</code> there.
		 * @param p
		 * @param item Name of the item you will look for. If no item, put <code>null</code>
		 * @param dialogue dialogue associated with the node
		 * @param node the child node, a NPCResponse node
		 */
		public UserQueryNode(Player p, String item, String dialogue, NPCResponseNode node) {
			myPlayer = p;
			myItemName = item;
			myDialogue = dialogue;
			myNPCResponseNode = node;
		}
		
		public void setNPCResponseNode(NPCResponseNode node) {
			myNPCResponseNode = node;
		}
		
		public boolean hasItem() {
			return myPlayer.hasItem(myItemName);
		}
		
		public String toString() {
			return myDialogue;
		}

		public NPCResponseNode getMyNPCResponseNode() {
			return myNPCResponseNode;
		}
		
		public String getItemName() {
			return myItemName;
		}
		
}
