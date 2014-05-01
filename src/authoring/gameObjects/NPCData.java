package authoring.gameObjects;

import java.util.List;

import authoring.SpriteImageChooser;

import util.Constants;

/**
 * Class that holds all data for a given NPC
 * 
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 * 
 */
public class NPCData extends GridObjectData {
	private NPCResponseNodeData myRoot;
	private String[] myImages;

	public NPCData(int x, int y, int width, int height, String image,
			NPCResponseNodeData root) {
		super(x, y, width, height, image, root, Constants.NPC);
		myRoot = root;
		SpriteImageChooser imageChoose = new SpriteImageChooser();
		myImages = imageChoose.getSpriteImages(image);
		setHeight(height);
		setWidth(width);
		System.out.println(image);
	}

	/**
	 * Returns the NPCResponseData of this NPC
	 */
	public NPCResponseNodeData getDialogue() {
		return myRoot;
	}

	/**
	 * Returns the images of this NPC
	 */
	public String[] getImages() {
		return myImages;
	}

}
