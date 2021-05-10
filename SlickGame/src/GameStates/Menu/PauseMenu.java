package GameStates.Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GUI.Button;
import GameStates.States;

public class PauseMenu extends Menu {

	private Button resume;
	private Button exit;
	private Button options;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		resume = new Button("Resume", 0.25f, 0.25f, 0.1f, 0.8f, container);
		options = new Button("Option", 0.25f, 0.25f, 0.3f, 0.6f, container);
		exit = new Button("Exit", 0.25f, 0.25f, 0.5f, 0.4f, container);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		game.getState(States.GAME.getState()).render(container, game, g);
		resume.render(container, game, g);
		options.render(container, game, g);
		exit.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		resume.update(container, game, delta);
		options.update(container, game, delta);
		exit.update(container, game, delta);

		Input input = container.getInput();

		if (resume.clicked()) {
			game.enterState(States.GAME.getState());
		}

		if (options.clicked()) {
			OptionsMenu optionsMenu = (OptionsMenu) game.getState(States.OPTIONSMENU.getState());
			optionsMenu.setLastState(this.getID());
			game.enterState(States.OPTIONSMENU.getState());
		}

		if (exit.clicked()) {
			game.enterState(States.STARTMENU.getState());
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(States.GAME.getState());
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.PAUSEMENU.getState();
	}

	public void newImage(GameContainer container, StateBasedGame game) throws SlickException {

		Image tmpImage = new Image(container.getWidth(), container.getHeight(), Image.FILTER_NEAREST);
		Image image;
		Graphics test = tmpImage.getGraphics();
		test.flush();
		game.getState(States.GAME.getState()).render(container, game, test);

		image = new Image(container.getWidth(), container.getHeight(), Image.FILTER_NEAREST);
		ImageBuffer ib = new ImageBuffer(container.getWidth(), container.getHeight());
		Graphics ig = image.getGraphics();

		int[][] red = new int[container.getWidth()][container.getHeight()];
		int[][] green = new int[container.getWidth()][container.getHeight()];
		int[][] blue = new int[container.getWidth()][container.getHeight()];

		for (int i = 0; i < container.getWidth(); i++) 
		{
			for (int j = 0; j < container.getHeight(); j++) 
			{
				Color color = tmpImage.getColor(i, j);
				for (int i2 = i - 1; i2 <= i + 1; i2++) 
				{
					for (int j2 = j - 1; j2 <= j + 1; j2++) 
					{
						if (!(i2 < 0 || i2 > container.getWidth() - 1 || j2 < 0 || j2 > container.getHeight() - 1)) 
						{
							red[i2][j2] += color.getRed();
							green[i2][j2] += color.getGreen();
							blue[i2][j2] += color.getBlue();
						}
					}
				}
			}
		}

		for (int i = 0; i < container.getWidth(); i++) {
			for (int j = 0; j < container.getHeight(); j++) {
				ib.setRGBA(i, j, blue[i][j] / 9, green[i][j] / 9, red[i][j] / 9, 255);
			}
		}

		image = ib.getImage();

		ig.flush();
	}

}
