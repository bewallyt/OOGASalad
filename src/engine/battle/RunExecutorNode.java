package engine.battle;

import engine.dialogue.BattleExecutorNode;

public class RunExecutorNode extends BattleExecutorNode {
	
	BattleExecutable myRun;
	
	public RunExecutorNode(BattleExecutable run){
		myRun = run;
	}
	@Override
	public BattleExecutable getExecutor() {
		return myRun;
	}
	@Override
	public String toString() {
		return "You Ran Away";
	}

}
