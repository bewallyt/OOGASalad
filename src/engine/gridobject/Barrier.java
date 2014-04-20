package engine.gridobject;

import java.util.List;

public class Barrier extends GridObject {
	
	public Barrier(String image, int numTilesWidth, int numTilesHeight) {
		super(image, numTilesWidth, numTilesHeight);
	}

	public Barrier(List<Object> list) {
		super((String) list.get(0), (int) list.get(2), (int) list.get(3));
	}
}
