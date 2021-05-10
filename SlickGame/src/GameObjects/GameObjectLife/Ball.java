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

	static final float REDUCE_SPEED = .99f;
	static final float SPEED = .5f;
	static final float MAXLIVE = 5f;

	public Ball(float x, float y, float width, float height) {
		super(x, y, width, height);

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

		if(mygame.getPlayer().getHitBox().intersects(getHitBox())) {
			test.setMagnitude(getVel().length()+mygame.getPlayer().getVel().length());
			
			mygame.getPlayer().getVel().add(test);
			setAcc(test.mul(-1f));
		}
		
		getVel().add(getAcc()).limit(32);
		getVel().mul(REDUCE_SPEED);
		
		for(GameObject gameObject : mygame.getGameList()) {
			if(gameObject instanceof Wall) {
				colltiontoWall((Wall) gameObject);
			}
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

}
