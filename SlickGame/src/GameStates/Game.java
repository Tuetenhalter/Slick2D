package GameStates;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameObjects.GameObjectLife.Player;
import GameObjects.GameObjectLife.Enemy.Blue;
import GameObjects.GameObjectLife.Enemy.Red;
import GameObjects.GameObjectLife.Enemy.Yellow;
import GameObjects.Wall.Wall;
import Tile.Tile;
import Tile.TileMap;
import idk.Camara;
import idk.MapMaker;

public class Game extends MyBasicGameState {
	
	static final int TILEWIDHT = 32;
	static final int TILEHEIGHT = TILEWIDHT;
	
	static final int TILEARRAYWIDHT = 100;
	static final int TILEARRAYHEIGHT = TILEARRAYWIDHT;
	
	static final int MAPRANDOM = 48;
	static final int MAPIT = 4;
	static final int MAPFILLSPICKS = 2;
	static final boolean MAPFILLHOLLS = false;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		System.out.println("[Game] Start init");

		tileMap = new TileMap(TILEARRAYWIDHT, TILEARRAYHEIGHT, TILEWIDHT, TILEHEIGHT, new Image("testdata/dungeontiles.gif"));

		container.setMinimumLogicUpdateInterval(20);
		container.setMaximumLogicUpdateInterval(20);

		camara = new Camara(0, 0, -1000, -1000, 10000, 10000);
		gameList = new ArrayList<GameObject>();

		creatMap();
		camara.setRangex2(camara.getRangex2() - container.getWidth());
		camara.setRangey2(camara.getRangey2() - container.getHeight());
		tileMap.createTileMap();
		
		Yellow yellow = new Yellow(1, 1, TILEWIDHT, TILEHEIGHT, 10, 10);
		gameList.add(yellow);
		player.getPos().set(500, 500);
		yellow.getPos().set(601, 500);
		yellow.getVel().set(0, 0);
		
		gameList.add(new Red(600, 600, TILEWIDHT, TILEHEIGHT));
		
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

		g.translate(-camara.getPos().getX(), -camara.getPos().getY());

		g.drawImage(tileMap.getMap(), 0, 0);

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

		camara.camaraMove(this, container);

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
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.GAME.getState();
	}

	public void creatMap() throws SlickException {

		MapMaker mapMaker = new MapMaker(TILEARRAYHEIGHT, TILEARRAYHEIGHT, "Pascal", MAPIT, MAPFILLSPICKS, MAPRANDOM, MAPFILLHOLLS);
		mapMaker.creat();

		int[][] map = mapMaker.getList();

		Random ran = new Random();
		int ranx;
		int rany;

		do {
			ranx = ran.nextInt(mapMaker.getWidth());
			rany = ran.nextInt(mapMaker.getHeight());

		} while (map[ranx][rany] == 1);

		player = new Player(ranx * TILEWIDHT, rany * TILEHEIGHT, TILEWIDHT, TILEHEIGHT, 100, 10);
		gameList.add(player);

		do {
			ranx = ran.nextInt(mapMaker.getWidth());
			rany = ran.nextInt(mapMaker.getHeight());

		} while (map[ranx][rany] == 1);

		gameList.add(new Blue(ranx * TILEWIDHT, rany * TILEHEIGHT, TILEHEIGHT, TILEHEIGHT));

		Image image = new Image("res/test.png");
		image.setFilter(Image.FILTER_NEAREST);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				tileMap.setTileMap(new Tile(map, i, j, image), i, j);
			}
		}

		mapMaker.onlyEges();

		map = mapMaker.getList();

		camara.setRangex(0);
		camara.setRangey(0);
		camara.setRangex2(map.length * TILEWIDHT);
		camara.setRangey2(map[0].length * TILEHEIGHT);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					gameList.add(new Wall(i * TILEWIDHT, j * TILEHEIGHT, TILEWIDHT, TILEHEIGHT));
				}
			}
		}

	}
}
