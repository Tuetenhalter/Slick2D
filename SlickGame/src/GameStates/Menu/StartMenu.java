package GameStates.Menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.lwjgl.opengl.EXTAbgr;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tests.xml.Stats;

import GUI.Button;
import GameStates.Game;
import GameStates.MyBasicGameState;
import GameStates.States;
import idk.Options;

public class StartMenu extends Menu {

	public Button play;
	private Button options;
	private Button exit;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		play = new Button("Play", .25f, .25f, .1f, .8f, container);
		options = new Button("Option", 0.25f, 0.25f, 0.3f, 0.6f, container);
		exit = new Button("exit", 0.25f, 0.25f, 0.7f, 0.2f, container);

		Options.init();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		play.render(container, game, g);
		options.render(container, game, g);
		exit.render(container, game, g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		play.update(container, game, delta);
		options.update(container, game, delta);
		exit.update(container, game, delta);

		if (play.clicked()) {
			game.enterState(States.GAME.getState());
		}

		if (options.clicked()) {
			OptionsMenu optionsMenu = (OptionsMenu) game.getState(States.OPTIONSMENU.getState());
			optionsMenu.setLastState(this.getID());
			game.enterState(States.OPTIONSMENU.getState());
		}

		if (exit.clicked()) {
			container.exit();
		}

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	@Override
	public int getID() {
		return States.STARTMENU.getState();
	}
}
