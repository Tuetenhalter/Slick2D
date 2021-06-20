package Weapon;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObject;
import GameStates.MyBasicGameState;
import idk.Options;
import idk.Sounds;
import idk.Vector2D;

public class Schrot extends Weapon {

	public static final int SHOOT_DELAY_MAX = 2500;

	public static final float BULLET_SPEED = 2000f;
	// Bulletspeed +- BUTTET_RANDOM_SPEED
	public static final float BUTTET_RANDOM_SPEED = 1000f;
	public static final float BULLET_AMMOUNT = 12;
	public static final float SPRAY = 10f;
	public static final float BULLET_SIZE = 10f;
	public static final int BULLET_DMG = 10;
	
	public static final int BULLET_LIVE_TIME = 500;
	public static final int BULLET_LIVE_TIME_RANDOM = 100;

	public static final int AMMUNTION_MAX = 2;
	public static final int RELOAD_TIME = 1000;

	private boolean reload = false;
	private int reloadTime = RELOAD_TIME;

	public Schrot() {
		super();
		setShootDelayMax(SHOOT_DELAY_MAX);
		setAmmunitionMax(AMMUNTION_MAX);
		setAmmunition(AMMUNTION_MAX);
	}

	@Override
	public void shoot(GameObject me, Vector2D target, GameContainer container, StateBasedGame game, int delta,
			MyBasicGameState mygame) {
		Input input = container.getInput();

		if (getShootDelay() > 0) {
			setShootDelay(getShootDelay() - delta);
		}

		if (reload) {
			reloadTime -= delta;
			if (reloadTime < 0) {
				reloadTime = RELOAD_TIME;
				if (getAmmunition() >= getAmmunitionMax()) {
					reload = false;
				} else {
					setAmmunition(getAmmunition() + 1);
				}
			}
		}

		if (input.isKeyDown(Options.reload)) {
			reload = true;
		}

		if (getShootDelay() <= 0) {
			if (input.isMousePressed(Options.shoot)) {
				if (getAmmunition() > 0) {
					Sounds.schrotShoot.play();
					reload = false;
					setAmmunition(getAmmunition() - 1);
					target.sub(me.getCenter());

					for (int i = 0; i < BULLET_AMMOUNT; i++) {

						float sprayAngle = SPRAY * (mygame.getRandom().nextFloat() - .5f);
						float randomSpeed = BULLET_SPEED
								+ BUTTET_RANDOM_SPEED * (mygame.getRandom().nextFloat() * 2 - 1);
						Vector2D target2 = target.clone().addTheta(sprayAngle).setMagnitude(randomSpeed);

						Bullet bullet = new Bullet(me.getCenter().clone(), target2, BULLET_SIZE, BULLET_SIZE);
						bullet.setBounce(0);
						bullet.setGroup(Bullet.GROUP_PLAYER);
						bullet.setLiveTime(BULLET_LIVE_TIME + mygame.getRandom().nextInt(BULLET_LIVE_TIME_RANDOM*2) - BULLET_LIVE_TIME_RANDOM);
						bullet.setDamage(BULLET_DMG);
						mygame.getGameList().add(bullet);
					}

					setShootDelay(getShootDelayMax());
					
					if(getAmmunition() <= 0) {
						reload = true;
					}
				} else {
					reload = true;
				}

			}

		}

	}

}
