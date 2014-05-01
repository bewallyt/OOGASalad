package Data.world;


import util.Constants;
import authoring.gameObjects.HealerData;
import authoring.gameObjects.WorldData;
import engine.gridobject.GridObject;
import engine.gridobject.person.Healer;

/**
 * @author Sanmay Jain
 */
public class HealerTransformer implements Transformer {
	Healer myHealer;
	HealerData myHealerData;
	WorldUtil myWorldUtil;
	WorldData myWorldData;
	
	public HealerTransformer(GridObject g) {
		myHealer = (Healer) g;
		myHealerData = null;
		myWorldUtil = new WorldUtil();
	}
	
	@Override
	public void transform() {
		String spriteName = myWorldUtil.getSpriteName(myHealer.getImageFile());
		myHealerData = new HealerData(myHealer.getX()/Constants.TILE_SIZE,
				myHealer.getY()/Constants.TILE_SIZE,
				myHealer.getWidth()/Constants.TILE_SIZE,
				myHealer.getHeight()/Constants.TILE_SIZE,
				spriteName);
	}
	
	public HealerData getTransformedData() {
		return myHealerData;
	}
}
