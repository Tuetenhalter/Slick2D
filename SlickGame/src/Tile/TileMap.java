package Tile;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.imageout.ImageIOWriter;
import org.newdawn.slick.imageout.ImageOut;

public class TileMap
{

	private int widhtArray;
	private int heigthArray;

	private int widhtTile;
	private int heightTile;

	private Tile[][] tileMap;

	private Image image;

	private Image map;

	// Constuctor

	public TileMap(int widhtArray, int heigthArray, int widhtTile, int heightTile, Tile[][] tileMap, Image image,
			Image map)
	{
		super();
		this.widhtArray = widhtArray;
		this.heigthArray = heigthArray;
		this.widhtTile = widhtTile;
		this.heightTile = heightTile;
		this.tileMap = tileMap;
		this.image = image;
		this.map = map;
	}

	public TileMap(int widhtArray, int heigthArray, int widhtTile, int heightTile, Image image)
	{
		this(widhtArray, heigthArray, widhtTile, heightTile, new Tile[widhtArray][heigthArray], image, null);
	}

<<<<<<< HEAD
	public void createTileMap() throws SlickException
	{

		Image cimage = new Image(widhtArray * widhtTile, heigthArray * heightTile, Image.FILTER_NEAREST);
		image.setFilter(Image.FILTER_NEAREST);
=======
	public void createTileMap() throws SlickException {

		Image cimage = new Image(widhtArray * widhtTile, heigthArray * heightTile, Image.FILTER_LINEAR);
		image.setFilter(Image.FILTER_LINEAR);
>>>>>>> branch 'master' of https://github.com/Tuetenhalter/Slick2D.git
		Graphics g = cimage.getGraphics();

<<<<<<< HEAD
		for (int i = 0; i < tileMap.length; i++)
		{
			for (int j = 0; j < tileMap[0].length; j++)
			{
=======
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[0].length; j++) {
>>>>>>> branch 'master' of https://github.com/Tuetenhalter/Slick2D.git
				Tile tile = tileMap[i][j];

<<<<<<< HEAD
				if (tile != null)
				{
					// g.drawImage(image, i*widhtTile, j*heightTile, tile.getX(), tile.getY(),
					// tile.getX2(), tile.getY2());
					if (tile.getImage() != null)
					{
						g.drawImage(tile.getImage(), i * widhtTile, j * heightTile, i * widhtTile + widhtTile,
								j * heightTile + heightTile, tile.getX(), tile.getY(), tile.getX2(), tile.getY2());
=======
				if (tile != null) {
					if (tile.getImage() != null) {

						g.drawImage(tile.getImage(), i * widhtTile, j * heightTile, i * widhtTile + widhtTile,
								j * heightTile + heightTile, tile.getX(), tile.getY(), tile.getX2(), tile.getY2());

>>>>>>> branch 'master' of https://github.com/Tuetenhalter/Slick2D.git
					}

				}

			}
		}

		g.flush();

		map = cimage;
	}
<<<<<<< HEAD
=======

	// Getter Setters
>>>>>>> branch 'master' of https://github.com/Tuetenhalter/Slick2D.git

	// Getter Setters

	public int getWidhtArray()
	{
		return widhtArray;
	}

	public void setWidhtArray(int widhtArray)
	{
		this.widhtArray = widhtArray;
	}

	public int getHeigthArray()
	{
		return heigthArray;
	}

	public void setHeigthArray(int heigthArray)
	{
		this.heigthArray = heigthArray;
	}

	public int getWidhtTile()
	{
		return widhtTile;
	}

	public void setWidhtTile(int widhtTile)
	{
		this.widhtTile = widhtTile;
	}

	public int getHeightTile()
	{
		return heightTile;
	}

	public void setHeightTile(int heightTile)
	{
		this.heightTile = heightTile;
	}

	public Tile[][] getTileMap()
	{
		return tileMap;
	}

<<<<<<< HEAD
	public Tile getTileMap(int x, int y)
	{
=======
	public Tile getTileMap(int x, int y) {
>>>>>>> branch 'master' of https://github.com/Tuetenhalter/Slick2D.git
		return tileMap[x][y];
	}

	public void setTileMap(Tile[][] tileMap)
	{
		this.tileMap = tileMap;
	}

<<<<<<< HEAD
	public void setTileMap(Tile tile, int x, int y)
	{
=======
	public void setTileMap(Tile tile, int x, int y) {
>>>>>>> branch 'master' of https://github.com/Tuetenhalter/Slick2D.git
		this.tileMap[x][y] = tile;
	}

	public Image getImage()
	{
		return image;
	}

	public void setImage(Image image)
	{
		this.image = image;
	}

	public Image getMap()
	{
		return map;
	}

	public void setMap(Image map)
	{
		this.map = map;
	}

}
