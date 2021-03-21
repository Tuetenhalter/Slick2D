package GameStates;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import idk.Camara;
import test.GameObject;
import test.Player;

public abstract class MyBasicGameState extends BasicGameState {
	
	public ArrayList<GameObject> gameList;
	
	public Camara camara;
	public Player player;

	public abstract void init(GameContainer container, StateBasedGame game) throws SlickException;

	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;

	public abstract void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;
	
	public abstract int getID();

}
