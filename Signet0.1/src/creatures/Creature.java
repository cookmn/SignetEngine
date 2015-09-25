package creatures;

import health.Body;
import inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import main.GameRunner;
import misc.DamageType;
import misc.DeathException;
import misc.DiceRoller;

public abstract class Creature {
	/* 
	 * PHYSICAL: str (strength), agl (agilit), end(endurance),
	 * SKILL: dex(dexterity), cha(charisma), anl(analysis),
	 * MENTAL: per(perception), wil(willpower), int(intuition), rec(rection)
	 */
	public static final String[] ABILITIES = {"str", "agl", "end", "dex", "cha", "anl", "per", "wil", "int", "rec"};
	
	
	public String name;
	
	private Body body;
	private Inventory inv;
	
	public HashMap<String,Integer> stats_base, stats_adjusted;
	public HashMap<String,Integer> damageMultipliers;
	public HashMap<String, Skill> skills;
	
	public Creature(String creatureName, HashMap<String,Integer> baseStats, HashMap<String,Integer> damageMultipliers){
		this.name = creatureName;
		inv = null;
		body = null;
		this.damageMultipliers = damageMultipliers;
		stats_base = baseStats;
		this.name = creatureName;
		stats_adjusted = stats_base; // TODO make this a deep copy, then make it actually modify stats.
		skills = startingSkills();
		inv = new Inventory();
		body = new Body(this);
	}
	
	// Selects the combat action that will be used in a fight. PC's prompt for user input, NPC's (Monster) use algorithms.
	public abstract void die() throws DeathException;
	public abstract boolean isPlayer();
	public abstract String handleDeath(DeathException e);
	public abstract String handleKills(DeathException e);
	
	public HashMap<String, Skill> startingSkills(){
		HashMap<String, Skill> skills = new HashMap<String, Skill>();
		return skills;
	}
	
	public int rollInititative(){
		int init = stats_adjusted.get("rec") + stats_adjusted.get("agl");
		Random r = new Random();
		for (int i = 0; i < 3; i++){
			init += r.nextInt(6);
		}		
		return init;
	}
	
	public HashMap<String, Skill> getSkills(){
		return skills;
	}
	
	public Inventory getInventory(){
		return inv;
	}
	public int getWeaponType(){
		//TODO get from weapon
		return DamageType.SLASHING;
	}
	
	public int getWeaponMight(){
		int might = 1; // TODO use weapon stats.
		int[] strengthTest = DiceRoller.makeRoll(stats_adjusted.get("str"));
		// TODO account for glitching.
		might += strengthTest[0];
		return might;
	}
	
	public void recieveWound(int[][] attackData, int damageType) throws DeathException{
		body.recieveWounds(attackData, damageType);
	}
	
	private int defendFromAttackFlatfooted(){
		return 0;
	}
	
	public Inventory initializeInventory(){
		inv = new Inventory();
		return inv;
	}
	/**
	 * gets the base healing factor. Averages to 1 with an average endurance of 6.
	 * @return
	 */
	private double getBaseHealingFactor(){
		return .4 + ( stats_adjusted.get("end") * 0.1 );
	}
	
	public Body initializeBody(){
		body = new Body(this);
		return body;
	}	
		
	public int setUpCombatRound(Creature enemy){
		// TODO implement
		return Integer.MIN_VALUE;
	}
	public boolean endCombatRound(){
		// TODO implement
		return false;
	}
	public void endCombat(){
		// TODO implement
	}
	public void refreshStats(){
		// TODO implement
	}
	public HashMap<String, Integer> getStats(){
		return stats_adjusted;
	}
	public void setStat(String key, int value){
		stats_base.put(key, value);
	}
	private void modifySingleStat(String key, int modifier){
		int starting = stats_base.get(key);
		stats_adjusted.put(key, starting + modifier);
	}
	private void adjustSingleStat(String key, int modifier){
		int starting = stats_adjusted.get(key);
		stats_adjusted.put(key, starting + modifier);
	}
	private void calculateStatMods(){
		// TODO implement
	}
	public void refreshCombatStats(){
		// TODO implement
	}
	public void passTime(int timePassed, double healingFactor, boolean resting) throws DeathException{
		body.passTime(timePassed, healingFactor, resting);
		//TODO implement
	}
	public void bedRest(int timePassed){
		// TODO implement
	}
	public void wildernessRest(int timePassed){
		// TODO implement
	}
	public void travel(int travelTime, double exhaustionFactor){
		// TODO implement
		exhaustionFactor *= (inv.getCarriedWeight() / 20);
		travelTime += travelTime * (inv.getCarriedWeight() / 20);
	}
	public int[] makeAttributeTest(String[] attributes){
		int dicePool = 0;
		for (int i = 0; i < attributes.length; i++){
			dicePool += this.stats_adjusted.get(attributes[i]);
		}
		return DiceRoller.makeRoll(dicePool);
		
	}
	
//	
//	public int[] makeSkillTest(String skill, int difficulty){
//		// TODO get skill level
//		int dicePool = 9;
//		return DiceRoller.makeSuccessTest(dicePool, difficulty);
//		
//	}
//	public int[] makeSkillTest(int skillID, int difficulty){
//		// TODO get skill level
//		int skillLevel = 9;
//		return null;
//	}
}
