package Data.world;

import java.util.ArrayList;
import java.util.List;

import authoring.gameObjects.AttacksData;
import authoring.gameObjects.WeaponData;
import engine.battle.Attack;
import engine.item.Weapon;

public class WeaponTransformer implements Transformer {
	Weapon myWeapon;
	WeaponData myWeaponData;
	
	public WeaponTransformer(Weapon w) {
		myWeapon = w;
		myWeaponData = null;
	}
	
	@Override
	public void transform() {
		List<Attack> attackList = myWeapon.getAttackList();
		List<AttacksData> attacksDataList = new ArrayList<AttacksData>();
		for (Attack a : attackList) {
			if (a == null) {
				continue;
			}
			AttackTransformer wt = new AttackTransformer(a);
			wt.transform();
			AttacksData attacksData = wt.getTransformedData();
			if (attacksData != null) {
				attacksDataList.add(attacksData);
			}
		}
	}
	
	public WeaponData getTransformedData() {
		return myWeaponData;
	}
}
