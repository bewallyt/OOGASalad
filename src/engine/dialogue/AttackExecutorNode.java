package engine.dialogue;

import engine.battle.Attack;
import engine.battle.BattleExecutable;

public class AttackExecutorNode extends BattleExecutorNode {

	private BattleExecutable myAttack;

	public AttackExecutorNode(BattleExecutable attack){
		myAttack = attack;
	}

	@Override
	public BattleExecutable getExecutor() {
		return myAttack;
	}

	@Override
	public String toString() {
		return ((Attack) myAttack).toString();
	}
}
