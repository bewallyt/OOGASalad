package Data.world;

import util.Constants;
import authoring.gameObjects.NPCData;
import authoring.gameObjects.NPCResponseNodeData;
import authoring.gameObjects.WorldData;
import engine.gridobject.GridObject;
import engine.gridobject.person.NPC;

/**
 * @author Sanmay Jain
 */
public class NPCTransformer implements Transformer {
	NPC myNPC;
	NPCData myNPCData;
	WorldUtil myWorldUtil;
	WorldData myWorldData;
	
	public NPCTransformer(GridObject g) {
		myNPC =  (NPC) g;
		myNPCData= null;
		myWorldUtil = new WorldUtil();
	}

	@Override
	public void transform() {
		String spriteName = myWorldUtil.getSpriteName(myNPC.getImageFile());
		NPCResponseNodeTransformer respTransformer = new NPCResponseNodeTransformer(myNPC.getResponseNode());
		respTransformer.transform();
		NPCResponseNodeData npcResponseNodeData = respTransformer.getTransformedData();

	    myNPCData = new NPCData(myNPC.getX()/Constants.TILE_SIZE,
	    		myNPC.getY()/Constants.TILE_SIZE,
	    		myNPC.getWidth()/Constants.TILE_SIZE,
	    		myNPC.getHeight()/Constants.TILE_SIZE,
				spriteName,
				npcResponseNodeData, 3);
		
	}
	
	public NPCData getTransformedData() {
		return myNPCData;
	}

}
