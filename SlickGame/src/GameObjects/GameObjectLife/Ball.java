package GameObjects.GameObjectLife;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameObjects.GameObjectLife.Enemy.Enemy;
import GameObjects.Wall.Wall;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Ball extends GameObject {

	static final float REDUCE_SPEED = 500f;
	static final float SPEED = 1000f;
	static final float MAXLIVE = 5f;
	int time;

	public Ball(float x, float y, float width, float height) {
		super(x, y, width, height);
		time = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame)
			throws SlickException {
		getHitBox().setLocation(getPos().toVector2f());
		g.setColor(Color.green);
		g.fill(getHitBox());
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame)
			throws SlickException {

		getAcc().set(0, 0);

		Vector2D test = mygame.getPlayer().getPos().clone().sub(getPos());

		test.setMagnitude(SPEED);

		setAcc(test);
		time--;
		Player player = mygame.getPlayer();
		System.out.println("theta2:" + Math.atan2(player.getSpeedY(), player.getSpeedX()));
		if (mygame.getPlayer().getHitBox().intersects(getHitBox()) && time < 0) {
//			test.setMagnitude(getVel().length()+mygame.getPlayer().getVel().length());
//			
//			mygame.getPlayer().getVel().add(test);
//			setAcc(test.mul(-1f));

			double phi, theta1, theta2; // collision angle, a's movement angle, b's movement angle
			double v1, v2; // scalar values of velocity for balls a and b
			double m1, m2; // mass for ball's a and b
			double v1xf, v1yf, v2xf, v2yf; // x and y components of final velocities after collision

			// calculate angles
			phi = Math.atan2(player.getY() - getY(), player.getX() - getX());
			theta1 = Math.atan2(player.getSpeedY(), player.getSpeedX());
			theta2 = Math.atan2(getSpeedY(), getSpeedX());

			System.out.println("phi: " + Math.toDegrees(phi));
			System.out.println("theta1: " + Math.toDegrees(theta1));
			System.out.println("theta2:" + Math.toDegrees(theta2));
			// get mass
			m1 = 10;
			m2 = 1;

			// calculate scalar values of velocities
			v1 = player.getVel().length();
			v2 = getVel().length();

			v1xf = ((((v1 * Math.cos(theta1 - phi) * (m1 - m2)) + (2 * m2 * v2 * Math.cos(theta2 - phi))) / (m1 + m2))
					* Math.cos(phi)) + (v1 * Math.sin(theta1 - phi) * Math.cos(phi + (Math.PI / 2)));
			v2xf = ((((v2 * Math.cos(theta2 - phi) * (m2 - m1)) + (2 * m1 * v1 * Math.cos(theta1 - phi))) / (m1 + m2))
					* Math.cos(phi)) + (v2 * Math.sin(theta2 - phi) * Math.cos(phi + (Math.PI / 2)));

			v1yf = ((((v1 * Math.cos(theta1 - phi) * (m1 - m2)) + (2 * m2 * v2 * Math.cos(theta2 - phi))) / (m1 + m2))
					* Math.sin(phi)) + (v1 * Math.sin(theta1 - phi) * Math.sin(phi + (Math.PI / 2)));
			v2yf = ((((v2 * Math.cos(theta2 - phi) * (m2 - m1)) + (2 * m1 * v1 * Math.cos(theta1 - phi))) / (m1 + m2))
					* Math.sin(phi)) + (v2 * Math.sin(theta2 - phi) * Math.sin(phi + (Math.PI / 2)));

			player.getVel().set((float) v1xf, (float) v1yf);
			getVel().set((float) v2xf, (float) v2yf);
//			container.exit();
			time = 10;

		}

//		colltiontoPlayer(mygame.getPlayer());

		getVel().add(getAcc()).limit(32);
		getVel().mul(REDUCE_SPEED);

		for (GameObject gameObject : mygame.getGameList()) {
			if (gameObject instanceof Wall) {
				colltiontoWall((Wall) gameObject, delta);
			}
		}

		getPos().add(getVel());
	}

	public void colltiontoWall(Wall gameObject, int delta) {

		float x = getX();
		float y = getY();

		float speedx = getSpeedX() * delta / 1000f;
		float speedy = getSpeedY() * delta / 1000f;

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
			setSpeedX(-getSpeedX());
			setX(x2 + width2);
		}

		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y) {
			setSpeedX(-getSpeedX());
			setX(x2 - width);
		}

		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x) {
			setSpeedY(-getSpeedY());
			setY(y2 + height2);
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x) {
			setSpeedY(-getSpeedY());
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

	public void colltiontoPlayer(Player gameObject) {

		float x = getX();
		float y = getY();

		float speedx1 = getSpeedX();
		float speedy1 = getSpeedY();

		float height = getHeight();
		float width = getWidth();

		float x2 = gameObject.getX();
		float y2 = gameObject.getY();

		float height2 = gameObject.getHeight();
		float width2 = gameObject.getWidth();

		float speedx2 = gameObject.getSpeedX();
		float speedy2 = gameObject.getSpeedY();

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
		if (x + speedx1 < x2 + speedx2 + width2 && x + speedx1 > x2 + speedx2 && y2 + speedy2 < y + height
				&& y2 + speedy2 + height2 > y) {
			setSpeedX(-getSpeedX());
			System.out.println(1);

		}

		// Rechts
		if (x + speedx1 + width > x2 + speedx2 && x + speedx1 + width < x2 + speedx2 + width2
				&& y2 + speedy2 < y + height && y2 + speedy2 + height2 > y) {
			setSpeedX(-getSpeedX());
			System.out.println(2);

		}

		// oben
		if (y + speedy1 < y2 + speedy2 + height2 && y + speedy1 > y2 + speedy2 && x2 + speedx2 < x + width
				&& x2 + speedx2 + width2 > x) {
			setSpeedY(-getSpeedY());
			System.out.println(3);

		}
		// untem
		if (y + speedy1 + height > y2 + speedy2 && y + speedy1 + height < y2 + speedy2 + height2
				&& x2 + speedx2 < x + width && x2 + speedx2 + width2 > x) {
			setSpeedY(-getSpeedY());
			System.out.println(4);
		}
	}
}
