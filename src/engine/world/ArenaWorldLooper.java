package engine.world;

import engine.battle.BattleManager;
import engine.dialogue.DialogueDisplayControl;
import engine.state.BattleState;

public class ArenaWorldLooper extends GameLooper {

	ArenaWorld myWorld;
	BattleManager myBattleManager;
	public ArenaWorldLooper(ArenaWorld currentWorld) {
		super(currentWorld);
		myWorld = (ArenaWorld) getWorld();
		myWorld.getPlayer().setDialogueDisplayControl(new DialogueDisplayControl(currentWorld));
		myBattleManager = new BattleManager(myWorld.getPlayer(),myWorld.getEnemy());
		myWorld.getPlayer().setInteractionBox(myBattleManager);
		myWorld.getPlayer().setState(new BattleState(myBattleManager));
	}

	@Override
	public World doLoop() {
		if(myWorld.getEnemy().getStatsMap().get("health").getValue()==0){
			return myWorld.getPrevWorld();
		}	
		return null;
//		System.out.println("Conversation Mode");
//		ConversationManager conversation = new ConversationManager(myPlayer, this, myResponseNode);
//		myWorld.getPlayer().setState(new DialogueState(conversation));
//		super.setInteractionBox(conversation);
	}

}
