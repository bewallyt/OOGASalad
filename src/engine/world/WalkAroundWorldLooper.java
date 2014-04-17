package engine.world;

import engine.GameLooper;
import engine.collision.CollisionMatrix;
import engine.dialogue.DialogueDisplayControl;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.NPC;

public class WalkAroundWorldLooper extends GameLooper {
	
	WalkAroundWorld myWorld;
	public WalkAroundWorldLooper(WalkAroundWorld currentWorld) {
		super(currentWorld);
		myWorld = (WalkAroundWorld) getWorld();
		getWorld().getPlayer().setSurroundingsChecker(new SurroundingChecker(myWorld));
		setDialogueDisplayControl();
	}

	private void setDialogueDisplayControl() {
		for (GridObject go : (myWorld.getGridObjectList())) {
			if (go instanceof NPC) {
				((NPC) go).setDialogueDisplayControl(new DialogueDisplayControl(myWorld));
			}
		}
		
	}

	@Override
	public World doLoop() {
		checkCollisions( myWorld.getCollisionMatrix());
		for (GridObject go : (myWorld.getGridObjectList())) {
			go.move();
			Door d = myWorld.getPlayer().isDoorEntered();
			if(d!=null){
				return d.getWorld();
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
