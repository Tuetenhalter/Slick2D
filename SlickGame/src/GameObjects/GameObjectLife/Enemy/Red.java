package GameObjects.GameObjectLife.Enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Red extends Enemy {

	static final int MAXLIVE = 3;
	static final int DEMAGE = 10;

	public Red(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive) {
		super(pos, vel, acc, width, height, hitBox, live, maxLive, 0, 0);
		// TODO Auto-generated constructor stub
	}

	public Red(float x, float y, float width, float height) {
		super(x, y, width + 1, height + 1, MAXLIVE, 0);
		setHeight(height);
		setWidth(width);
		setShootAngel(0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {
		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		g.setColor(Color.red);
		g.fill(getHitBox());

//		Player player = mygame.getPlayer();
//		g.drawLine(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2,
//				getX() + getWidth() / 2, getY() + getHeight() / 2);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {

		if (getLive() <= 0) {
			setDestroy(true);
		}

		if (mygame.getPlayer().getHitBox().intersects(getHitBox())) {
			setDestroy(true);
			mygame.getPlayer().setLive(mygame.getPlayer().getLive() - DEMAGE);

		}

		setAcc(mygame.getPlayer().getPos().clone().sub(getPos()).setMagnitude(1000f));

		getVel().add(getAcc());
		getPos().add(getVel());

	}

}
