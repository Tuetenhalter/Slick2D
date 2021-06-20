package idk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.MyBasicGameState;

public class Camara {

	static final float SMOOTH = 5f;
	static final float SMOOTHZOOM = 5f;
	static final float PLAYER_VEL = .25f;

	private Vector2D pos;
	private float zoom = 1;
	private float targedZoom = 1;
	private float rangex;
	private float rangey;
	private float rangex2;
	private float rangey2;

	public Camara(Vector2D pos) {
		super();
		this.pos = pos;
	}

	public Camara(float x, float y) {
		this(new Vector2D(x, y));
	}

	public Camara(Vector2D pos, float rangex, float rangey, float rangex2, float rangey2) {
		super();
		this.pos = pos;
		this.rangex = rangex;
		this.rangey = rangey;
		this.rangex2 = rangex2;
		this.rangey2 = rangey2;
	}

	public Camara(float x, float y, float rangex, float rangey, float rangex2, float rangey2) {
		super();
		this.pos = new Vector2D(x, y);
		this.rangex = rangex;
		this.rangey = rangey;
		this.rangex2 = rangex2;
		this.rangey2 = rangey2;
	}

	public void translateCamara(GameContainer container, StateBasedGame game, Graphics g, MyBasicGameState mygame) {
		g.resetTransform();
		g.scale(zoom, zoom);
		g.translate(-getPos().getX() + (container.getWidth()  * (1 / zoom)  / 2),
				-getPos().getY() + (container.getHeight()  * (1 / zoom)  / 2));

	}

	public void camaraMove(MyBasicGameState mygame, GameContainer container, int delta) {
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_UP)) {
			targedZoom *= 1.1f;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			targedZoom *= .9f;
		}

		zoom += (targedZoom - zoom) * (SMOOTHZOOM * delta / 1000f);

//		System.out.println("targedZoom: " + targedZoom);
//		System.out.println("zoom: " + zoom);
//		System.out.println("(SMOOTHZOOM * delta / 1000f): " + (SMOOTHZOOM * delta / 1000f));

		Vector2D target = mygame.getPlayer().getPos().clone();
		target.add(mygame.getPlayer().getWidth() / 2, mygame.getPlayer().getHeight() / 2);

		 target.add(mygame.getPlayer().getVel().clone().mul(PLAYER_VEL));

//		if (target.getX() < rangex) {
//			target.setX(rangex);
//		}
//		if (target.getX() > rangex2) {
//			target.setX(rangex2);
//		}
//
//		if (target.getY() < rangey) {
//			target.setY(rangey);
//		}
//		if (target.getY() > rangey2) {
//			target.setY(rangey2);
//		}

		target.sub(pos);
		target.mul(SMOOTH * delta / 1000f);
		pos.add(target);

	}

	public Vector2D mousePos(GameContainer container) {
		Vector2D mouse = new Vector2D(container.getInput().getMouseX(), container.getInput().getMouseY());


		mouse.mul(1/zoom);
		mouse.sub(-getPos().getX() + (container.getWidth()  * (1 / zoom)  / 2),
				-getPos().getY() + (container.getHeight()  * (1 / zoom)  / 2));


		return mouse;
	}

	// Getter Setter

	public Vector2D getPos() {
		return pos;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	public void setPos(float x, float y) {
		pos.set(x, y);
	}

	public float getRangex() {
		return rangex;
	}

	public void setRangex(float rangex) {
		this.rangex = rangex;
	}

	public float getRangey() {
		return rangey;
	}

	public void setRangey(float rangey) {
		this.rangey = rangey;
	}

	public float getRangex2() {
		return rangex2;
	}

	public void setRangex2(float rangex2) {
		this.rangex2 = rangex2;
	}

	public float getRangey2() {
		return rangey2;
	}

	public void setRangey2(float rangey2) {
		this.rangey2 = rangey2;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public float getTargedZoom() {
		return targedZoom;
	}

	public void setTargedZoom(float targedZoom) {
		this.targedZoom = targedZoom;
	}

}
