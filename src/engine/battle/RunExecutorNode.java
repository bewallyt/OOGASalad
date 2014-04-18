package engine.battle;

import engine.dialogue.BattleExecutorNode;

public class RunExecutorNode extends BattleExecutorNode {
	
	Run myRun;
	
	public RunExecutorNode(Run run){
		myRun = run;
	}
	@Override
	public BattleExecutable getExecutor() {
		return myRun;
	}

}
