package authoring.gameObjects;

import util.Constants;
/**
 * Class that holds data relevant to a Healer
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class HealerData extends GridObjectData {
	public HealerData(int x, int y, int width, int height, String image) {
		super(x,y,width,height,image, Constants.HEALER);
		setHeight(height);
		setWidth(width);
	}
}
