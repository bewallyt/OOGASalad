package engine.battle;

import engine.dialogue.BattleExecutorNode;
import engine.item.Item;

public class BagExecutorNode extends BattleExecutorNode {

	Item myItem;
	public BagExecutorNode(Item item) {
		myItem = item;
	}
	@Override
	public BattleExecutable getExecutor() {
		return myItem;
	}
	@Override
	public String getString() {
		return myItem.getName();
	}

}
