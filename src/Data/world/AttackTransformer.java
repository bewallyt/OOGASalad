package Data.world;

import authoring.gameObjects.AttacksData;
import engine.battle.Attack;
import engine.battle.Effect;

/**
 * @author Sanmay Jain
 */
public class AttackTransformer implements Transformer {
	Attack myAttack;
	AttacksData myAttacksData;

	public AttackTransformer(Attack a) {
		myAttack = a;
		myAttacksData = null;
	}

	@Override
	public void transform() {
		if (myAttack == null) {
			return;
		}
		String stats = "";
		int changeAmount = 0;
		boolean affectsSelf = false;
		Effect effect = myAttack.getEffect();
		if (effect != null) {
			stats = effect.getEffectStatistic();
			changeAmount = effect.getAmountToChange();
			affectsSelf = effect.getAffectsSelf();
		}
		myAttacksData = new AttacksData(myAttack.toString(), myAttack.getSpeed().getValue(),
				myAttack.getDamage().getValue(),
				stats, changeAmount, affectsSelf);		

	}
	
	public AttacksData getTransformedData() {
		return myAttacksData;
	}
}
