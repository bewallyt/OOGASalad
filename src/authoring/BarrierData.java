package authoring;

import java.util.List;

import util.Constants;

public class BarrierData extends GridObjectData{
 
	public BarrierData(int x, int y, int width, int height, String image) {
		super(x,y,width,height,image, Constants.BARRIER);
		setHeight(height);
		setWidth(width);
	}

}
