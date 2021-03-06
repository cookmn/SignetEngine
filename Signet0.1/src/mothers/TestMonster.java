package mothers;

import java.util.HashMap;
import java.util.Scanner;

import sampleCombatActions.DefaultDefense;
import sampleCombatActions.SimpleAttack;
import misc.DeathException;
import combat.AttackAction;
import combat.Combat;
import combat.CombatAction;
import combat.DefenseAction;
import creatures.Creature;

public class TestMonster extends Creature {
	
	

	public TestMonster() {
		super("generic-saurace-rex", getGenericMonsterStats(), new HashMap<String, Integer>());
	}

	@Override
	public CombatAction selectNormalCombatAction(Scanner inputScanner) {
		// TODO Auto-generated method stub
		
		Combat combat = Combat.getInstance();
		AttackAction atc = new SimpleAttack(this, getInventory().getWeapon(), combat.getPlayer());
		DefenseAction def = new DefaultDefense(this);
		return new CombatAction(this, atc, def);
	}

	@Override
	public AttackAction selectExtraAttack(Creature opponent, int advantage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void die() throws DeathException {
		throw new DeathException("You killed the generic-saurace-rex!!", false);
		
	}
	private static HashMap<String, Integer> getGenericMonsterStats(){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("str", 6);	// strength
		map.put("agl", 6);	// agility
		map.put("end", 6);	// endurance
		map.put("dex", 6);	// dexterity
		map.put("cha", 6);	// Charisma
		map.put("anl", 6);	// analysis
		map.put("per", 6);	// perception
		map.put("wil", 6);	// willpower
		map.put("int", 6);	// intuition
		map.put("rec", 6);	// reaction
		return map;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}

	@Override
	public String handleDeath(DeathException e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleKills(DeathException e) {
		// TODO Auto-generated method stub
		return null;
	}
}
