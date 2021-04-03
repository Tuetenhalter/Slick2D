package GameObjects.GameObjectLife.Enemy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.Bullet;
import GameObjects.GameObjectLife.Player;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Blue extends Enemy {

	public Blue(Vector2D pos, Vector2D vel, Vector2D acc, float width, float height, Shape hitBox, float live,
			float maxLive, int shootDelay, int shootDelayMax) {
		super(pos, vel, acc, width, height, hitBox, live, maxLive, shootDelay, shootDelayMax);
		// TODO Auto-generated constructor stub
	}

	public Blue(float x, float y, float width, float height, float maxlive, int shootDelayMax) {
		super(x, y, width + 1, height + 1, maxlive, shootDelayMax);
		setHeight(height);
		setWidth(width);
		setShootAngel(0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {
		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		g.setColor(Color.blue);
		g.fill(getHitBox());
		Player player = mygame.player;
		g.drawLine(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, getX() + getWidth() / 2, getY() + getHeight() / 2);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame) {

		if (getLive() <= 0) {
			setDestroy(true);
		}

		Player player = mygame.player;
		
		double distanceX = (getX() + getWidth() / 2) - (player.getX() + player.getWidth() / 2);
		double distanceY = (getY() + getHeight() / 2) - (player.getY() + player.getHeight() / 2);
		double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

		double angel = Math.asin(distanceY / distance);
		angel = Math.toDegrees(angel);
		if (distanceX>0) {
			angel = 180 - angel;
		} else {
			if (distanceY<0) {
				angel = angel + 90 + 270;
			}
		}
		

		if(Math.abs(angel-getShootAngel()) < 180) {
			if(angel < getShootAngel()) {
				setShootAngel(getShootAngel()-1f);
			}else {
				setShootAngel(getShootAngel()+1f);
			}
		} else {
			if(angel > getShootAngel()) {
				setShootAngel(getShootAngel()-1f);
			}else {
				setShootAngel(getShootAngel()+1f);
			}
		}
		
		if(getShootAngel() > 360) {
			setShootAngel(getShootAngel()-360);
		}
		
		if(getShootAngel() < 0) {
			setShootAngel(getShootAngel()+360);
		}
		
		if(Math.abs(getShootAngel()-angel) < 1) {
			setShootAngel((float) angel);
		}
		
		if (getShootDelay() <= getShootDelayMax()) {
			setShootDelay(getShootDelay() + 1);
		}

		if (getShootDelay() > getShootDelayMax()) {
			if (getPos().distanceSq(mygame.player.getPos()) < 10000000) {

				Bullet bullet = new Bullet(getPos().clone().add(getWidth() / 2 - 5, getHeight() / 2 - 5),
						mygame.player.getPos().clone().add(mygame.player.getWidth() / 2, mygame.player.getHeight() / 2),
						10, 10, 10);

				bullet.setVel(new Vector2D((float) Math.sin(Math.toRadians(getShootAngel()+90)), (float) Math.cos(Math.toRadians(getShootAngel()+90))));
				bullet.setGroup(Bullet.GROUP_ENEMY);
				mygame.gameList.add(bullet);
				setShootDelay(0);
			}

		}
	}

}
