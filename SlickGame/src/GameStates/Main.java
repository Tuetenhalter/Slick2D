package GameStates;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame{
	
	
	
	public Main() {
		super("Moin");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		
	
		System.out.println("lull");
		AppGameContainer container = new AppGameContainer(new Main());
		container.setDisplayMode(container.getScreenWidth(), container.getScreenHeight(), false);
		container.setVSync(true);
		container.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Test1());
		addState(new Menu());
		addState(new test3());
				
	}
}
