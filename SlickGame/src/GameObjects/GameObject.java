package GameObjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.MyBasicGameState;
import idk.Vector2D;

public abstract class GameObject {

	private Vector2D pos;
	private Vector2D vel;
	private Vector2D acc;
	private float width;
	private float height;
	private Shape hitBox;

	public GameObject(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox) {
		super();
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		this.width = width;
		this.height = height;
		this.hitBox = hitBox;
	}

	public GameObject(float x, float y, float width, float height) {
		this(new Vector2D(x, y), new Vector2D(0, 0), new Vector2D(0, 0), width, height,
				new Rectangle(x, y, width, height));
	}

	public abstract void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame);

	public abstract void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame);

	public float getX() {
		return pos.getX();
	}

	public float getY() {
		return pos.getY();
	}

	public void setX(float x) {
		getPos().setX(x);
	}
	
	public void setY(float y) {
		getPos().setY(y);
	}
	
	public void setPos(float x, float y) {
		getPos().set(x, y);
	}
	
	public float getSpeedX() {
		return vel.getX();
	}

	public float getSpeedY() {
		return vel.getY();
	}

	public void setSpeedX(float speedX) {
		getVel().setX(speedX);
	}
	
	public void setSpeedY(float speedY) {
		getVel().setY(speedY);
	}
	
	public void setVel(float speedX, float speedY) {
		getVel().set(speedX, speedY);
	}

	// Getter Setters

	public Vector2D getPos() {
		return pos;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	public Vector2D getVel() {
		return vel;
	}

	public void setVel(Vector2D vel) {
		this.vel = vel;
	}

	public Vector2D getAcc() {
		return acc;
	}

	public void setAcc(Vector2D acc) {
		this.acc = acc;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Shape getHitBox() {
		return hitBox;
	}

	public void setHitBox(Shape hitBox) {
		this.hitBox = hitBox;
	}

}
