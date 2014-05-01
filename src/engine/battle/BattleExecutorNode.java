package engine.battle;

import engine.dialogue.MatrixNode;

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
