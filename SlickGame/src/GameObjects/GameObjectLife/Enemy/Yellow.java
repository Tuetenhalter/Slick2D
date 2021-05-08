package GameObjects.GameObjectLife.Enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObject;
import GameObjects.GameObjectLife.Player;
import GameObjects.Wall.Wall;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Yellow extends Enemy {

	static final int SHOOTDELAYMAX = 20;
	static final int MAXLIVE = 3;
	static final int LENGHT = 25;

	private double angel = 0;
	private Polygon poly;
	int test = 0;

	public Yellow(float x, float y, float width, float height, float maxlive, int shootDelayMax) {
		super(x, y, width, height, maxlive, shootDelayMax);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame)
			throws SlickException {

		poly = new Polygon();
		poly.addPoint((float) Math.cos(Math.toRadians(angel)) * LENGHT,
				(float) Math.sin(Math.toRadians(angel)) * LENGHT);
		poly.addPoint((float) Math.cos(Math.toRadians(angel + 150)) * LENGHT,
				(float) Math.sin(Math.toRadians(angel + 150)) * LENGHT);
		poly.addPoint((float) Math.cos(Math.toRadians(angel + 180)) * LENGHT / 2,
				(float) Math.sin(Math.toRadians(angel + 180)) * LENGHT / 2);
		poly.addPoint((float) Math.cos(Math.toRadians(angel - 150)) * LENGHT,
				(float) Math.sin(Math.toRadians(angel - 150)) * LENGHT);

		poly.setX(getX() + getHeight() / 2);
		poly.setY(getY() + getWidth() / 2);
		poly.setLocation(getCenter().toVector2f());
		g.setColor(Color.yellow);
		g.fill(poly);

//		g.drawLine(getX(), getY(), getX()+getSpeedX()*100, getY()+getSpeedY()*100);
		g.setColor(Color.red);

//		getHitBox().setLocation(getPos().toVector2f());
//		g.draw(getHitBox());

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame)
			throws SlickException {
		
		if (getLive() <= 0) {
			setDestroy(true);
		}

		// Coliton

		for (GameObject gameObject : mygame.getGameList()) {
			if (gameObject instanceof Wall) {
				colltiontoWall((Wall) gameObject);
			}
		}

		move(mygame);
		
		shoot(container, game, delta, mygame);
		
		getHitBox().setLocation(getPos().toVector2f());

	}

	public void colltiontoWall(Wall gameObject) {

		double x = getPos().getX();
		double y = getPos().getY();

		double speedx = getVel().getX();
		double speedy = getVel().getY();

		double height = getHeight();
		double width = getWidth();

		double x2 = gameObject.getPos().getX();
		double y2 = gameObject.getPos().getY();

		double height2 = gameObject.getHeight();
		double width2 = gameObject.getWidth();

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
			getVel().setX(getVel().getX() * -1);

			return;
		}
		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y) {

			getVel().setX(getVel().getX() * -1);

			return;
		}
		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x) {

			getVel().setY(getVel().getY() * -1);

			return;
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x) {

			getVel().setY(getVel().getY() * -1);

			return;
		}

	}

	public void shoot(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {
		Player player = mygame.getPlayer();

		double distanceX = (getX() + getWidth() / 2) - (player.getX() + player.getWidth() / 2);
		double distanceY = (getY() + getHeight() / 2) - (player.getY() + player.getHeight() / 2);
		double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

		double angel = Math.asin(distanceY / distance);
		angel = Math.toDegrees(angel);
		if (distanceX > 0) {
			angel = 180 - angel;
		} else {
			if (distanceY < 0) {
				angel = angel + 90 + 270;
			}
		}

		if (Math.abs(angel - getShootAngel()) < 180) {
			if (angel < getShootAngel()) {
				setShootAngel(getShootAngel() - 1f);
			} else {
				setShootAngel(getShootAngel() + 1f);
			}
		} else {
			if (angel > getShootAngel()) {
				setShootAngel(getShootAngel() - 1f);
			} else {
				setShootAngel(getShootAngel() + 1f);
			}
		}

		if (getShootAngel() > 360) {
			setShootAngel(getShootAngel() - 360);
		}

		if (getShootAngel() < 0) {
			setShootAngel(getShootAngel() + 360);
		}

		if (Math.abs(getShootAngel() - angel) < 1) {
			setShootAngel((float) angel);
		}

		if (getShootDelay() <= getShootDelayMax()) {
			setShootDelay(getShootDelay() + 1);
		}

		if (getShootDelay() > getShootDelayMax()) {
			if (getPos().distanceSq(mygame.getPlayer().getPos()) < 10000000) {

				Bullet bullet = new Bullet(
						getPos().clone().add(getWidth() / 2 - 5, getHeight() / 2 - 5), mygame.getPlayer().getPos()
								.clone().add(mygame.getPlayer().getWidth() / 2, mygame.getPlayer().getHeight() / 2),
						10, 10, 10);

				bullet.setVel(new Vector2D((float) Math.sin(Math.toRadians(getShootAngel() + 90)),
						(float) Math.cos(Math.toRadians(getShootAngel() + 90))));
				bullet.setGroup(Bullet.GROUP_ENEMY);
				mygame.getGameList().add(bullet);
				setShootDelay(0);
			}
		}
	}

	public void move(MyBasicGameState mygame) {

		float distance = mygame.getPlayer().getPos().distance(getPos());

		float max = 500;
		float min = 100;

		if (distance > max) {
			distance = max;
		}
		if (distance < min) {
			distance = min;
		}

		distance = 1000 / (distance * distance);

		setAcc(mygame.getPlayer().getPos().clone().sub(getPos()).setMagnitude(distance));

		getVel().add(getAcc());
		getPos().add(getVel());

		double angel = Math.asin(getVel().getY() / getVel().length());
		angel = Math.toDegrees(angel);
		if (getVel().getX() > 0) {
			angel = 180 - angel;
		} else {
			if (getVel().getX() < 0) {
				angel = angel + 90 + 270;
			}
		}

		this.angel = 180 - angel;

	}

}
