package test;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameObjects.Wall.BouncieWall;
import GameObjects.Wall.Wall;
import GameStates.MyBasicGameState;

public class Bullet extends GameObject {

	

	public Bullet(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}


//	public Bullet(float x, float y, float shootX, float shootY, float speed, float width, float height) {
//		double distance = Math.sqrt((shootX - x) * (shootX - x) + (shootY - y) * (shootY - y));
//		double shootSpeedX = (speed / distance) * (shootX - x);
//		double shootSpeedY = (speed / distance) * (shootY - y);
//
//		setSpeedX(shootSpeedX);
//		setSpeedY(shootSpeedY);
//		
//		
//		
//		
//	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g,
			MyBasicGameState mygame) {
		
		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		mygame.camara.drawShape(g, getHitBox(), Color.red);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {

		for (GameObject gameObject : mygame.gameList) {
			if (gameObject instanceof Wall) {
				colltiontoWall((Wall) gameObject);
			}

			if (gameObject instanceof BouncieWall) {
				colltiontoBouncieWall((BouncieWall) gameObject);
			}
		}

		getPos().add(getVel());

	}

	public void colltiontoWall(Wall gameObject) {

		double x = getPos().getX();
		double y = getPos().getY();

		double speedx =	getVel().getX();
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
			getVel().mul(-2);
			return;
		}
		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y) {
			getVel().mul(-2);
			return;
		}
		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x) {
			getVel().mul(-2);
			return;
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x) {
			getVel().mul(-2);
			return;
		}

	}

	public void colltiontoBouncieWall(BouncieWall gameObject) {


		double x = getPos().getX();
		double y = getPos().getY();

		double speedx =	getVel().getX();
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
			getVel().mul(-2);
			
			return;
		}
		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y) {
			getVel().mul(-2);
			return;
		}
		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x) {
			getVel().mul(-2);
			return;
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x) {
			getVel().mul(-2);
			return;
		}

	}

}
