package authoring.gameObjects;

import util.Constants;

/**
 * Class that holds data relevant to an encounter
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class EncounterData extends GridObjectData{
	public EncounterData(int x, int y, int width, int height, String image){
		super(x, y, width, height, image, Constants.DOOR);
	}
}
