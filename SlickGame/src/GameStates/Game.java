package GameStates;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;

import GameObjects.GameObjectLife.Player;
import GameObjects.GameObjectLife.Enemy.Blue;
import GameObjects.GameObjectLife.Enemy.Yellow;
import GameObjects.Wall.Wall;
import Tile.MapMaker;
import Tile.Tile;
import Tile.TileMap;
import idk.Camara;
import idk.Images;
import idk.Vector2D;

public class Game extends MyBasicGameState {

	static final int TILE_PIXEL = 16;
	static final int TILE_WIDHT = 64;
	static final int TILE_HEIGHT = TILE_WIDHT;

	static final int TILE_ARRAY_WIDHT = 200;
	static final int TILE_ARRAY_HEIGHT = TILE_ARRAY_WIDHT;

	static final int MAP_RANDOM = 49;
	static final int MAP_IT = 20;
	static final int MAP_FILLSPICKS = 1;
	static final boolean MAP_FILLHOLLS = true;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		System.out.println("[Game] Start init");

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

		Yellow yellow = new Yellow(1, 1, TILE_WIDHT, TILE_HEIGHT, 10, 10);
		gameList.add(yellow);
//		player.getPos().set(500, 500);
		yellow.getPos().set(601, 500);
		yellow.getVel().set(0, 0);

		// ball = new Ball(600, 600, TILEWIDHT, TILEHEIGHT);
		// gameList.add(ball);

		System.out.println("[Game] finised init");

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
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

		for (GameObject gameObject : gameList) {
			gameObject.render(container, game, g, this);
		}

		g.resetTransform();
		g.setColor(Color.gray);
		g.fillRect(10, 10, 510, 50);
		if (player.getLive() > 0) {
			g.setColor(Color.red);
			g.fillRect(10, 10, 500 * (float) (player.getLive() / player.getMaxLive() + 0.0) + 10, 50);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		camara.camaraMove(this, container, delta);
		for (int i = gameList.size() - 1; i >= 0; i--) {
			gameList.get(i).update(container, game, delta, this);
		}
		for (int i = gameList.size() - 1; i >= 0; i--) {
			if (gameList.get(i).isDestroy()) {
				gameList.remove(i);
			}
		}

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(States.PAUSEMENU.getState());
		}

		if (container.getInput().isKeyPressed(Input.KEY_R)) {
			init(container, game);
		}
		
		if(container.getInput().isKeyPressed(Input.KEY_T)) {
			player.setPos(camara.mousePos(container));
		}

	}

	@Override
	public int getID() {
		return States.GAME.getState();
	}

	public void creatMap() throws SlickException {

		Random ran = new Random();
		MapMaker mapMaker = new MapMaker(TILE_ARRAY_HEIGHT, TILE_ARRAY_HEIGHT, ran, MAP_IT, MAP_FILLSPICKS, MAP_RANDOM,
				MAP_FILLHOLLS);
		tileMap = new TileMap(TILE_ARRAY_WIDHT, TILE_ARRAY_HEIGHT, TILE_PIXEL, TILE_PIXEL);
		mapMaker.creat();
		int[][] map = mapMaker.getList();

		// Place Player
		int ranx;
		int rany;
		boolean bool;
		int corner = 0;
		// 0 up left
		// 1 up right
		// 2 down right
		// 3 down left

		int cornerx;
		int cornery;

		if (corner == 0 || corner == 1) {
			cornery = 0;
		} else {
			cornery = TILE_ARRAY_HEIGHT;
		}

		if (corner == 0 || corner == 3) {
			cornerx = 0;
		} else {
			cornerx = TILE_ARRAY_WIDHT;
		}

		do {
			bool = false;
			ranx = ran.nextInt(mapMaker.getWidth());
			rany = ran.nextInt(mapMaker.getHeight());

			if (map[ranx][rany] == 0) {
				if ((ranx - cornerx) * (ranx - cornerx) + (rany - cornery) * (rany - cornery) < 300 * 300) {
					int n = 3;
					for (int i = ranx-n; i < ranx+n+1; i++) {
						for (int j = rany-n; j < rany+n+1; j++) {
							if(map[i][j] == 1) {
								bool = true;
							}
						}
					}
				} else {
					bool = true;
				}
			} else {
				bool = true;
			}
		} while (bool);

		player = new Player(ranx * TILE_WIDHT, rany * TILE_HEIGHT, TILE_WIDHT, TILE_HEIGHT);
		gameList.add(player);

		// Place one Blue

		
		
		for (int i = 0; i < 100; i++) {
			do {
				ranx = ran.nextInt(mapMaker.getWidth());
				rany = ran.nextInt(mapMaker.getHeight());

			} while (map[ranx][rany] == 1);

			gameList.add(new Blue(ranx * TILE_WIDHT, rany * TILE_HEIGHT, TILE_HEIGHT, TILE_HEIGHT));

		}
		
		// Draw The Tile Map
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				tileMap.setTileMap(new Tile(map, i, j, Images.wall), i, j);
			}
		}
		tileMap.createTileMap();

		// Make wall Objects
		mapMaker.onlyEges();
		map = mapMaker.getList();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
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
	public void mouseWheelMoved(int newValue) {
		System.out.println(newValue);

		if (newValue < 0) {
			camara.setTargedZoom(camara.getTargedZoom() * .9f);
		} else {
			camara.setTargedZoom(camara.getTargedZoom() * 1.1f);
		}

		super.mouseWheelMoved(newValue);
	}
}
