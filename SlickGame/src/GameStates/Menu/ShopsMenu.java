package GameStates.Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import GUI.Button;
import GameStates.States;

public class ShopsMenu extends Menu
{
	private int lastState;
	private Button upgrade1, upgrade2, upgrade3, upgrade4, upgrade5, upgrade6;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{ 
		upgrade1 = new Button("Test1", 0.2f, 0.599f, 0.3f, 0.5f, container);
		upgrade2 = new Button("Test2", 0.6f, 0.6f, 0.3f, 0.5f, container);
		upgrade3 = new Button("Test3", 0.6f, 0.2f, 0.3f, 0.5f, container);
		upgrade4 = new Button("Test4", 0.2f, 0.6f, 0.5f, 0.3f, container);
		upgrade5 = new Button("Test5", 0.6f, 0.6f, 0.5f, 0.3f, container);
		upgrade6 = new Button("Test6", 0.6f, 0.2f, 0.5f, 0.3f, container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		// TODO Auto-generated method stub
		
		upgrade2.render(container, game, g);
		upgrade1.render(container, game, g);
		upgrade3.render(container, game, g);
		upgrade4.render(container, game, g);
		upgrade5.render(container, game, g);
		upgrade6.render(container, game, g);

		g.drawString("Coins: " + "coins Variable", container.getWidth() / 16, container.getHeight() / 14);
		g.setColor(Color.red);
		g.drawLine(0.2f, 0.2f, 0.3f, 0.5f);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{

		upgrade1.update(container, game, delta);
		upgrade2.update(container, game, delta);
		upgrade3.update(container, game, delta);
		upgrade4.update(container, game, delta);
		upgrade5.update(container, game, delta);
		upgrade6.update(container, game, delta);

		if (upgrade1.isClicked())
		{
			
		}

		if (upgrade2.isClicked())
		{

		}

		if (upgrade3.isClicked())
		{

		}
		if (upgrade4.isClicked())
		{

		}
		if (upgrade5.isClicked())
		{

		}
		if (upgrade6.isClicked())
		{

		}
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return States.SHOP.getState();
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
