package Tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile
{

	private float x;
	private float y;
	private float x2;
	private float y2;
	private Image image;

	public Tile(float x, float y, float x2, float y2, Image image)
	{
		super();
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.image = image;
	}

	public Tile(int[][] map, int i, int j, Image image) throws SlickException
	{
		int up, down, left, right, leftUp, leftDown, rightUp, rightDown;

		int tilelenght = 32;

		if (map[i][j] == 1)
		{

			// inti var up down ....
			if (i - 1 > 0)
			{
				left = map[i - 1][j];

				if (j - 1 > 0)
				{
					leftUp = map[i - 1][j - 1];
				}
				else
				{
					leftUp = 1;
				}

				if (j + 1 < map[0].length)
				{
					leftDown = map[i - 1][j + 1];
				}
				else
				{
					leftDown = 1;
				}

			}
			else
			{
				leftUp = 1;
				leftDown = 1;
				left = 1;
			}

			if (j - 1 > 0)
			{
				up = map[i][j - 1];
			}
			else
			{
				up = 1;
			}

			if (i + 1 < map.length)
			{
				right = map[i + 1][j];

				if (j - 1 > 0)
				{
					rightUp = map[i + 1][j - 1];
				}
				else
				{
					rightUp = 1;
				}

				if (j + 1 < map[0].length)
				{
					rightDown = map[i + 1][j + 1];
				}
				else
				{
					rightDown = 1;
				}

			}
			else
			{
				right = 1;
				rightDown = 1;
				rightUp = 1;
			}

			if (j + 1 < map[0].length - 1)
			{
				down = map[i][j + 1];
			}
			else
			{
				down = 1;
			}

			// Which tile

//			setTile(1, 1, tilelenght, image);
			setTile(0, 0, tilelenght, new Image("testdata/dungeontiles.gif"));
			
//			 -------------------- Beginn erste Reihe
			
			if (left == 0 && up == 0 && right == 1 && down == 1 && rightDown == 1)
			{
				setTile(0, 0, tilelenght, image);
			}

			if (up == 0 && left == 1 && right == 1 && down == 1 && leftDown == 1 && rightDown == 1)
			{
				setTile(1, 0, tilelenght, image);
			}

			if (left == 1 && down == 1 && leftDown == 1 && up == 0 && right == 0)
			{
				setTile(2, 0, tilelenght, image);
			}

			if (down == 1 && up == 0 && left == 0 && right == 0)
			{
				setTile(3, 0, tilelenght, image);
			}

			if (up == 0 && left == 0 && rightDown == 0 && right == 1 && down == 1)
			{
				setTile(4, 0, tilelenght, image);
			}

			if (up == 0 && left == 1 && rightDown == 0 && right == 1 && down == 1 && leftDown == 1)
			{
				setTile(5, 0, tilelenght, image);
			}

			if (up == 0 && left == 1 && rightDown == 1 && right == 1 && down == 1 && leftDown == 0)
			{
				setTile(6, 0, tilelenght, image);
			}

			if (up == 0 && right == 0 && leftDown == 0 && left == 1 && down == 1)
			{
				setTile(7, 0, tilelenght, image);
			}

			if (up == 0 && left == 1 && rightDown == 0 && right == 1 && down == 1 && leftDown == 0)
			{
				setTile(8, 0, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 1 && rightDown == 1
					&& rightUp == 0)
			{
				setTile(9, 0, tilelenght, image);
			}

//			----------------------------- Beginn zweite Reihe

			if (left == 0 && up == 1 && rightUp == 1 && right == 1 && rightDown == 1 && down == 1)
			{
				setTile(0, 1, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 1 && rightDown == 1
					&& rightUp == 1)
			{
				setTile(1, 1, tilelenght, image);
			}

			if (right == 0 && up == 1 && leftUp == 1 && left == 1 && leftDown == 1 && down == 1)
			{
				setTile(2, 1, tilelenght, image);
			}
			if (down == 1 && up == 1 && left == 0 && right == 0)
			{
				setTile(3, 1, tilelenght, image);
			}
			
			if (up == 1 && down == 1 && left == 0 && right == 1  && rightDown == 0 && rightUp == 1)
			{
				setTile(4, 1, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 1 && rightDown == 0
					&& rightUp == 1)
			{
				setTile(5, 1, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 1 && rightDown == 1
					&& rightUp == 1)
			{
				setTile(6, 1, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 0 && leftDown == 0 && leftUp == 1)
			{
				setTile(7, 1, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 1 && rightDown == 0
					&& rightUp == 1)
			{
				setTile(8, 1, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 0 && rightDown == 0
					&& rightUp == 1)
			{
				setTile(9, 1, tilelenght, image);
			}

			// ---------------- Beginn dritte Reihe
			
			if (left == 0 && down == 0 && up == 1 && right == 1 && rightUp == 1)
			{
				setTile(0, 2, tilelenght, image);
			}

			if (down == 0 && left == 1 && right == 1 && up == 1 && leftUp == 1 && rightUp == 1)
			{
				setTile(1, 2, tilelenght, image);
			}

			if (right == 0 && down == 0 && up == 1 && left == 1 && leftUp == 1)
			{
				setTile(2, 2, tilelenght, image);
			}

			if (up == 1 && down == 0 && left == 0 && right == 0)
			{
				setTile(3, 2, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 0 && right == 1 && rightDown == 1 && rightUp == 0)
			{
				setTile(4, 2, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 1 && rightDown == 1
					&& rightUp == 0)
			{
				setTile(5, 2, tilelenght, image);
			}

			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 0 && rightDown == 1
					&& rightUp == 1)
			{
				setTile(6, 2, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 0 && leftDown == 1 && leftUp == 0)
			{
				setTile(7, 2, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 0 && rightDown == 1
					&& rightUp == 0)
			{
				setTile(8, 2, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 0 && rightDown == 1
					&& rightUp == 0)
			{
				setTile(9, 2, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 0 && rightDown == 0
					&& rightUp == 0)
			{
				setTile(10, 2, tilelenght, image);
			}

//			--------------- Beginn vierte Reihe
			if (up == 0 && down == 0 && left == 0 && right == 1)
			{
				setTile(0, 3, tilelenght, image);
			}
			if (up == 0 && down == 0 && left == 1 && right == 1)
			{
				setTile(1, 3, tilelenght, image);
			}
			if (up == 0 && down == 0 && left == 1 && right == 0)
			{
				setTile(2, 3, tilelenght, image);
			}
			if (up == 0 && down == 0 && left == 0 && right == 0)
			{
				setTile(3, 3, tilelenght, image);
			}
			if (up == 1 && down == 0 && left == 0 && right == 1 && rightUp == 0)
			{
				setTile(4, 3, tilelenght, image);
			}
			if (up == 1 && down == 0 && left == 1 && right == 1 && leftUp == 1 && rightUp == 0)
			{
				setTile(5, 3, tilelenght, image);
			}
			if (up == 1 && down == 0 && left == 1 && right == 1 && leftUp == 0 && rightUp == 1)
			{
				setTile(6, 3, tilelenght, image);
			}
			if (up == 1 && down == 0 && left == 1 && right == 0 && leftUp == 0)
			{
				setTile(7, 3, tilelenght, image);
			}
			if (up == 1 && down == 0 && left == 1 && right == 1 && leftUp == 0 && rightUp == 0)
			{
				setTile(8, 3, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 0 && rightDown == 0
					&& rightUp == 1)
			{
				setTile(9, 3, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 1 && rightDown == 0
					&& rightUp == 0)
			{
				setTile(10, 3, tilelenght, image);
			}

//			Beginn der fünften Reihe I dont know, ob ich bei 0 oder 4 starten soll, habe mal bei 0 gestartet.

			if (up == 1 && down == 1 && left == 0 && right == 1 && rightDown == 0 && rightUp == 0)
			{
				setTile(4, 4, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 1 && leftUp == 1 && rightDown == 0
					&& rightUp == 0)
			{
				setTile(5, 4, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 0 && rightDown == 1
					&& rightUp == 1)
			{
				setTile(6, 4, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 0 && leftDown == 0 && leftUp == 0)
			{
				setTile(7, 4, tilelenght, image);
			}
			if (up == 1 && down == 1 && left == 1 && right == 1 && leftDown == 0 && leftUp == 0 && rightDown == 0
					&& rightUp == 0)
			{
				setTile(8, 4, tilelenght, image);
			}
		}

	}

	public void setTile(float x, float y, int tilelenght, Image image)
	{
		this.x = x * tilelenght;
		this.y = y * tilelenght;
		this.x2 = x * tilelenght + tilelenght;
		this.y2 = y * tilelenght + tilelenght;
		this.image = image;
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getX2()
	{
		return x2;
	}

	public void setX2(float x2)
	{
		this.x2 = x2;
	}

	public float getY2()
	{
		return y2;
	}

	public void setY2(float y2)
	{
		this.y2 = y2;
	}

	public Image getImage()
	{
		return image;
	}

	public void setImage(Image image)
	{
		this.image = image;
	}
}
