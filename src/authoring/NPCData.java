package authoring;

import java.util.List;

import util.Constants;

public class NPCData extends GridObjectData{
	private NPCResponseNode myRoot;
	private String[] myImages;
	
	public NPCData(int x, int y, int width, int height, String image, NPCResponseNode root) {
		super(x, y, width, height, image, root, Constants.NPC);
		myRoot = root;
		init();
		myImages=getMyImages(image);
		setHeight(height);
		setWidth(width);
	}
	
	public NPCResponseNode getDialogue(){
		return myRoot;
	}
	
	private String[] getMyImages(String image){
		return new String[] {Constants.GRIDOBJECTPATH+image, Constants.GRIDOBJECTPATH+image, Constants.GRIDOBJECTPATH+image, Constants.GRIDOBJECTPATH+image};
	}
}
