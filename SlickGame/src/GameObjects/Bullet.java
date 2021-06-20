package GameObjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;
import GameObjects.GameObjectLife.Player;
import GameObjects.GameObjectLife.Enemy.Enemy;
import GameObjects.Wall.BouncieWall;
import GameObjects.Wall.Wall;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public class Bullet extends GameObject
{

	static final public int GROUP_PLAYER = 0;
	static final public int GROUP_ENEMY = 1;

	private int bounce = 0;
	private int group;
	private int liveTime = 10000;
	private int damage = 1;

	public Bullet(float x, float y, float width, float height)
	{
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

//	public Bullet(float x, float y, float shootX, float shootY, float speed, float width, float height) {
//		double distance = Math.sqrt((shootX - x) * (shootX - x) + (shootY - y) * (shootY - y));
//		double shootSpeedX = (speed / distance) * (shootX - x);
//		double shootSpeedY = (speed / distance) * (shootY - y);
//
//		setSpeedX(shootSpeedX);
//		setSpeedY(shootSpeedY);
//		
//		
//		
//		
//	}

	public Bullet(Vector2D pos, Vector2D vel, float width, float height)
	{
		super(pos.clone().sub(width / 2, height / 2), vel, new Vector2D(0, 0), width, height,
				new Circle(pos.getX() + width / 2, pos.getY() + height / 2, width / 2));

	}

	public Bullet(Vector2D pos, Vector2D shootPos, float speed, float width, float height)
	{
		super(pos.clone().sub(width / 2, height / 2), shootPos.sub(pos).setMagnitude(speed), new Vector2D(0, 0), width,
				height, new Circle(pos.getX() + width / 2, pos.getY() + height / 2, width / 2));

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame)
	{

		getHitBox().setX(getPos().getX());
		getHitBox().setY(getPos().getY());
		g.setColor(Color.red);
		g.fill(getHitBox());

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta, MyBasicGameState mygame)
	{

		for (GameObject gameObject : mygame.getGameList())
		{
			if (gameObject instanceof Wall)
			{
				colltiontoWall((Wall) gameObject, delta);
			}

			if (gameObject instanceof BouncieWall)
			{
				colltiontoBouncieWall((BouncieWall) gameObject);
			}

			if (gameObject instanceof Enemy)
			{
				if (group == GROUP_PLAYER)
				{
					if (gameObject.getHitBox().intersects(getHitBox()))
					{
						setDestroy(true);
						Enemy blue = (Enemy) gameObject;
						blue.setLive(blue.getLive() - damage);
					}
				}
			}

			if (gameObject instanceof Player)
			{
				if (group == GROUP_ENEMY)
				{
					if (gameObject.getHitBox().intersects(getHitBox()))
					{
						setDestroy(true);
						Player player = (Player) gameObject;
						player.setLive(player.getLive() - damage);
					}
				}
			}
		}
		liveTime -= delta;
		if (liveTime < 0)
		{
			setDestroy(true);
		}

		getPos().add(getVel().clone().mul(delta / 1000f));

	}

	public void colltiontoWall(Wall gameObject, int delta)
	{

		float x = getX();
		float y = getY();

		float speedx = getSpeedX() * delta / 1000f;
		float speedy = getSpeedY() * delta / 1000f;

		double height = getHeight();
		double width = getWidth();

		double x2 = gameObject.getPos().getX();
		double y2 = gameObject.getPos().getY();

		double height2 = gameObject.getHeight();
		double width2 = gameObject.getWidth();

//		System.out.println("x: " + x);
//		System.out.println("y: " + y);
//
//		System.out.println("height: " + height);
//		System.out.println("width: " + width);
//
//		System.out.println("x2: " + x2);
//		System.out.println("y2: " + y2);
//
//		System.out.println("height2: " + height2);
//		System.out.println("width2: " + width2);
//
//		System.out.println("---------------------------");

		// Colotion

		// Links
		if (x + speedx < x2 + width2 && x + speedx > x2 && y2 < y + height && y2 + height2 > y)
		{
			if (bounce > 0)
			{
				getVel().setX(getVel().getX() * -1);
				bounce--;
			}
			else
			{
				setDestroy(true);
			}
			return;
		}
		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y)
		{
			if (bounce > 0)
			{
				getVel().setX(getVel().getX() * -1);
				bounce--;
			}
			else
			{
				setDestroy(true);
			}
			return;
		}
		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x)
		{
			if (bounce > 0)
			{
				getVel().setY(getVel().getY() * -1);
				bounce--;
			}
			else
			{
				setDestroy(true);
			}
			return;
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x)
		{
			if (bounce > 0)
			{
				getVel().setY(getVel().getY() * -1);
				bounce--;
			}
			else
			{
				setDestroy(true);
			}
			return;
		}

	}

	public void colltiontoBouncieWall(BouncieWall gameObject)
	{

		double x = getPos().getX();
		double y = getPos().getY();

		double speedx = getVel().getX();
		double speedy = getVel().getY();

		double height = getHeight();
		double width = getWidth();

		double x2 = gameObject.getPos().getX();
		double y2 = gameObject.getPos().getY();

		double height2 = gameObject.getHeight();
		double width2 = gameObject.getWidth();

//		System.out.println("x: " + x);
//		System.out.println("y: " + y);
//
//		System.out.println("height: " + height);
//		System.out.println("width: " + width);
//
//		System.out.println("x2: " + x2);
//		System.out.println("y2: " + y2);
//
//		System.out.println("height2: " + height2);
//		System.out.println("width2: " + width2);
//
//		System.out.println("---------------------------");

		// Colotion

		// Links
		if (x + speedx < x2 + width2 && x + speedx > x2 && y2 < y + height && y2 + height2 > y)
		{
			getVel().mul(-2);

			return;
		}
		// Rechts
		if (x + speedx + width > x2 && x + speedx + width < x2 + width2 && y2 < y + height && y2 + height2 > y)
		{
			getVel().mul(-2);
			return;
		}
		// oben
		if (y + speedy < y2 + height2 && y + speedy > y2 && x2 < x + width && x2 + width2 > x)
		{
			getVel().mul(-2);
			return;
		}
		// untem
		if (y + speedy + height > y2 && y + speedy + height < y2 + height2 && x2 < x + width && x2 + width2 > x)
		{
			getVel().mul(-2);
			return;
		}

	}

	public int getBounce()
	{
		return bounce;
	}

	public void setBounce(int bounce)
	{
		this.bounce = bounce;
	}

	public int getGroup()
	{
		return group;
	}

	public void setGroup(int group)
	{
		this.group = group;
	}

	public int getLiveTime()
	{
		return liveTime;
	}

	public void setLiveTime(int liveTime)
	{
		this.liveTime = liveTime;
	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

}
