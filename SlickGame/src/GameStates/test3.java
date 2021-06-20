package GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class test3 extends MyBasicGameState
{

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{

		game.getState(0).render(container, game, g);

		g.setColor(Color.green);
		g.resetTransform();
		g.drawString("test3", 100, 100);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		if (container.getInput().isKeyPressed(Input.KEY_J))
		{
			game.enterState(0);
		}

	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return -1;
	}

}
