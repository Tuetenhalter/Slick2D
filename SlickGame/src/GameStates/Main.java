package GameStates;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.omg.PortableInterceptor.ServerRequestInfo;

import GameStates.Menu.GameOverMenu;
import GameStates.Menu.OptionsMenu;
import GameStates.Menu.PauseMenu;
import GameStates.Menu.ShopsMenu;
import GameStates.Menu.StartMenu;
import idk.Stats;
/**
 * 
 * @author Pascal Heimann, Amir Hamdoun ,David Schmitt
 *
 */

public class Main extends StateBasedGame{
	
	/**
	 * 
	 * @param args
	 * @throws SlickException
	 */
	
	
	public static void main(String[] args) throws SlickException {	
		System.out.println("lull");
		AppGameContainer container = new AppGameContainer(new Main("Moin"));
		container.setIcon("res/Untitled.png");
		container.setDisplayMode(1600, 900, false);
		container.start();
		
		
	}
	
	public Main(String test) {
		super(test);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new StartMenu());
		addState(new Game());
		addState(new PauseMenu());
		addState(new OptionsMenu());
		addState(new ShopsMenu());
		addState(new GameOverMenu());
		
	}
}
