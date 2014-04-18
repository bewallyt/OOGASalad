package engine.battle;

import engine.dialogue.BattleExecutorNode;

public class WeaponExecutorNode extends BattleExecutorNode {

	private Weapon myWeapon;
	public WeaponExecutorNode(Weapon weapon){
		myWeapon = weapon;
	}
	@Override
	public BattleExecutable getExecutor() {
		return myWeapon;
	}

}
