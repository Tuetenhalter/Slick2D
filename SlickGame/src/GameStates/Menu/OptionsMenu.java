package GameStates.Menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import GUI.Button;
import GameStates.States;

public class OptionsMenu extends Menu
{

	private int lastState;
	private Button back;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		back = new Button("back", .90f, .01f, .9f, .01f, container);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		back.render(container, game, g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		back.update(container, game, delta);

		if (back.clicked())
		{
			game.enterState(lastState);
		}

	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return States.OPTIONSMENU.getState();
	}

	public int getLastState()
	{
		return lastState;
	}

	public void setLastState(int lastState)
	{
		this.lastState = lastState;
	}

}
