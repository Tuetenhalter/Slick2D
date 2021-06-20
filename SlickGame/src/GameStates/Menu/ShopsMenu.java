package GameStates.Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import GUI.Button;
import GameStates.States;
import Weapon.Kalashnikov;
import Weapon.Pistol;
import Weapon.Schrot;
import Weapon.Sniper;
import idk.Stats;

public class ShopsMenu extends Menu {

	private int lastState;
	private Button pistol, schrot, kalashnikov, sniper;
	private Button back;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		pistol = new Button("Pistol", 0.30f, 0.5f, 0.30f, 0.5f, container);
		schrot = new Button("Schrot", 0.5f, 0.30f, 0.30f, 0.5f, container);
		kalashnikov = new Button("Kalashnikov", 0.30f, 0.5f, 0.5f, 0.30f, container);
		sniper = new Button("Sniper", 0.5f, 0.30f, 0.5f, 0.30f, container);

		back = new Button("Back", .90f, .01f, .9f, .01f, container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		//Green or red if u bord it or not
		
		//Pistal 
		g.setColor(Color.green);
		g.fillRect(0, 0, container.getWidth()/2, container.getHeight()/2);
		
		//schrot
		if(Stats.schrot) {
			g.setColor(Color.green);
		}else {
			g.setColor(Color.red);
		}
		g.fillRect(container.getWidth()/2, 0, container.getWidth()/2, container.getHeight()/2);
		
		//kalashnikov
		if(Stats.kalashnikov) {
			g.setColor(Color.green);
		}else {
			g.setColor(Color.red);
		}
		g.fillRect(0, container.getHeight()/2, container.getWidth()/2, container.getHeight()/2);
		
		//sniper
		if(Stats.sniper) {
			g.setColor(Color.green);
		}else {
			g.setColor(Color.red);
		}
		g.fillRect(container.getWidth()/2, container.getHeight()/2, container.getWidth()/2, container.getHeight()/2);
		
		
		schrot.render(container, game, g);
		pistol.render(container, game, g);
		kalashnikov.render(container, game, g);
		sniper.render(container, game, g);
		back.render(container, game, g);

		g.drawString("Potatos: " + Stats.potatos, container.getWidth() / 16, container.getHeight() / 14);
		g.drawString("Weapon: " + Stats.weapon.getName(), container.getWidth() / 16, container.getHeight() / 14 + 15);
		g.setColor(Color.red);
		g.drawLine(0.2f, 0.2f, 0.3f, 0.5f);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		pistol.update(container, game, delta);
		schrot.update(container, game, delta);
		kalashnikov.update(container, game, delta);
		sniper.update(container, game, delta);
		back.update(container, game, delta);

		if (pistol.clicked()) {
			Stats.weapon = new Pistol();
		}

		if (schrot.clicked()) {
			if(Stats.schrot) {
				Stats.weapon = new Schrot();				
			} else {
				if(Stats.potatos >= 200) {
					Stats.potatos -= 200;
					Stats.schrot = true;
					Stats.weapon = new Schrot();
					Stats.save();
				}
			}
		}

		if (kalashnikov.clicked()) {
			if(Stats.kalashnikov) {
				Stats.weapon = new Kalashnikov();
			} else {
				if(Stats.potatos >= 1000) {
					Stats.potatos -= 1000;
					Stats.schrot = true;
					Stats.weapon = new Kalashnikov();
					Stats.save();
				}
			}
		}
		if (sniper.clicked()) {
			if(Stats.sniper) {
				Stats.weapon = new Sniper();
			} else {
				if(Stats.potatos >= 5000) {
					Stats.potatos -= 5000;
					Stats.sniper = true;
					Stats.weapon = new Sniper();
					Stats.save();
				}
			}
		}

		if (back.clicked()) {
			game.enterState(lastState);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.SHOP.getState();
	}

	public int getLastState() {
		return lastState;
	}

	public void setLastState(int lastState) {
		this.lastState = lastState;
	}
}
