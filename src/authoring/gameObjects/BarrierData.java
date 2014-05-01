package authoring.gameObjects;

import java.util.List;

import util.Constants;

/**
 * Class that holds information relevant to a barrier
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class BarrierData extends GridObjectData{
 
	public BarrierData(int x, int y, int width, int height, String image) {
		super(x,y,width,height,image, Constants.BARRIER);
		setHeight(height);
		setWidth(width);
	}

}
