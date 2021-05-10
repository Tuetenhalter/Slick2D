package idk;

import org.newdawn.slick.GameContainer;

import GameStates.MyBasicGameState;

public class Camara {

	static final float smooth = .1f;
	static final float playerVel = 10f;

	private Vector2D pos;
	private float rangex;
	private float rangey;
	private float rangex2;
	private float rangey2;

	public Camara(Vector2D pos) {
		super();
		this.pos = pos;
	}

	public Camara(float x, float y) {
		this(new Vector2D(x, y));
	}

	public Camara(Vector2D pos, float rangex, float rangey, float rangex2, float rangey2) {
		super();
		this.pos = pos;
		this.rangex = rangex;
		this.rangey = rangey;
		this.rangex2 = rangex2;
		this.rangey2 = rangey2;
	}

	public Camara(float x, float y, float rangex, float rangey, float rangex2, float rangey2) {
		super();
		this.pos = new Vector2D(x, y);
		this.rangex = rangex;
		this.rangey = rangey;
		this.rangex2 = rangex2;
		this.rangey2 = rangey2;
	}

	public void camaraMove(MyBasicGameState mygame, GameContainer container) {

		Vector2D target = mygame.getPlayer().getPos().clone();
		target.sub(container.getWidth() / 2, container.getHeight() / 2);
		target.add(mygame.getPlayer().getWidth() / 2, mygame.getPlayer().getHeight() / 2);
		target.add(mygame.getPlayer().getVel().clone().mul(playerVel));

		if (target.getX() < rangex) {
			target.setX(rangex);
		}
		if (target.getX() > rangex2) {
			target.setX(rangex2);
		}

		if (target.getY() < rangey) {
			target.setY(rangey);
		}
		if (target.getY() > rangey2) {
			target.setY(rangey2);
		}

		target.sub(pos);
		target.mul(smooth);
		pos.add(target);

	}

	// Getter Setter

	public Vector2D getPos() {
		return pos;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	public void setPos(float x, float y) {
		pos.set(x, y);
	}

	public float getRangex() {
		return rangex;
	}

	public void setRangex(float rangex) {
		this.rangex = rangex;
	}

	public float getRangey() {
		return rangey;
	}

	public void setRangey(float rangey) {
		this.rangey = rangey;
	}

	public float getRangex2() {
		return rangex2;
	}

	public void setRangex2(float rangex2) {
		this.rangex2 = rangex2;
	}

	public float getRangey2() {
		return rangey2;
	}

	public void setRangey2(float rangey2) {
		this.rangey2 = rangey2;
	}

}
