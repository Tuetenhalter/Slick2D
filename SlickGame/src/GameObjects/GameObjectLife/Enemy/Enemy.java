package GameObjects.GameObjectLife.Enemy;

import org.newdawn.slick.geom.Shape;

import GameObjects.GameObjectLife.GameObjectLife;
import GameObjects.Wall.Wall;
import Weapon.Weapon;
import idk.Vector2D;

public abstract class Enemy extends GameObjectLife {
	
	private float shootAngel;

	public Enemy(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive, int shootDelay, int shootDelayMax) {
		super(pos, vel, acc, width, height, hitBox, live, maxLive, shootDelay, shootDelayMax);
		// TODO Auto-generated constructor stub
	}

	public Enemy(float x, float y, float width, float height, float maxlive, int shootDelayMax) {
		super(x, y, width, height, maxlive, shootDelayMax);
		// TODO Auto-generated constructor stub
	}

	public float getShootAngel() {
		return shootAngel;
	}

	public void setShootAngel(float shootAngel) {
		this.shootAngel = shootAngel;
	}

}
