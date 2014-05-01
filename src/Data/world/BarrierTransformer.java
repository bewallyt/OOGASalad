package Data.world;

import engine.gridobject.Barrier;
import engine.gridobject.GridObject;
import util.Constants;
import authoring.gameObjects.BarrierData;

/**
 * @author Sanmay Jain
 *
 */
public class BarrierTransformer implements Transformer {
	Barrier myBarrier;
	BarrierData myBarrierData;

	public BarrierTransformer(GridObject g) {
		myBarrier = (Barrier) g;
	}

	@Override
	public void transform() {
		 myBarrierData = new BarrierData(myBarrier.getX()/Constants.TILE_SIZE,
				myBarrier.getY()/Constants.TILE_SIZE,
				myBarrier.getWidth()/Constants.TILE_SIZE,
				myBarrier.getHeight()/Constants.TILE_SIZE,
				myBarrier.getImageFile());	
	}
	
	public BarrierData getTransformedData() {
		return myBarrierData;
	}

}
