package GameStates;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameObjects.GameObjectLife.Player;
import GameObjects.Wall.Wall;
import idk.Camara;

public class Test1 extends MyBasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		container.setMinimumLogicUpdateInterval(20);
		container.setMaximumLogicUpdateInterval(20);
		
		camara = new Camara(0, 0);

		gameList = new ArrayList<GameObject>();

//		gameList.add(new BouncieWall(500, 500, 25, 50, null));

		gameList.add(new Wall(0, 0, 25, 10800));
		gameList.add(new Wall(0, 0, 19200, 25));

		gameList.add(new Wall(19200 - 25, 0, 25, 10800));

		gameList.add(new Wall(0, 10800 - 25, 19200, 25));

//		gameList.add(new Player(0, 0, 210, 210, 50, 50, null, new Rectangle(200, 200, 50, 50), 100, 100));
		player = new Player(200, 200, 50, 50, 100);
		gameList.add(player);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		
		g.translate(-camara.getPos().getX(), -camara.getPos().getY());
		for (GameObject gameObject : gameList) {
			gameObject.render(container, game, g, this);
		}
		
		
		g.setColor(Color.red);
		g.drawString("List: " + gameList.size(), 10, 50);

		g.drawString("viewX: " + camara.getPos().getX(), 5, 140);
		g.drawString("viewY: " + camara.getPos().getY(), 5, 160);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		
		camara.camaraMove(this);
		for (int i = gameList.size() - 1; i >= 0; i--) {

			gameList.get(i).update(container, game, delta, this);
		}

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
