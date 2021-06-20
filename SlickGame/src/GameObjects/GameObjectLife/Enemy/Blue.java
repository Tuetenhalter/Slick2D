package GameObjects.GameObjectLife.Enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObjectLife.Player;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Blue extends Enemy {

	static final int SHOOTDELAYMAX = 1000;
	static final int SHOOTDELAY_RANDOM = 100;
	static final int MAXLIVE = 100;

	static final float ROTATION_SPEED = 45f;

	static final float BULLET_SPEED = 200f;
	static final float BUTTET_RANDOM_SPEED = 100f;
	static final float BULLET_WIDTH = 10f;
	static final float BULLET_HEIGHT = 10f;
	static final float SPRAY = 10f;
	static final float BULLET_SIZE = 10f;
	static final int BULLET_DMG = 10;
	
	protected int dmg = BULLET_DMG;

	public Blue(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive, int shootDelay, int shootDelayMax) {
		super(pos, vel, acc, width, height, hitBox, live, maxLive, shootDelay, shootDelayMax);
		// TODO Auto-generated constructor stub
	}

	public Blue(float x, float y, float width, float height) {
		super(x, y, width + 1, height + 1, MAXLIVE, SHOOTDELAYMAX);
		setHeight(height);
		setWidth(width);
		setShootAngel(0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {
		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		g.setColor(Color.blue);
		g.fill(getHitBox());

//		Player player = mygame.getPlayer();
//		g.drawLine(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2,
//				getX() + getWidth() / 2, getY() + getHeight() / 2);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {

		if (getLive() <= 0) {
			setDestroy(true);
		}

		shoot(container, game, delta, mygame);
	}

	public void shoot(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {
		if (getPos().distanceSq(mygame.getPlayer().getPos()) < 10000000) {

			Player player = mygame.getPlayer();
			Vector2D target = player.getPos().clone();

			if (getShootDelay() > 0) {
				setShootDelay(getShootDelay() - delta);
			}

			if (getShootDelay() <= 0) {
				setShootDelay(getShootDelayMax() + mygame.getRandom().nextInt(SHOOTDELAY_RANDOM*2)- SHOOTDELAY_RANDOM);

				// get shoot direction

				// calcilat random spray and speed
				float sprayAngle = SPRAY * (mygame.getRandom().nextFloat() - .5f);
				float randomSpeed = BULLET_SPEED + BUTTET_RANDOM_SPEED * (mygame.getRandom().nextFloat() * 2 - 1);
				Vector2D target2 = target.clone().sub(getCenter()).addTheta(sprayAngle).setMagnitude(randomSpeed);

				// make the bullet
				Bullet bullet = new Bullet(getCenter().clone(), target2, BULLET_SIZE, BULLET_SIZE);
				bullet.setBounce(0);

				bullet.setGroup(Bullet.GROUP_ENEMY);

				bullet.setDamage(dmg);
				mygame.getGameList().add(bullet);

			}
		}
	}

}
