package Data.world;

import java.util.List;

import authoring.UserQueryNodeData;
import authoring.gameObjects.NPCResponseNodeData;
import authoring.gameObjects.WorldData;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;

public class NPCResponseNodeTransformer implements Transformer {
	NPCResponseNode myNPCResponseNode;
	NPCResponseNodeData myNPCResponseNodeData;
	WorldUtil myWorldUtil;
	WorldData myWorldData;
	
	public NPCResponseNodeTransformer(NPCResponseNode npcResponseNode) {
		myNPCResponseNode =  npcResponseNode;
		myNPCResponseNodeData = null;
	}
	
	@Override
	public void transform() {
		myNPCResponseNodeData = new NPCResponseNodeData(myNPCResponseNode.getDialogue());
		String item = null;
		if (myNPCResponseNode.getItem() != null) {
			item = myNPCResponseNode.getItem().toString();
		}
		 myNPCResponseNodeData.setItem(item);
		List<UserQueryNode> usqNodesList = myNPCResponseNode.getUserQueryNodes();
		for (UserQueryNode usqNode : usqNodesList) {
			UserQueryNodeData usqNodeData = new UserQueryNodeData();
			String itemName = null;
			if (usqNode.hasItem()) {
				itemName = usqNode.getItemName();
			} 
			usqNodeData.setItem(itemName);
			usqNodeData.setString(usqNode.toString());
			NPCResponseNode rNode = usqNode.getMyNPCResponseNode();
			NPCResponseNodeData rNodeData = new NPCResponseNodeData(rNode.getDialogue());
	        String itName = null;
	        if (rNode.getItem() != null) {
	        	itName = rNode.getItem().toString();
	        }
			rNodeData.setItem(itName);
			List<UserQueryNode> usqNodesList1 = rNode.getUserQueryNodes();
			for (UserQueryNode uNode : usqNodesList1) {
				UserQueryNodeData uNodeData = new UserQueryNodeData();
				String iName = null;
				if (uNode.hasItem()) {
					iName = uNode.getItemName();
				} 
				uNodeData.setItem(iName);	
				uNodeData.setString(uNode.toString());
				rNodeData.addChild(uNodeData);
			}
			usqNodeData.setChild(rNodeData);
			 myNPCResponseNodeData.addChild(usqNodeData);
		}				
	}
	
	public NPCResponseNodeData getTransformedData() {
		return myNPCResponseNodeData;
	}

}
