package engine.dialogue;

import engine.battle.BattleExecutable;

public class BattleExecutorNode implements MatrixNode{


	private BattleExecutable myExecutable;

	public BattleExecutorNode(BattleExecutable executable){
		myExecutable = executable;
	}

	public BattleExecutable getExecutor() {
		return myExecutable;
	}

	@Override
	public String toString() {
		return (myExecutable).toString();
	}
}
