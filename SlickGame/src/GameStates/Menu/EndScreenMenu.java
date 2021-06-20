package GameStates.Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import GUI.Button;
import GameStates.States;

public class EndScreenMenu extends Menu
{
	private Button back;
	private int lastState;
	private Image endBackgroundScreen;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		back = new Button("back", .90f, .01f, .9f, .01f, container);
		endBackgroundScreen = new Image("res/bg.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		// TODO Auto-generated method stub
		
		g.clear();
		endBackgroundScreen.draw(0,0);
		back.render(container, game, g);
		g.setColor(Color.white);
		g.drawString("YOU WON WTF ARE YOU CRAZY?!?!?!!!", container.getWidth()/7, container.getHeight()/8);
		g.drawString("Or did you just abuse the T hack?", container.getWidth()/7, container.getHeight()/8+30);
		
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
		return States.ENDSCREENMENU.getState();
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
