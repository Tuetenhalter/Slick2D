package GameStates;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 * 
 * @author Pascal Heimann, Amir Hamdoun,David Schmitt
 *
 */

public class Main extends StateBasedGame{
	
	
	
	public Main() {
		super("Moin");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		
	
		System.out.println("lull");
		AppGameContainer container = new AppGameContainer(new Main());
		container.setDisplayMode(1600, 900, false);
		container.setVSync(false);
		container.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Menu());
		addState(new Test1());
		addState(new test3());
		
	}
}
