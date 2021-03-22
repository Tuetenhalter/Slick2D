package idk;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import GameStates.MyBasicGameState;

public class Camara {

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

	public void camaraMove(MyBasicGameState mygame) {

		Vector2D target = mygame.player.getPos().clone();
		target.sub(1920 / 2, 1080 / 2);
		target.add(mygame.player.getWidth() / 2, mygame.player.getHeight() / 2);
		

		if (rangex2 != 0) {
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
		}
		
		
		target.sub(pos);
		target.mul(1f);
		pos.add(target);

	}

	public void drawShape(Graphics g, Shape shape, Color color) {
		g.setColor(color);

		Vector2D v = new Vector2D(shape.getX(), shape.getY());

//		v.sub(pos);

		shape.setX(v.getX());
		shape.setY(v.getY());

		g.fill(shape);

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
