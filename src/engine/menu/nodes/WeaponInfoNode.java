package engine.menu.nodes;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import engine.battle.Attack;
import engine.gridobject.person.Player;
import engine.item.Weapon;
import engine.menu.managers.MenuManager;
import engine.menu.managers.WeaponInfoManager;
import engine.menu.managers.WeaponManager;
import engine.state.WeaponInfoState;

public class WeaponInfoNode extends MenuNode {

	private WeaponInfoManager myWIM;
	private WeaponManager myWeaponManager;
	private Player myPlayer;
	private MenuManager myMenuManager;
	private int myCount;
	
	private Image weaponImage;
	private String weaponName;
	private List<Integer> weaponStats;
	private List<Integer> attackDamageSpeed;
	private List<String> attackNameEffect;
	

	public WeaponInfoNode(Player p, WeaponManager wm, MenuManager mm, int c) {
		myPlayer = p;
		myWeaponManager = wm;
		myMenuManager = mm;
		myCount = c;
		weaponStats = new ArrayList<Integer>();

	}

	@Override
	public void doAction() {
		getWeaponData();
		changeState();
		changeWorld();
	}

	@Override
	public void changeWorld() {
		myWIM = new WeaponInfoManager(myPlayer, weaponImage, weaponName, weaponStats);
		myWIM.extractAttackStats(attackDamageSpeed, attackNameEffect);
		myPlayer.setInteractionBox(myWIM);

	}

	@Override
	public void changeState() {
		myPlayer.setState(new WeaponInfoState(myPlayer, myWeaponManager,
				myMenuManager));

	}

	private void getWeaponData() {
		Weapon currentWeapon = myPlayer.getWeaponList().get(myCount);
		
		attackDamageSpeed = new ArrayList<Integer>();
		attackNameEffect = new ArrayList<String>();

		// (1) Name and Image
		weaponName = currentWeapon.toString();
		weaponImage = currentWeapon.getImage();

		// (2) Attack
		int currentDamage = currentWeapon.getDamage().getValue();
		int maxDamage = currentWeapon.getDamage().getMaxValue();

		// (3) Speed
		int currentSpeed = currentWeapon.getSpeed().getValue();
		int maxSpeed = currentWeapon.getSpeed().getMaxValue();
		
		weaponStats.add(currentDamage);
		weaponStats.add(maxDamage);
		weaponStats.add(currentSpeed);
		weaponStats.add(maxSpeed);

		// (4) Attacks
		List<Attack> attacks = currentWeapon.getAttackList();
		
		for (int i = 0; i < attacks.size(); i++) {
			String attackName   = attacks.get(i).toString();
			String attackEffect = attacks.get(i).getDataEffectMessage();
			int attackDamage    = attacks.get(i).getDamage().getValue();
			int attackSpeed     = attacks.get(i).getSpeed().getValue();
			attackDamageSpeed.add(attackDamage);
			attackDamageSpeed.add(attackSpeed);
			attackNameEffect.add(attackName);
			attackNameEffect.add(attackEffect);
		}

	}

	@Override
	public String getGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
