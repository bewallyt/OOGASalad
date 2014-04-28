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
		myBattleManager = new BattleManager(myWorld.getPlayer(),myWorld.getEnemy(),myWorld.getLabels());
		myWorld.getPlayer().setInteractionBox(myBattleManager);
		myWorld.getPlayer().setState(new BattleState(myBattleManager));
	}

	@Override
	public World doLoop() {
		myWorld.setPlayerImage(myBattleManager.getCurrentPlayerBattleImage());
		myWorld.setEnemyImage(myBattleManager.getCurrentEnemyBattleImage());
		if(myBattleManager.getCurrentState().equals("ExitWon")){
			myBattleManager.setCurrentState("TopLevel");
			System.out.println("go back");
			if(myWorld.getEnemy().isRandom()){
				myWorld.getEnemy().getStatsMap().get("health").setToMax();
			}
			else{
				myWorld.getEnemy().setWasBattled();
			} 
			return myWorld.getPrevWorld();
		}	
		if(myBattleManager.getCurrentState().equals("Ran")){
			myBattleManager.setCurrentState("TopLevel");
			myWorld.getEnemy().getStatsMap().get("health").setToMax();
			return myWorld.getPrevWorld();
		}
		if(myBattleManager.getCurrentState().equals("ExitLost")){
			myBattleManager.setCurrentState("TopLevel");
			return myWorld.getPrevWorld();
		}
		return null;
		//		System.out.println("Conversation Mode");
		//		ConversationManager conversation = new ConversationManager(myPlayer, this, myResponseNode);
		//		myWorld.getPlayer().setState(new DialogueState(conversation));
		//		super.setInteractionBox(conversation);
	}

}
