package GameObjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import GameStates.MyBasicGameState;
import idk.Images;
import idk.Vector2D;

public class End extends GameObject
{

	public End(float x, float y, float width, float height)
	{
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public End(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox)
	{
		super(pos, vel, acc, width, height, hitBox);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame)
			throws SlickException
	{
		g.drawImage(Images.spawn, getX(), getY(), getX() + 64, getY() + 64, 0, 0, Images.spawn.getWidth(),
				Images.spawn.getHeight());

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}

}
