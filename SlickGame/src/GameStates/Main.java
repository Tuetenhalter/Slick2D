package GameStates;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.Menu.EndScreenMenu;
import GameStates.Menu.GameOverMenu;
import GameStates.Menu.OptionsMenu;
import GameStates.Menu.PauseMenu;
import GameStates.Menu.ShopsMenu;
import GameStates.Menu.StartMenu;

/**
 * 
 * @author Pascal Heimann, Amir Hamdoun ,David Schmitt
 * @version 4.2
 */

public class Main extends StateBasedGame
{

	/**
	 * 
	 * @param args
	 * @throws SlickException
	 */

	public static void main(String[] args) throws SlickException
	{
		System.out.println("lull");
		AppGameContainer container = new AppGameContainer(new Main("Moin"));
		container.setIcon("res/Untitled.png");
		container.setDisplayMode(1600, 900, false);
		container.start();

	}

	public Main(String test)
	{
		super(test);

		// TODO Auto-generated constructor stub
	}
 
	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{addState(new EndScreenMenu());
		addState(new StartMenu());
		
		Game level1 = new Game("Level 1", 0);

		Game level2 = new Game("Level 2", 9);
		level2.setDamage(1.1f);
		level2.setLive(1.1f);
		level2.setPotatos(2f);
		level2.setEnemys(40);

		Game level3 = new Game("Level 3", 2);
		level3.setDamage(1.25f);
		level3.setLive(1.25f);
		level3.setPotatos(4f);
		level3.setEnemys(50);

		Game level4 = new Game("Level 4", 3);
		level4.setDamage(1.5f);
		level4.setLive(1.5f);
		level4.setPotatos(4f);
		level4.setEnemys(50);

		Game level5 = new Game("Level 5", 4);
		level5.setDamage(1.75f);
		level5.setLive(1.75f);
		level5.setPotatos(8f);
		level5.setEnemys(60);

		Game level6 = new Game("Level 6", 5);
		level6.setDamage(2f);
		level6.setLive(2f);
		level6.setPotatos(16f);
		level6.setEnemys(70);

		Game level7 = new Game("Level 7", 6);
		level7.setDamage(2.5f);
		level7.setLive(2.5f);
		level7.setPotatos(32f);
		level7.setEnemys(100);

		Game level8 = new Game("Level 8", 7);
		level8.setDamage(3.5f);
		level8.setLive(3.5f);
		level8.setPotatos(64f);
		level8.setEnemys(125);

		Game level9 = new Game("Level 9", 8);
		level9.setDamage(4f);
		level9.setLive(4f);
		level9.setPotatos(128f);
		level9.setEnemys(150);

		Game level10 = new Game("Level 10", 9);
		level10.setDamage(5f);
		level10.setLive(5f);
		level10.setPotatos(256f);
		level10.setEnemys(200);

		addState(level1);
		addState(level2);
		addState(level3);
		addState(level4);
		addState(level5);
		addState(level6);
		addState(level7);
		addState(level8);
		addState(level9);
		addState(level10);

		addState(new PauseMenu());
		addState(new OptionsMenu());
		addState(new ShopsMenu());
		addState(new GameOverMenu());
		

	}
}
