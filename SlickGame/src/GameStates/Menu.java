package GameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import GUI.Button;

public class Menu extends BasicGameState{
	
	public Button play;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		play = new Button("Play", .25f, .25f, .25f, .25f, container);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		play.render(container, game, g);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		play.update(container, game, delta);
		
		
		if(play.isClicked()) {
			play.setClicked(false);
			
			
			Main.main(null);
			container.exit();
		}
		
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
