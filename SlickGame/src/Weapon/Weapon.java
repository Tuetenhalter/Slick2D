package Weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public abstract class Weapon {
	
	private int shootDelay = 0;
	private int shootDelayMax;
	
	private int ammunition;
	private int ammunitionMax;
	
	private int reloadtime;
	
	public Weapon() {
		super();
	}

	public abstract void shoot(GameObject me, Vector2D target, GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame);
	
	public int getShootDelay() {
		return shootDelay;
	}

	public void setShootDelay(int shootDelay) {
		this.shootDelay = shootDelay;
	}

	public int getShootDelayMax() {
		return shootDelayMax;
	}

	public void setShootDelayMax(int shootDelayMax) {
		this.shootDelayMax = shootDelayMax;
	}

	public int getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(int ammunition) {
		this.ammunition = ammunition;
	}

	public int getAmmunitionMax() {
		return ammunitionMax;
	}

	public void setAmmunitionMax(int ammunitionMax) {
		this.ammunitionMax = ammunitionMax;
	}

	public int getReloadtime() {
		return reloadtime;
	}

	public void setReloadtime(int reloadtime) {
		this.reloadtime = reloadtime;
	}
}
