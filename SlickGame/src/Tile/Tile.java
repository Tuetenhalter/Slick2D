package Tile;

public class Tile {

	public static final Tile FLOOR = new Tile(0, 0, 32, 32);
	public static final Tile WALL = new Tile(0, 128, 32, 128 + 32);

	private float x;
	private float y;
	private float x2;
	private float y2;

	public Tile(float x, float y, float x2, float y2) {
		super();
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}
}
