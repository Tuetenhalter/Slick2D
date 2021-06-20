package Weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameObjects.GameObject;
import GameStates.MyBasicGameState;
import idk.Vector2D;

public abstract class Weapon
{

	private int shootDelay = 0;
	private int shootDelayMax;

	private int ammunition;
	private int ammunitionMax;

	private int reloadtime;

	public Weapon()
	{
		super();
	}

	public abstract void shoot(GameObject me, Vector2D target, GameContainer container, StateBasedGame game, int delta,
			MyBasicGameState mygame);

	public void renderGUI(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame)
			throws SlickException
	{
		for (int i = 0; i < ammunitionMax; i++)
		{

			g.resetTransform();
			if (i + 1 > ammunition)
			{
				g.setColor(Color.gray);
			}
			else
			{
				g.setColor(Color.red);
			}
			g.fillRect(1540 - i * 60, 840, 50, 50);

			mygame.getCamara().translateCamara(container, game, g, mygame);

		}
	}

	public abstract String getName();

	public void shoot(GameObject me, GameObject target, GameContainer container, StateBasedGame game, int delta,
			MyBasicGameState mygame)
	{
		shoot(me, target.getPos(), container, game, delta, mygame);
	}

	public int getShootDelay()
	{
		return shootDelay;
	}

	public void setShootDelay(int shootDelay)
	{
		this.shootDelay = shootDelay;
	}

	public int getShootDelayMax()
	{
		return shootDelayMax;
	}

	public void setShootDelayMax(int shootDelayMax)
	{
		this.shootDelayMax = shootDelayMax;
	}

	public int getAmmunition()
	{
		return ammunition;
	}

	public void setAmmunition(int ammunition)
	{
		this.ammunition = ammunition;
	}

	public int getAmmunitionMax()
	{
		return ammunitionMax;
	}

	public void setAmmunitionMax(int ammunitionMax)
	{
		this.ammunitionMax = ammunitionMax;
	}

	public int getReloadtime()
	{
		return reloadtime;
	}

	public void setReloadtime(int reloadtime)
	{
		this.reloadtime = reloadtime;
	}
}
