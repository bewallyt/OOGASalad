package engine.dialogue;

import engine.gridobject.person.Player;

public class UserQueryNode implements MatrixNode {

		private Player myPlayer;
		private String myItemName;
		private String myDialogue;
		private NPCResponseNode myNPCResponseNode;
		
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
		
		public String getString() {
			return myDialogue;
		}

		public NPCResponseNode getMyNPCResponseNode() {
			return myNPCResponseNode;
		}
		
		
		
}
