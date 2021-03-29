package Tile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TileMap {

	private int widhtArray;
	private int heigthArray;

	private int widhtTile;
	private int heightTile;

	private Tile[][] tileMap;

	private Image image;

	private Image map;
	
	//Constuctor

	public TileMap(int widhtArray, int heigthArray, int widhtTile, int heightTile, Tile[][] tileMap, Image image,
			Image map) {
		super();
		this.widhtArray = widhtArray;
		this.heigthArray = heigthArray;
		this.widhtTile = widhtTile;
		this.heightTile = heightTile;
		this.tileMap = tileMap;
		this.image = image;
		this.map = map;
	}

	public TileMap(int widhtArray, int heigthArray, int widhtTile, int heightTile, Image image) {
		this(widhtArray, heigthArray, widhtTile, heightTile, new Tile[widhtArray][heigthArray], image, null);
	}
	
	public void createTileMap() throws SlickException{
		Image cimage = new Image(widhtArray*widhtTile, heigthArray*heightTile, Image.FILTER_NEAREST);
		image.setFilter(Image.FILTER_NEAREST);
		Graphics g = cimage.getGraphics();
		
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[0].length; j++) {
				Tile tile = tileMap[i][j];
				
				if(tile != null) {
					//g.drawImage(image, i*widhtTile, j*heightTile, tile.getX(), tile.getY(), tile.getX2(), tile.getY2());
					g.drawImage(image, i*widhtTile, j*heightTile, i*widhtTile+widhtTile, j*heightTile+heightTile, tile.getX(), tile.getY(), tile.getX2(), tile.getY2());
					
				}
				
			}
		}
		
		g.flush();
		
		map = cimage;
	}
	
	
	
	//Getter Setters

	public int getWidhtArray() {
		return widhtArray;
	}

	public void setWidhtArray(int widhtArray) {
		this.widhtArray = widhtArray;
	}

	public int getHeigthArray() {
		return heigthArray;
	}

	public void setHeigthArray(int heigthArray) {
		this.heigthArray = heigthArray;
	}

	public int getWidhtTile() {
		return widhtTile;
	}

	public void setWidhtTile(int widhtTile) {
		this.widhtTile = widhtTile;
	}

	public int getHeightTile() {
		return heightTile;
	}

	public void setHeightTile(int heightTile) {
		this.heightTile = heightTile;
	}

	public Tile[][] getTileMap() {
		return tileMap;
	}
	
	public Tile getTileMap(int x, int y) {
		return tileMap[x][y];
	}
	
	

	public void setTileMap(Tile[][] tileMap) {
		this.tileMap = tileMap;
	}
	
	public void setTileMap(Tile tile, int x, int y) {
		this.tileMap[x][y] = tile;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getMap() {
		return map;
	}

	public void setMap(Image map) {
		this.map = map;
	}
	
	

}
