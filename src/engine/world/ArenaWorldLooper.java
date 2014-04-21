package engine.world;

import engine.battle.BattleManager;
import engine.dialogue.DialogueDisplayControl;
import engine.state.BattleState;

public class ArenaWorldLooper extends GameLooper {

	ArenaWorld myWorld;
	BattleManager myBattleManager;
	public ArenaWorldLooper(ArenaWorld currentWorld) {
		super(currentWorld);
		//myWorld.getEnemy().setWasBattled();
		myWorld = (ArenaWorld) getWorld();
		myWorld.getPlayer().setDialogueDisplayControl(new DialogueDisplayControl(currentWorld));
		myBattleManager = new BattleManager(myWorld.getPlayer(),myWorld.getEnemy());
		myWorld.getPlayer().setInteractionBox(myBattleManager);
		myWorld.getPlayer().setState(new BattleState(myBattleManager));
	}

	@Override
	public World doLoop() {
		System.out.println(myBattleManager.getCurrentState());
		if(myBattleManager.getCurrentState()==BattleManager.EXITWON){
			System.out.println("go back");
			myWorld.getEnemy().setWasBattled();
			return myWorld.getPrevWorld();
		}	
		if(myBattleManager.didRun()){
			myWorld.getEnemy().setWasBattled();
			return myWorld.getPrevWorld();
		}
		if(myBattleManager.getCurrentState()==BattleManager.EXITLOST){
			
			return myWorld.getPrevWorld();
		}
		return null;
//		System.out.println("Conversation Mode");
//		ConversationManager conversation = new ConversationManager(myPlayer, this, myResponseNode);
//		myWorld.getPlayer().setState(new DialogueState(conversation));
//		super.setInteractionBox(conversation);
	}

}
