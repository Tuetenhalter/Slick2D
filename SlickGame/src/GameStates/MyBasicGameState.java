package GameStates;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import GameObjects.GameObject;
import GameObjects.GameObjectLife.Player;
import Tile.TileMap;
import idk.Camara;

public abstract class MyBasicGameState extends BasicGameState
{

	protected ArrayList<GameObject> gameList;

	protected Camara camara;
	protected Player player;

	protected TileMap tileMap;

	protected Random random;

	public abstract void init(GameContainer container, StateBasedGame game) throws SlickException;

	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;

	public abstract void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;

	public abstract int getID();

	public ArrayList<GameObject> getGameList()
	{
		return gameList;
	}

	public void setGameList(ArrayList<GameObject> gameList)
	{
		this.gameList = gameList;
	}

	public Camara getCamara()
	{
		return camara;
	}

	public void setCamara(Camara camara)
	{
		this.camara = camara;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public TileMap getTileMap()
	{
		return tileMap;
	}

	public void setTileMap(TileMap tileMap)
	{
		this.tileMap = tileMap;
	}

	public Random getRandom()
	{
		return random;
	}

	public void setRandom(Random random)
	{
		this.random = random;
	}

}
