package Weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
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

		//sub shoot delay
		if (getShootDelay() > 0) {
			setShootDelay(getShootDelay() - delta);
		}
		
		
		//reload 
		if (reload) {
			reloadTime -= delta;
			if (reloadTime < 0) {
				reloadTime = RELOAD_TIME;
				if (getAmmunition() >= getAmmunitionMax()) {
					reload = false;
				} else {
					setAmmunition(getAmmunition() + 1);
					if(getAmmunition() != AMMUNTION_MAX) {
						Sounds.schrotReload.play(1f, Options.volume);						
					}
				}
			}
		}

		//if button R pressed reload
		if (input.isKeyDown(Options.reload)) {
			if (!reload) {
				reload = true;
				reloadTime = RELOAD_TIME;
				Sounds.schrotReload.play(1f, Options.volume);
			}
		}

		
		//if shootdaly and button pressed shoot
		if (getShootDelay() <= 0) {
			if (input.isMousePressed(Options.shoot)) {
				if (getAmmunition() > 0) {
					//play sound
					Sounds.schrotShoot.play(1f, Options.volume);
					
					//if u shoot u stop reloading
					reload = false;
					//after shooting the time of reloadgin is extra long
					reloadTime = SHOOT_DELAY_MAX + RELOAD_TIME;
					setAmmunition(getAmmunition() - 1);
					//get shoot direction
					target.sub(me.getCenter());

					for (int i = 0; i < BULLET_AMMOUNT; i++) {
						//calcilat random spray and speed
						float sprayAngle = SPRAY * (mygame.getRandom().nextFloat() - .5f);
						float randomSpeed = BULLET_SPEED
								+ BUTTET_RANDOM_SPEED * (mygame.getRandom().nextFloat() * 2 - 1);
						Vector2D target2 = target.clone().addTheta(sprayAngle).setMagnitude(randomSpeed);
						
						//make the bullet
						Bullet bullet = new Bullet(me.getCenter().clone(), target2, BULLET_SIZE, BULLET_SIZE);
						bullet.setBounce(0);
						bullet.setGroup(Bullet.GROUP_PLAYER);
						bullet.setLiveTime(BULLET_LIVE_TIME + mygame.getRandom().nextInt(BULLET_LIVE_TIME_RANDOM * 2)
								- BULLET_LIVE_TIME_RANDOM);
						bullet.setDamage(BULLET_DMG);
						mygame.getGameList().add(bullet);
					}

					setShootDelay(getShootDelayMax());
					//if ammuntion emty start reloading
					if (getAmmunition() <= 0) {
						reload = true;
						setAmmunition(-1);
					}
				} else {
					reload = true;
					Sounds.schrotReload.play(1f, Options.volume);
				}

			}

		}

	}

}
