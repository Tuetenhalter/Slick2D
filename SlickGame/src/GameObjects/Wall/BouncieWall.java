package GameObjects.Wall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameStates.MyBasicGameState;

public class BouncieWall extends GameObject {

	
	public BouncieWall(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {
		g.setColor(Color.green);
		g.fill(getHitBox());

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {
		// TODO Auto-generated method stub

	}

}
