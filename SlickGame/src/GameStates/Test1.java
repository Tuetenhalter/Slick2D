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
import GameObjects.Wall.Wall;
import Tile.Tile;
import Tile.TileMap;
import idk.Camara;
import idk.MapMaker;

public class Test1 extends MyBasicGameState {

	TileMap tileMap;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		tileMap = new TileMap(50, 50, 32, 32, new Image("testdata/dungeontiles.gif"));


		container.setMinimumLogicUpdateInterval(20);
		container.setMaximumLogicUpdateInterval(20);

		camara = new Camara(0, 0, -1000, -1000, 10000, 10000);
		gameList = new ArrayList<GameObject>();

		creatMap();
		tileMap.createTileMap();

//		gameList.add(new BouncieWall(500, 500, 25, 50, null));

//		gameList.add(new Wall(0, 0, 50, 10800));
//		gameList.add(new Wall(0, 0, 19200, 50));
//
//		gameList.add(new Wall(19200 - 50, 0, 50, 10800));
//
//		gameList.add(new Wall(0, 10800 - 50, 19200, 50));

//		gameList.add(new Player(0, 0, 210, 210, 50, 50, null, new Rectangle(200, 200, 50, 50), 100, 100));

		// player = new Player(200, 200, 50, 50, 100);
		gameList.add(player);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.resetTransform();

		g.setColor(Color.red);
		g.drawString("List: " + gameList.size(), 10, 50);

		g.drawString("viewX: " + camara.getPos().getX(), 5, 140);
		g.drawString("viewY: " + camara.getPos().getY(), 5, 160);

		g.translate(-camara.getPos().getX(), -camara.getPos().getY());

		g.drawImage(tileMap.getMap(), 0, 0);

		for (GameObject gameObject : gameList) {
			gameObject.render(container, game, g, this);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		camara.camaraMove(this);
		for (int i = gameList.size() - 1; i >= 0; i--) {
			gameList.get(i).update(container, game, delta, this);
		}
		for (int i = gameList.size() - 1; i >= 0; i--) {
			if(gameList.get(i).isDestroy()) {
				gameList.remove(i);
			}
		}

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}

		if (container.getInput().isKeyPressed(Input.KEY_H)) {
			game.enterState(1);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void creatMap() throws SlickException{

		MapMaker mapMaker = new MapMaker(50, 50, "Pascal", 4, 1, 48, true);
		mapMaker.creat();

		int[][] map = mapMaker.getList();

		Random ran = new Random();
		int blockx = 32;
		int blocky = 32;
		int ranx;
		int rany;
		
		do {
			ranx = ran.nextInt(mapMaker.getWidth());
			rany = ran.nextInt(mapMaker.getHeight());

		} while (map[ranx][rany] == 1);
		
		player = new Player(ranx * blockx, rany * blocky, blockx, blocky, 100, 20);
		gameList.add(player);
		
		do {
			ranx = ran.nextInt(mapMaker.getWidth());
			rany = ran.nextInt(mapMaker.getHeight());

		} while (map[ranx][rany] == 1);
		gameList.add(new Blue(ranx*blockx, rany*blocky, blocky, blocky, 10, 20));
		
		
		
		Image image = new Image("res/test.png");
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				tileMap.setTileMap(new Tile(map, i, j, image), i, j);
			}
		}

		mapMaker.onlyEges();

		map = mapMaker.getList();

		camara.setRangex(0);
		camara.setRangey(0);
		camara.setRangex2(map.length * blockx + blockx);
		camara.setRangey2(map[0].length * blocky + blocky);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					gameList.add(new Wall(i * blockx, j * blocky, blockx, blocky));
				}
			}
		}

	}
}
