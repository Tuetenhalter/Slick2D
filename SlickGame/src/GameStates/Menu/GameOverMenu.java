package GameStates.Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import GUI.Button;
import GameStates.States;

public class GameOverMenu extends Menu
{

	private Button restart;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		restart = new Button("Restart", 0.25f, 0.25f, 0.1f, 0.8f, container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.setBackground(Color.black);
		game.getState(States.GAME.getState()).render(container, game, g);
		restart.render(container, game, g);
//		g.drawString("GameOver!!!", container.getWidth()/2, container.getHeight()/8);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		restart.update(container, game, delta);

		if (restart.clicked())
		{
			game.enterState(States.STARTMENU.getState());
		}

	}

	@Override
	public int getID()
	{
		return States.GAMEOVER.getState();
	}

}
