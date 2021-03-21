package idk;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import GameStates.MyBasicGameState;

public class Camara {
	private Vector2D pos;

	public Camara(Vector2D pos) {
		super();
		this.pos = pos;
	}
	
	public Camara(float x, float y) {
		this(new Vector2D(x, y));		
	}
	
	public void camaraMove(MyBasicGameState mygame) {
		
//		double targetviewx = mygame.player.getX() - (container.getWidth() / 2) + (player.getWidth() / 2);
//		double targetviewy = mygame.player.getY() - (container.getHeight() / 2) + (player.getHeight() / 2);
		
		
		Vector2D target = mygame.player.getPos().clone();
		target.sub(1920/2, 1080/2);
		target.add(mygame.player.getWidth()/2, mygame.player.getHeight()/2);
		target.sub(pos);
		target.mul(.01f);
		pos.add(target);

//		double targetviewx = (player.getX() + (player.getWidth() / 2) + container.getInput().getMouseX() + viewx) / 2
//				- container.getWidth() / 2;
//		double targetviewy = (player.getY() + (player.getHeight() / 2) + container.getInput().getMouseY() + viewy) / 2
//				- container.getHeight() / 2;

//		if (targetviewx < 0) {
//			targetviewx = 0;
//		}
//
//		if (targetviewy < 0) {
//			targetviewy = 0;
//		}

//		viewx += (targetviewx - viewx) * .01;
//		viewy += (targetviewy - viewy) * .01;
		
	}
	
	public void drawShape(Graphics g, Shape shape, Color color) {
		g.setColor(color);
		
		Vector2D v = new Vector2D(shape.getX(), shape.getY());
		
		v.sub(pos);
		
		shape.setX(v.getX());
		shape.setY(v.getY());
		
		g.fill(shape);
		
	}

	//Getter Setter
		
	public Vector2D getPos() {
		return pos;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}
	
	public void setPos(float x, float y) {
		pos.set(x, y);
	}
	
	
	
	
	
	
}
