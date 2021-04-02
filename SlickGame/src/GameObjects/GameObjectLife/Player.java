package GameObjects.GameObjectLife;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObject;
import GameObjects.Wall.BouncieWall;
import GameObjects.Wall.Wall;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Player extends GameObjectLife {

	public Player(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive, int shootDelay, int shootDelayMax) {
		super(pos, vel, acc, width, height, hitBox, live, maxLive, shootDelay, shootDelayMax);
		// TODO Auto-generated constructor stub
	}

	public Player(float x, float y, float width, float height, float maxlive, int shootDelayMax) {
		super(x, y, width + 1, height + 1, maxlive, shootDelayMax);
		setHeight(height);
		setWidth(width);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {

		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
//		mygame.camara.drawShape(g, getHitBox(), Color.blue);

		g.fill(getHitBox());

		g.resetTransform();
		g.setColor(Color.red);
		g.drawString("speedX: " + getVel().getX(), 5, 20);
		g.drawString("speedY: " + getVel().getY(), 5, 40);

		g.drawString("X: " + getPos().getX(), 5, 100);
		g.drawString("Y: " + getPos().getY(), 5, 120);
		g.translate(-mygame.camara.getPos().getX(), -mygame.camara.getPos().getY());

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {

		Input input = container.getInput();

		if (getShootDelay() <= getMaxLive()) {
			setShootDelay(getShootDelay() + 1);
		}

		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (getShootDelay() > getMaxLive()) {
				Bullet bullet = new Bullet(getPos().clone().add(getWidth() / 2 - 5, getHeight() / 2 - 5),
						new Vector2D(input.getMouseX() + mygame.camara.getPos().getX(),
								input.getMouseY() + mygame.camara.getPos().getY()),
						10, 10, 10);
				bullet.setBounce(1);
				bullet.setGroup(Bullet.GROUP_PLAYER);
				mygame.gameList.add(bullet);
				setShootDelay(0);
				;
			}

		}

		getAcc().set(0, 0);

		if (input.isKeyDown(Input.KEY_S)) {
			getAcc().add(0, 1);
		}

		if (input.isKeyDown(Input.KEY_W)) {
			getAcc().add(0, -1);
		}
		if (input.isKeyDown(Input.KEY_D)) {
			getAcc().add(1, 0);
		}
		if (input.isKeyDown(Input.KEY_A)) {
			getAcc().add(-1, 0);
		}

		if (getAcc().magnitude() > 0) {
			getAcc().setMagnitude(1f);
		}

		getAcc().sub(getVel().clone().mul(.25f));

		getVel().add(getAcc());

		for (GameObject gameObject : mygame.gameList) {
			if (gameObject instanceof Wall) {
				colltiontoWall((Wall) gameObject);
			}

//			if (gameObject instanceof BouncieWall) {
//				collitcionBounciWall((BouncieWall) gameObject);
//
//			}
		}

		getPos().add(getVel());
	}

	public void colltiontoWall(Wall gameObject) {

		float x = getX();
		float y = getY();

		float speedx = getSpeedX();
		float speedy = getSpeedY();

		float height = getHeight();
		float width = getWidth();

		float x2 = gameObject.getX();
		float y2 = gameObject.getY();

		float height2 = gameObject.getHeight();
		float width2 = gameObject.getWidth();

//		System.out.println("x: " + x);
//		System.out.println("y: " + y);
//
//		System.out.println("height: " + height);
//		System.out.println("width: " + width);
//
//		System.out.println("x2: " + x2);
//		System.out.println("y2: " + y2);
//
//		System.out.println("height2: " + height2);
//		System.out.println("width2: " + width2);
//
//		System.out.println("---------------------------");

		// Colotion

		// Links
		if (x + speedx < x2 + width2 && x + speedx > x2 && y2 < y + height && y2 + height2 > y) {
			setSpeedX(0);
			setX(x2 + width2);
		}

		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y) {
			setSpeedX(0);
			setX(x2 - width);
		}

		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x) {
			setSpeedY(0);
			setY(y2 + height2);
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x) {
			setSpeedY(0);
			setY(y2 - height);
		}

//		// links oben
//		if (!(x < x2 + width2) && speedx < 1 && speedy < 1 && x + speedx < x2 + width2 && x + speedx > x2
//				&& y + speedy < y2 + height2 && y + speedy > y2) {
//			setSpeedX(0);
//		}
//		// links unten
//		if (!(x < x2 + width2) && speedx < 1 && speedy > 1 && x + speedx < x2 + width2 && x + speedx > x2
//				&& y + speedy + height > y2 && y + speedy + height < y2 + height2) {
//			setSpeedX(0);
//		}
//		// recht oben
//		if (!(x + width > x2) && speedx > 1 && speedy < 1 && x + speedx + width > x2
//				&& x + speedx + width < x2 + height2 && y + speedy < y2 + height2 && y + speedy > y2) {
//			setSpeedX(0);
//		}
//		// rechts unten
//		if (!(x + width > x2) && speedx > 1 && speedy > 1 && x + speedx + width > x2
//				&& x + speedx + width < x2 + height2 && y + speedy + height > y2
//				&& y + speedy + height < y2 + height2) {
//			setSpeedX(0);
//		}

	}

	public void collitcionBounciWall(BouncieWall gameObject) {

		float x = getPos().getX();
		float y = getPos().getY();

		float speedx = getVel().getX();
		float speedy = getVel().getY();

		float height = getHeight();
		float width = getWidth();

		float x2 = gameObject.getPos().getX();
		float y2 = gameObject.getPos().getY();

		float height2 = gameObject.getHeight();
		float width2 = gameObject.getWidth();

//		System.out.println("x: " + x);
//		System.out.println("y: " + y);
//
//		System.out.println("height: " + height);
//		System.out.println("width: " + width);
//
//		System.out.println("x2: " + x2);
//		System.out.println("y2: " + y2);
//
//		System.out.println("height2: " + height2);
//		System.out.println("width2: " + width2);
//
//		System.out.println("---------------------------");

		// Colotion

		// Links
		if (x + speedx < x2 + width2 && x + speedx > x2 && y2 < y + height && y2 + height2 > y) {
			setSpeedX(-getSpeedX());
			return;
		}
		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y) {
			setSpeedX(-getSpeedX());
			return;
		}
		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x) {
			setSpeedY(-getSpeedY());
			return;
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x) {
			setSpeedY(-getSpeedY());
			return;
		}
	}
}
