package GameObjects.GameObjectLife.Gegner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObjectLife.GameObjectLife;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Blue extends GameObjectLife {

	public Blue(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive) {
		super(pos, vel, acc, width, height, hitBox, live, maxLive);
		// TODO Auto-generated constructor stub
	}

	public Blue(float x, float y, float width, float height, float maxlive) {
		super(x, y, width + 1, height + 1, maxlive);
		setHeight(height);
		setWidth(width);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {
		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		g.setColor(Color.blue);
		g.fill(getHitBox());

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {

		if (getPos().distanceSq(mygame.player.getPos()) < 10000000) {
			Bullet bullte = new Bullet(getPos().clone().add(getWidth() / 2 - 5, getHeight() / 2 - 5),
					mygame.player.getPos().clone().add(mygame.player.getWidth() / 2, mygame.player.getHeight() / 2), 10,
					10, 10);
			mygame.gameList.add(bullte);
		}

	}

}
