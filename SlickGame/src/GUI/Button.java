package GUI;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Button {

	private boolean clicked = false;
	private boolean mouseOver = false;

	private String label;

	private float distanceright;
	private float distanceleft;
	private float distancetop;
	private float distancebotten;

	private Rectangle rect1;
	private Rectangle rect2;

	public Button(String label, float distanceright, float distanceleft, float distancetop, float distancebotten,
			GameContainer container) {
		super();
		this.label = label;
		this.distanceright = distanceright;
		this.distanceleft = distanceleft;
		this.distancetop = distancetop;
		this.distancebotten = distancebotten;

		float x = container.getWidth() * distanceleft;
		float y = container.getHeight() * distancetop;
		float width = container.getWidth() * (1-distancebotten) - x;
		float height = container.getHeight() * (1-distanceright) -y;

		rect1 = new Rectangle(x, y, width, height);

		height -= x * .02f;
		width -= x * .02f;
		x += x * .01f;
		y += x * .01f;

		rect2 = new Rectangle(x, y, width, height);

		System.out.println(this);

		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("height: " + height);
		System.out.println("width: " + width);

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.resetTransform();
		g.setColor(Color.gray);
		g.fill(rect1);
		
		if(!mouseOver) {
			g.setColor(Color.white);
			g.fill(rect2);			
		}

	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		System.out.println(container.getWidth());
		Input input = container.getInput();	
		if(rect1.contains(input.getMouseX(), input.getMouseY())) {	
			mouseOver = true;
			

			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				clicked =  true;
				System.out.println("moin");
			}
			
		} else {
			mouseOver = false;
		}

	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getDistancetop() {
		return distancetop;
	}

	public void setDistancetop(float distancetop) {
		this.distancetop = distancetop;
	}

	public float getDistancebotten() {
		return distancebotten;
	}

	public void setDistancebotten(float distancebotten) {
		this.distancebotten = distancebotten;
	}

	public float getDistanceright() {
		return distanceright;
	}

	public void setDistanceright(float distanceright) {
		this.distanceright = distanceright;
	}

	public float getDistanceleft() {
		return distanceleft;
	}

	public void setDistanceleft(float distanceleft) {
		this.distanceleft = distanceleft;
	}

	@Override
	public String toString() {
		return "Button [clicked=" + clicked + ", mouseOver=" + mouseOver + ", label=" + label + ", distanceright="
				+ distanceright + ", distanceleft=" + distanceleft + ", distancetop=" + distancetop
				+ ", distancebotten=" + distancebotten + ", rect1=" + rect1 + ", rect2=" + rect2 + "]";
	}

}
