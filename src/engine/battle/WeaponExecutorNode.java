package engine.battle;

import engine.dialogue.BattleExecutorNode;

public class WeaponExecutorNode extends BattleExecutorNode {

	private BattleExecutable myWeapon;
	public WeaponExecutorNode(BattleExecutable weapon){
		myWeapon = weapon;
	}
	@Override
	public BattleExecutable getExecutor() {
		return myWeapon;
	}
	@Override
	public String toString() {
		return ((Weapon) myWeapon).toString();
	}

}
