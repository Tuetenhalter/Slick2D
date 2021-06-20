package Weapon;

import org.newdawn.slick.Color;
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

public class Kalashinikov extends Weapon {

	public static final int SHOOT_DELAY_MAX = 100;

	public static final float BULLET_SPEED = 2000f;
	// Bulletspeed +- BUTTET_RANDOM_SPEED
	public static final float BUTTET_RANDOM_SPEED = 100f;
	public static final float SPRAY = 5f;
	public static final float BULLET_SIZE = 10f;
	public static final int BULLET_DMG = 50;

	public static final int BULLET_LIVE_TIME = 2000;
	public static final int BULLET_LIVE_TIME_RANDOM = 100;

	public static final int AMMUNTION_MAX = 30;
	public static final int RELOAD_TIME = 3000;

	private boolean reload = false;
	private int reloadTime = RELOAD_TIME;
	private boolean playReloadSound = false;

	public  Kalashinikov() {
		super();
		setShootDelayMax(SHOOT_DELAY_MAX);
		setAmmunitionMax(AMMUNTION_MAX);
		setAmmunition(AMMUNTION_MAX);
	}
	

	@Override
	public void shoot(GameObject me, Vector2D target, GameContainer container, StateBasedGame game, int delta,
			MyBasicGameState mygame) {
		Input input = container.getInput();

		// sub shoot delay
		if (getShootDelay() > 0) {
			setShootDelay(getShootDelay() - delta);
		}else {
			if(reload && playReloadSound) {
				Sounds.schrotReload.play(1f, Options.volume);
				playReloadSound = false;
			}
		}

		// reload
		if (reload) {
			reloadTime -= delta;
			if (reloadTime < 0) {
				//reset time
				reloadTime = RELOAD_TIME;

				//stop reloading
				reload = false;
				
				//set ammuntion
				setAmmunition(AMMUNTION_MAX);
			}
		}

		// if button R pressed reload
		if (input.isKeyDown(Options.reload))

		{
			if (!reload) {
				reload = true;
				reloadTime = RELOAD_TIME;
				Sounds.schrotReload.play(1f, Options.volume);
			}
		}

		// if shootdaly and button pressed shoot
		if (getShootDelay() <= 0) {
			if (input.isMouseButtonDown(Options.shoot)) {
				if (!reload) {
					if (getAmmunition() > 0) {
						// play sound
						Sounds.shoot.play(1f, Options.volume);

						// after shooting the time of reloadgin is extra long
						reloadTime = SHOOT_DELAY_MAX + RELOAD_TIME;

						setAmmunition(getAmmunition() - 1);
						if (getAmmunition() <= 0) {
							reload = true;
							reloadTime = RELOAD_TIME;
							playReloadSound = true;
						}
						
						//reset shoot delay
						setShootDelay(getShootDelayMax());
						
						// get shoot direction
						target.sub(me.getCenter());

						// calcilat random spray and speed
						float sprayAngle = SPRAY * (mygame.getRandom().nextFloat() - .5f);
						float randomSpeed = BULLET_SPEED
								+ BUTTET_RANDOM_SPEED * (mygame.getRandom().nextFloat() * 2 - 1);
						Vector2D target2 = target.clone().addTheta(sprayAngle).setMagnitude(randomSpeed);

						// make the bullet
						Bullet bullet = new Bullet(me.getCenter().clone(), target2, BULLET_SIZE, BULLET_SIZE);
						bullet.setBounce(0);
						bullet.setGroup(Bullet.GROUP_PLAYER);
						bullet.setLiveTime(BULLET_LIVE_TIME + mygame.getRandom().nextInt(BULLET_LIVE_TIME_RANDOM * 2)
								- BULLET_LIVE_TIME_RANDOM);
						bullet.setDamage(BULLET_DMG);
						mygame.getGameList().add(bullet);

						
						// if ammuntion emty start reloading

					} else {
						reload = true;
						reloadTime = RELOAD_TIME;
						Sounds.schrotReload.play(1f, Options.volume);
					}

				}
			}
		}

	}
	
	public void renderGUI(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) throws SlickException {
		for (int i = 0; i < getAmmunitionMax(); i++) {
			
			g.resetTransform();
			if(i +1 > getAmmunition()) {
				g.setColor(Color.gray);
			} else {
				g.setColor(Color.red);
			}
			g.fillRect(1580 - i*20, 840, 10, 50);
			
			mygame.getCamara().translateCamara(container, game, g, mygame);
			
			
		}
	}

//	@Override
//	public void renderGUI(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame)
//			throws SlickException {
//		// TODO Auto-generated method stub
//
//	}

}
