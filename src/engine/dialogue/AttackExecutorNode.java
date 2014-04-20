package engine.dialogue;

import engine.battle.Attack;
import engine.battle.BattleExecutable;

public class AttackExecutorNode extends BattleExecutorNode {

	private Attack myAttack;

	public AttackExecutorNode(Attack attack){
		myAttack = attack;
	}

	@Override
	public BattleExecutable getExecutor() {
		return myAttack;
	}

	@Override
	public String getString() {
		return myAttack.getName();
	}




}
