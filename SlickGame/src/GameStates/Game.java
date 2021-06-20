package GameStates;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;
import GameObjects.End;
import GameObjects.GameObject;
import GameObjects.GameObjectLife.Player;
import GameObjects.GameObjectLife.Enemy.Blue;
import GameObjects.GameObjectLife.Enemy.Enemy;
import GameObjects.Wall.Wall;
import Tile.MapMaker;
import Tile.Tile;
import Tile.TileMap;
import Weapon.Weapon;
import idk.Camara;
import idk.Images;
import idk.Stats;
import idk.Vector2D;

public class Game extends MyBasicGameState
{

	public Game(String name, int id)
	{
		super();
		this.name = name;
		this.id = id;
	}

	static final int TILE_PIXEL = 16;
	static final int TILE_WIDHT = 64;
	static final int TILE_HEIGHT = TILE_WIDHT;

	static final int TILE_ARRAY_WIDHT = 200;
	static final int TILE_ARRAY_HEIGHT = TILE_ARRAY_WIDHT;

	static final int MAP_RANDOM = 49;
	static final int MAP_IT = 20;
	static final int MAP_FILLSPICKS = 1;
	static final boolean MAP_FILLHOLLS = true;

	protected End end;

	protected String name = "Game";
	protected int id = 0;

	protected float damage = 1f;
	protected float live = 1f;
	protected float potatos = 1f;

	protected int enemys = 50;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		System.out.println("[" + name + "] Start init");

//		IntBuffer temp = BufferUtils.createIntBuffer(16);
//		GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE, temp);
//		System.out.println("Moin: " + temp.get(0));
//		
//		container.setMinimumLogicUpdateInterval(20);
//		container.setMaximumLogicUpdateInterval(20);

		camara = new Camara(0, 0, -1000, -1000, 10000, 10000);
		gameList = new ArrayList<GameObject>();

		creatMap();
		camara.setRangex2(camara.getRangex2() - container.getWidth());
		camara.setRangey2(camara.getRangey2() - container.getHeight());

		// Yellow yellow = new Yellow(1, 1, TILE_WIDHT, TILE_HEIGHT, 10, 10);
		// gameList.add(yellow);
//		player.getPos().set(500, 500);
		// yellow.getPos().set(601, 500);
		// yellow.getVel().set(0, 0);

		// ball = new Ball(600, 600, TILEWIDHT, TILEHEIGHT);
		// gameList.add(ball);

		System.out.println("[" + name + "] finised init");

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{

		g.resetTransform();

		g.setColor(Color.red);

//		g.drawString("List: " + gameList.size(), 10, 50);
//
//		g.drawString("viewX: " + camara.getPos().getX(), 5, 140);
//		g.drawString("viewY: " + camara.getPos().getY(), 5, 160);

		camara.translateCamara(container, game, g, this);

//		g.drawImage(tileMap.getMap(), 0, 0);
		g.drawImage(tileMap.getMap(), 0, 0, TILE_WIDHT * TILE_ARRAY_WIDHT, TILE_HEIGHT * TILE_ARRAY_HEIGHT, 0, 0,
				tileMap.getMap().getWidth(), tileMap.getMap().getHeight());

		for (GameObject gameObject : gameList)
		{
			gameObject.render(container, game, g, this);
		}

		g.resetTransform();
		g.setColor(Color.gray);
		g.fillRect(10, 10, 510, 50);
		if (player.getLive() > 0)
		{
			g.setColor(Color.red);
			g.fillRect(10, 10, 500 * (float) (player.getLive() / player.getMaxLive() + 0.0) + 10, 50);
		}
		g.drawString(name, 10, 60);
		g.drawString("Potatos:" + Stats.potatos, 10, 75);

		player.getWeapon().renderGUI(container, game, g, this);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{

		if (end.getHitBox().intersects(player.getHitBox()))
		{
			Stats.save();
			game.getState(id + 101).init(container, game);
			game.enterState(id + 101);
		}

		camara.camaraMove(this, container, delta);
		for (int i = gameList.size() - 1; i >= 0; i--)
		{
			gameList.get(i).update(container, game, delta, this);
		}

		for (int i = gameList.size() - 1; i >= 0; i--)
		{
			if (gameList.get(i).isDestroy())
			{
				if (getGameList().get(i) instanceof Enemy)
				{
					Stats.potatos += 10 * potatos;
				}
				gameList.remove(i);
			}
		}

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(States.PAUSEMENU.getState());
		}

		if (container.getInput().isKeyPressed(Input.KEY_J))
		{
			init(container, game);
		}

		if (container.getInput().isKeyPressed(Input.KEY_T))
		{
			player.setPos(camara.mousePos(container));
		}

	}

	@Override
	public int getID()
	{
		return States.GAME.getState() + id;
	}

	public void creatMap() throws SlickException
	{

		Random ran = new Random();
		random = ran;
		MapMaker mapMaker = new MapMaker(TILE_ARRAY_HEIGHT, TILE_ARRAY_HEIGHT, ran, MAP_IT, MAP_FILLSPICKS, MAP_RANDOM,
				MAP_FILLHOLLS);
		tileMap = new TileMap(TILE_ARRAY_WIDHT, TILE_ARRAY_HEIGHT, TILE_PIXEL, TILE_PIXEL);
		mapMaker.creat();
		int[][] map = mapMaker.getList();

		// Place Player
		int ranx;
		int rany;
		boolean bool;

		int cornerx = 0;
		int cornery = 0;

		// loop for Player pos
		float distance = 1f;
		do
		{
			bool = false;
			ranx = ran.nextInt(mapMaker.getWidth());
			rany = ran.nextInt(mapMaker.getHeight());

			if ((ranx - cornerx) * (ranx - cornerx) + (rany - cornery) * (rany - cornery) < distance * distance)
			{
				distance += 0.1f;
				if (map[ranx][rany] == 0)
				{
					int n = 1;
					if (ranx != 0 && rany != 0 && ranx != TILE_ARRAY_WIDHT && rany != TILE_ARRAY_HEIGHT)
					{
						for (int i = ranx - n; i < ranx + n + 1; i++)
						{
							for (int j = rany - n; j < rany + n + 1; j++)
							{
								if (map[i][j] == 1)
								{
									bool = true;
								}
							}
						}
					}
					else
					{
						bool = true;
					}
				}
				else
				{
					bool = true;
				}
			}
			else
			{
				bool = true;
			}
		} while (bool);

		// waffe
		Weapon sniper = Stats.weapon;

		player = new Player(ranx * TILE_WIDHT, rany * TILE_HEIGHT, TILE_WIDHT, TILE_HEIGHT, sniper);
		gameList.add(player);

		// loop for end

		cornerx = TILE_ARRAY_WIDHT;
		cornery = TILE_ARRAY_HEIGHT;

		distance = 10f;
		do
		{
			bool = false;
			ranx = ran.nextInt(mapMaker.getWidth());
			rany = ran.nextInt(mapMaker.getHeight());

			if ((ranx - cornerx) * (ranx - cornerx) + (rany - cornery) * (rany - cornery) < distance * distance)
			{
				distance += 0.1f;
				if (map[ranx][rany] == 0)
				{
					int n = 1;
					if (ranx != 0 && rany != 0 && ranx != TILE_ARRAY_WIDHT && rany != TILE_ARRAY_HEIGHT)
					{
						for (int i = ranx - n; i < ranx + n + 1; i++)
						{
							for (int j = rany - n; j < rany + n + 1; j++)
							{
								if (map[i][j] == 1)
								{
									bool = true;
								}
							}
						}
					}
					else
					{
						bool = true;
					}
				}
				else
				{
					bool = true;
				}
			}
			else
			{
				bool = true;
			}
		} while (bool);

		end = new End(new Vector2D(ranx * TILE_WIDHT, rany * TILE_HEIGHT), new Vector2D(), new Vector2D(), 64, 64,
				new Circle(ranx * TILE_WIDHT + TILE_WIDHT / 2, rany * TILE_HEIGHT + TILE_HEIGHT / 2, 32));
		gameList.add(end);

		// Place Blue
		for (int i = 0; i < enemys; i++)
		{
			do
			{
				ranx = ran.nextInt(mapMaker.getWidth());
				rany = ran.nextInt(mapMaker.getHeight());

			} while (map[ranx][rany] == 1);
			Blue blue = new Blue(ranx * TILE_WIDHT, rany * TILE_HEIGHT, TILE_HEIGHT, TILE_HEIGHT);
			blue.setLive(blue.getLive() * live);
			gameList.add(blue);

		}

		// Draw The Tile Map
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[0].length; j++)
			{
				tileMap.setTileMap(new Tile(map, i, j, Images.wall), i, j);
			}
		}
		tileMap.createTileMap();

		// Make wall Objects
		mapMaker.onlyEges();
		map = mapMaker.getList();

		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[0].length; j++)
			{
				if (map[i][j] == 1)
				{
					gameList.add(new Wall(i * TILE_WIDHT, j * TILE_HEIGHT, TILE_WIDHT, TILE_HEIGHT));
				}
			}
		}

		// set Camare range

		camara.setRangex(0);
		camara.setRangey(0);
		camara.setRangex2(map.length * TILE_WIDHT);
		camara.setRangey2(map[0].length * TILE_HEIGHT);

	}

	@Override
	public void mouseWheelMoved(int newValue)
	{

		if (newValue < 0)
		{
			camara.setTargedZoom(camara.getTargedZoom() * .9f);
		}
		else
		{
			camara.setTargedZoom(camara.getTargedZoom() * 1.1f);
		}

		super.mouseWheelMoved(newValue);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getDamage()
	{
		return damage;
	}

	public void setDamage(float damage)
	{
		this.damage = damage;
	}

	public float getLive()
	{
		return live;
	}

	public void setLive(float live)
	{
		this.live = live;
	}

	public float getPotatos()
	{
		return potatos;
	}

	public void setPotatos(float potatos)
	{
		this.potatos = potatos;
	}

	public int getEnemys()
	{
		return enemys;
	}

	public void setEnemys(int enemys)
	{
		this.enemys = enemys;
	}
}
