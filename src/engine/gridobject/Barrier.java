package engine.gridobject;

import java.util.List;

import util.Constants;

public class Barrier extends GridObject {
	
	public Barrier(String image, int numTilesWidth, int numTilesHeight) {
		super(image, numTilesWidth, numTilesHeight);
	}

	public Barrier(List<Object> list) {
		super((String) list.get(Constants.IMAGE_CONST), (int) ((Double) list.get(Constants.WIDTH_CONST)).intValue(), (int) ((Double) list.get(Constants.HEIGHT_CONST)).intValue());
	}
}
