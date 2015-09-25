package misc;

import combat.AttackAction;

public class DeathException extends Exception {

	/**
	 * generated by Java
	 */
	private static final long serialVersionUID = -7742598234753889898L;
	public boolean playerDeath;
	public boolean diedWhileWaiting = false;
	public boolean diedWhileSleeping = false;
	public boolean diedWhileTraveling = false;
	public boolean diedWhileFighting = false;
	public boolean diedFromInstantDamage = false;
	public boolean diedFromBleeding = false;
	public AttackAction finishingBlow = null;
	public AttackAction yourAttack = null;
	
	public DeathException(boolean playerDeath){
		super(playerDeath ? "you have been killed!" : "You killed the ");
		this.playerDeath = playerDeath;
	}
	public DeathException(String message, boolean playerDeath){
		super(message);
		this.playerDeath = playerDeath;
	}
}