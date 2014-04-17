package engine.world;

import java.util.List;

import engine.GameLooper;
import engine.collision.CollisionMatrix;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;

public class WalkAroundWorldLooper extends GameLooper {
	
	WalkAroundWorld myWorld;
	public WalkAroundWorldLooper(WalkAroundWorld currentWorld) {
		super(currentWorld);
		myWorld = (WalkAroundWorld) getWorld();
		getWorld().getPlayer().setSurroundingsChecker(new SurroundingChecker(myWorld));
	}

	@Override
	public World doLoop() {
		checkCollisions( myWorld.getCollisionMatrix());
		for (GridObject go : (myWorld.getGridObjectList())) {
			go.move();
			Door d = myWorld.getPlayer().isDoorEntered();
			if(d!=null){
				return d.getBuildingWorld();
			}
			if(go instanceof Enemy){
				if(((Enemy) go).isBattle()){
					return ((Enemy) go).getWorld();
				}	
			}

		}
		return null;
	}
	
	private void checkCollisions(CollisionMatrix cm) {
		for (int i = 0; i < ((WalkAroundWorld) getWorld()).getGridObjectList().size(); i++) {
			for (int j = 0; j < ((WalkAroundWorld) getWorld()).getGridObjectList().size(); j++) {
				if (myWorld.getGridObjectList().get(i).getBounds().intersects(
						myWorld.getGridObjectList().get(j).getBounds())) {
					if(cm!=null) {
						cm.getMatrix()[i][j].doCollision();
					}
				}
			}
		}
	}

}
