package GameObjects.Wall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Wall extends GameObject {
	
	

	public Wall(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {

		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		mygame.camara.drawShape(g, getHitBox(), Color.white);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {

	}

}
