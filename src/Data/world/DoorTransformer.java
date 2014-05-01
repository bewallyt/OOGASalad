package Data.world;

import engine.gridobject.Door;
import engine.gridobject.GridObject;
import util.Constants;
import authoring.gameObjects.DoorData;

/**
 * @author Sanmay Jain
 *
 */
public class DoorTransformer implements Transformer {
	Door myDoor;
	DoorData myDoorData;

	public DoorTransformer(GridObject g) {
		myDoor = (Door) g;
	}

	@Override
	public void transform() {
			myDoorData = new DoorData(myDoor.getX()/Constants.TILE_SIZE, 
					myDoor.getY()/Constants.TILE_SIZE,
					myDoor.getWidth()/Constants.TILE_SIZE,
					myDoor.getHeight()/Constants.TILE_SIZE,
					myDoor.getImageFile(), 
					myDoor.getX()/Constants.TILE_SIZE,
					myDoor.getY()/Constants.TILE_SIZE,
					myDoor.getToMap()
	                );

	}
	
	public DoorData getTransformedData() {
		return myDoorData;
	}

}
