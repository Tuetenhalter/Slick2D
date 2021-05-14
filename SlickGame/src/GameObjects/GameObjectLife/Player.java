package GameObjects.GameObjectLife;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObject;
import GameObjects.Wall.BouncieWall;
import GameObjects.Wall.Wall;
import GameStates.MyBasicGameState;
import GameStates.States;
import idk.Camara;
import idk.Options;
import idk.Vector2D;

public class Player extends GameObjectLife {
	
	static final float SPEED = 2000f;
	static final float MAX_SPEED = 1000f;
	static final float REDUCE_SPEED = .99f;
	static final float BULLET_SPEED = 1000f;
	static final float MAXLIVE = 100f;
	static final int SHOOT_DELAY_MAX = 1000;
	private float dashCouldown = 0f;
	
	Sound sound;

	public Player(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive, int shootDelay, int shootDelayMax) {
		super(pos, vel, acc, width, height, hitBox, live, maxLive, shootDelay, shootDelayMax);
		// TODO Auto-generated constructor stub
	}

	public Player(float x, float y, float width, float height) throws SlickException{
		super(x, y, width + 1, height + 1, MAXLIVE, SHOOT_DELAY_MAX);
		setHeight(height);
		setWidth(width);
		
		sound = new Sound("res/Shoot.wav");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {
		
		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		
		g.setColor(Color.red);
		//g.fill(getHitBox());

//		g.resetTransform();
//		g.setColor(Color.red);
//		
//		g.drawString("speedX: " + getVel().getX(), 5, 200);
//		g.drawString("speedY: " + getVel().getY(), 5, 400);
//
//		g.drawString("X: " + getPos().getX(), 5, 100);
//		g.drawString("Y: " + getPos().getY(), 5, 120);
//		
//		mygame.getCamara().translateCamara(g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) throws SlickException{
		
		Input input = container.getInput();
		if (getLive() <= 0) {
			setDestroy(true);
			game.enterState(States.GAMEOVER.getState());
		}
		
		if(getShootDelay() > 0) {
			setShootDelay(getShootDelay() - delta);			
		}

		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (getShootDelay() <= 0) {
				Bullet bullet = new Bullet(getPos().clone().add(getWidth() / 2 - 5, getHeight() / 2 - 5),
						new Vector2D(input.getMouseX() + mygame.getCamara().getPos().getX(),
								input.getMouseY() + mygame.getCamara().getPos().getY()),
						BULLET_SPEED, 10, 10);
				bullet.setBounce(1);
				bullet.setGroup(Bullet.GROUP_PLAYER);	
				sound.play();
				mygame.getGameList().add(bullet);
				setShootDelay(getShootDelayMax());

			}

		}

		getAcc().set(0, 0);

		if (input.isKeyDown(Options.back)) {
			getAcc().add(0, 1);
		}

		if (input.isKeyDown(Options.forward)) {
			getAcc().add(0, -1);
		}
		if (input.isKeyDown(Options.right)) {
			getAcc().add(1, 0);
		}
		if (input.isKeyDown(Options.left)) {
			getAcc().add(-1, 0);
		}

		if (getAcc().magnitude() > 0) {

				getAcc().setMagnitude(SPEED);				
		
		} else {	
			getAcc().set(getVel());
			getAcc().mul(-1f);
			getAcc().setMagnitude(SPEED);
		}
		
		dashCouldown--;
		
		if (input.isKeyDown(Input.KEY_SPACE) && dashCouldown <0) {
			getAcc().mul(10);
			dashCouldown = 20;
		}

		
		getVel().add(getAcc().clone().mul(delta/1000.0f));
		getVel().limit(MAX_SPEED);
		if(getVel().magnitude() < SPEED*delta/1000.0f) {
			getVel().set(0, 0);
		}
		
		for (GameObject gameObject : mygame.getGameList()) {
			if (gameObject instanceof Wall) {
				 //colltiontoWall((Wall) gameObject, delta);
			}

//			if (gameObject instanceof BouncieWall) {
//				collitcionBounciWall((BouncieWall) gameObject);
//
//			}
		}

		getPos().add(getVel().clone().mul(delta/1000.0f));
	}

	public void colltiontoWall(Wall gameObject, int delta) {

		float x = getX();
		float y = getY();

		float speedx = getSpeedX()*delta/1000f;
		float speedy = getSpeedY()*delta/1000f;

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
