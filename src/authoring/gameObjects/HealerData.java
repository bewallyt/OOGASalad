package authoring.gameObjects;

import util.Constants;

public class HealerData extends GridObjectData {
	public HealerData(int x, int y, int width, int height, String image) {
		super(x,y,width,height,image, Constants.BARRIER);
		setHeight(height);
		setWidth(width);
	}
}
