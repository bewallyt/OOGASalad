package engine.world;

import engine.collision.CollisionMatrix;
import engine.dialogue.DialogueDisplayControl;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Person;
import engine.state.WalkAroundState;


public class WalkAroundWorldLooper extends GameLooper {

	WalkAroundWorld myWorld;

	public WalkAroundWorldLooper(WalkAroundWorld currentWorld) {
		super(currentWorld);
		myWorld = (WalkAroundWorld) getWorld();
		getWorld().getPlayer().setSurroundingsChecker(new SurroundingChecker(myWorld));
		getWorld().getPlayer().setState(new WalkAroundState(getWorld().getPlayer()));
		setDialogueDisplayControl();
		setMenuControl();
	}

	private void setDialogueDisplayControl() {
		for (GridObject go : (myWorld.getGridObjectList())) {
			//if (go instanceof Person) {
				go.setDialogueDisplayControl(new DialogueDisplayControl(myWorld));
			//}
		}
	}

	private void setMenuControl(){

		//		myWorld.setMenuDisplayer(new MenuManager());
		//myWorld.getPlayer().setMenuControl(new MenuControl(myWorld));
	}

	@Override
	public World doLoop() {
		if(myWorld.getPlayer().getState() instanceof WalkAroundState){
			checkCollisions( myWorld.getCollisionMatrix());
			for (GridObject go : (myWorld.getGridObjectList())) {
				go.move();
				Door d = myWorld.getPlayer().isDoorEntered();
				if(d!=null){
					return d.getWorld();
				}
				if(go instanceof Enemy){
					if(((Enemy) go).isBattle() && myWorld.getPlayer().getState() instanceof WalkAroundState){
						if(!((Enemy) go).getWasBattled()){
							return ((Enemy) go).getWorld();
						}
					}	
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
