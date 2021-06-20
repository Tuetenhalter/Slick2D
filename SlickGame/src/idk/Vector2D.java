package idk;

import org.newdawn.slick.geom.Vector2f;
/**
 * 
 * @author Pascal
 *
 */
public class Vector2D {
	private float x, y;

	public Vector2D() {
		super();
	}

	public Vector2D(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * Makes a Vector2D with the length 1
	 * and the given theta
	 * @param theta in degress
	 */
	public Vector2D(float theta) {
		super();
		this.x = 1;
		this.y = 0;
		setTheta(theta);
	}

	/**
	 * 
	 * @return theta in Degrees
	 */
	public float getTheta() {
		float theta = (float) Math.toDegrees(Math.atan2(y, x));

		theta %= 360;
		if (theta < 0) {
			theta += 360;
		}
		return theta;
	}

	/**
	 * @param theta in Degrees
	 * @return returns this Object
	 */
	public Vector2D setTheta(float theta) {
		theta %= 360;
		if (theta < 0) {
			theta += 360;
		}

		float length = length();

		x = length * (float) Math.cos(Math.toRadians(theta));
		y = length * (float) Math.sin(Math.toRadians(theta));

		return this;
	}

	/**
	 * @param theta in Degrees
	 * @return returns this Object
	 */
	public Vector2D addTheta(float theta) {
		float newtheta = theta + getTheta();
		newtheta %= 360;
		if (newtheta < 0) {
			newtheta += 360;
		}
		setTheta(newtheta);
		return this;
	}

	public Vector2D add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2D add(Vector2D v) {
		return add(v.getX(), v.getY());
	}

	public Vector2D sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector2D sub(Vector2D v) {
		return sub(v.getX(), v.getY());
	}

	public Vector2D mul(float f) {
		x *= f;
		y *= f;
		return this;
	}

	public Vector2D dev(float f) {
		x /= f;
		y /= f;
		return this;
	}

	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public Vector2D setMagnitude(float f) {
		return normalize().mul(f);
	}

	public float lengthsqr() {
		return x * x + y * y;
	}

	public float length() {
		return magnitude();
	}

	public float distance(float vx, float vy) {
		vx -= x;
		vy -= y;
		return (float) Math.sqrt(vx * vx + vy * vy);
	}

	public float distance(Vector2D v) {
		return distance(v.getX(), v.getY());
	}

	public float distanceSq(float vx, float vy) {
		vx -= x;
		vy -= y;
		return (vx * vx + vy * vy);
	}

	public float distanceSq(Vector2D v) {
		return distanceSq(v.getX(), v.getY());
	}

	public Vector2D normalize() {
		float magnitude = magnitude();
		if (magnitude != 0) {
			x /= magnitude;
			y /= magnitude;
		}
		return this;
	}

	public Vector2D limit(float max) {
		if (magnitude() > max) {
			setMagnitude(max);
		}
		return this;
	}

	public Vector2D limit(float min, float max) {
		float magnitude = magnitude();

		if (magnitude < min) {
			setMagnitude(min);
		}

		if (magnitude > max) {
			setMagnitude(max);
		}
		return this;
	}

	public Vector2D abs() {
		x = Math.abs(x);
		y = Math.abs(y);
		return this;
	}

	public Vector2D clone() {
		return new Vector2D(x, y);
	}

	public Vector2f toVector2f() {
		return new Vector2f(x, y);
	}

	// Static

	static Vector2D add(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());

	}

	static Vector2D sub(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.getX() - v2.getX(), v1.getY() - v2.getY());
	}

	static Vector2D mul(Vector2D v1, float f) {
		return new Vector2D(v1.getX() * f, v1.getY() * f);
	}

	static Vector2D dev(Vector2D v1, float f) {
		return new Vector2D(v1.getX() / f, v1.getY() / f);
	}

	// Getter Setter

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2D vector2d) {
		this.x = vector2d.getX();
		this.y = vector2d.getY();
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

	@Override
	public String toString() {
		return "Vector2D [x=" + x + ", y=" + y + "]";
	}

}
