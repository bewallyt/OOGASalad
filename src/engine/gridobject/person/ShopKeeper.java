package engine.gridobject.person;

import java.util.List;

public class ShopKeeper extends NPC {

	public ShopKeeper(String[] animImages, double speed, int numTilesWidth,
			int numTilesHeight, int movementType, Player player) {
		super(animImages, speed, numTilesWidth, numTilesHeight, movementType,
				player);
		// TODO Auto-generated constructor stub
	}

	public ShopKeeper(List<Object> list) {
		super((String[]) list.get(1), (int) list.get(4), (int) list.get(2),
				(int) list.get(3), (int) list.get(5), (Player) list.get(6));
	}

}
