package GameObjects.GameObjectLife;

import org.newdawn.slick.geom.Shape;

import GameObjects.GameObject;
import idk.Vector2D;

public abstract class GameObjectLife extends GameObject {

	private float live;
	private float maxLive;

	private int shootDelay = 0;
	private int shootDelayMax;

	public GameObjectLife(float x, float y, float width, float height, float maxlive, int shootDelayMax) {
		super(x, y, width, height);
		this.live = maxlive;
		this.maxLive = maxlive;
		this.shootDelayMax = shootDelayMax;
		// TODO Auto-generated constructor stub
	}

	public GameObjectLife(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive, int shootDelay, int shootDelayMax) {
		super(pos, vel, acc, width, height, hitBox);
		this.live = live;
		this.maxLive = maxLive;
		this.shootDelay = shootDelay;
		this.shootDelayMax = shootDelayMax;
	}

	// Getter Setter

	public float getLive() {
		return live;
	}

	public void setLive(float live) {
		this.live = live;
	}

	public float getMaxLive() {
		return maxLive;
	}

	public void setMaxLive(float maxLive) {
		this.maxLive = maxLive;
	}

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

}
