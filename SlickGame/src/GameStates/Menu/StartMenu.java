package GameStates.Menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import GUI.Button;
import GameStates.States;
import idk.Images;
import idk.Options;
import idk.Sounds;
import idk.Stats;

public class StartMenu extends Menu
{

	public Button play;
	private Button options;
	private Button exit;
	private Button shop;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		Stats.startUp();
		Images.startUp();
		Sounds.startUp();
		play = new Button("Play", .25f, .25f, .1f, .8f, container);
		options = new Button("Option", 0.25f, 0.25f, 0.3f, 0.6f, container);
		exit = new Button("Exit", 0.25f, 0.25f, 0.7f, 0.2f, container);
		shop = new Button("Shop", 0.25f, 0.25f, 0.5f, 0.4f, container);

		Options.init();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{

		play.render(container, game, g);
		options.render(container, game, g);
		exit.render(container, game, g);
		shop.render(container, game, g);
		g.drawImage(Images.spawn, 0, 0);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{

		play.update(container, game, delta);
		options.update(container, game, delta);
		exit.update(container, game, delta);
		shop.update(container, game, delta);

		if (play.clicked())
		{
			game.enterState(States.GAME.getState());
		}

		if (options.clicked())
		{
			OptionsMenu optionsMenu = (OptionsMenu) game.getState(States.OPTIONSMENU.getState());
			optionsMenu.setLastState(this.getID());
			game.enterState(States.OPTIONSMENU.getState());
		}

		if (shop.clicked())
		{
			ShopsMenu shopMenu = (ShopsMenu) game.getState(States.SHOP.getState());
			shopMenu.setLastState(this.getID());
			game.enterState(States.SHOP.getState());
		}

		if (exit.clicked())
		{
			container.exit();
		}

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			container.exit();
		}
	}

	@Override
	public int getID()
	{
		return States.STARTMENU.getState();
	}
}
