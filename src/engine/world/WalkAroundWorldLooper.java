package engine.world;

import engine.collision.CollisionMatrix;
import engine.dialogue.AbstractManager;
import engine.dialogue.ConversationManager;
import engine.dialogue.DialogueDisplayControl;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.NotificationManager;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.state.DialogueState;
import engine.state.SaveState;
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
				
		} else if(myWorld.getPlayer().getState() instanceof SaveState){
			//	System.out.println("in instance of SaveState");
			SaveState saveState = (SaveState) myWorld.getPlayer().getState();
			if (saveState.isSavingState()) {
				saveState.setSavingState(false);
			//	System.out.println("saveWorld");
			    /// save world data 
			}
<<<<<<< HEAD

		}
=======
		}
//			if(myWorld.getPlayer().getWeaponList().size()>2){
//				NPCResponseNode n = new NPCResponseNode(myWorld.getPlayer(), "You have too many items");
////				System.out.println("Item found alert");
//				AbstractManager conversation = new ConversationManager(myWorld.getPlayer(), myWorld.getPlayer(), n);
//				myWorld.getPlayer().setState(new DialogueState(conversation));
//				myWorld.getPlayer().setInteractionBox(conversation);
//			}
>>>>>>> 13c0e33d77834a4fe8c0a6d583f1eb0dc4ec75d5
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
