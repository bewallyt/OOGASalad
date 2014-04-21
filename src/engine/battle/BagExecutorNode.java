package engine.battle;

import engine.dialogue.BattleExecutorNode;
import engine.item.Item;

public class BagExecutorNode extends BattleExecutorNode {

	BattleExecutable myItem;
	public BagExecutorNode(BattleExecutable item) {
		myItem = item;
	}
	@Override
	public BattleExecutable getExecutor() {
		return myItem;
	}
	@Override
	public String toString() {
		return myItem.toString();
	}

}
