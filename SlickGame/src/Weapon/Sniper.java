package Weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObject;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Sniper extends Weapon {
	
	public static final int SHOOT_DELAY_MAX = 1000;

	public static final float BULLET_SPEED = 2000f;
	public static final float BULLET_SIZE = 10f;
	public static final float BULLET_DMG = 100f;

	public Sniper() {
		super();
		setShootDelayMax(SHOOT_DELAY_MAX);
	}

	@Override
	public void shoot(GameObject me, Vector2D target, GameContainer container, StateBasedGame game, int delta,
			MyBasicGameState mygame) {

		Input input = container.getInput();

		if (getShootDelay() > 0) {
			setShootDelay(getShootDelay() - delta);
		}

		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

			if (getShootDelay() <= 0) {

				Bullet bullet = new Bullet(me.getCenter(), target, BULLET_SPEED, 10, 10);
				bullet.setBounce(0);
				bullet.setGroup(Bullet.GROUP_PLAYER);
				mygame.getGameList().add(bullet);
				setShootDelay(getShootDelayMax());

			}

		}

	}
	
	@Override
	public void renderGUI(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Sniper [getShootDelay()=" + getShootDelay() + ", getShootDelayMax()=" + getShootDelayMax()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	
}
