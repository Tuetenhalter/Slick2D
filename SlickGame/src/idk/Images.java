package idk;

import java.lang.reflect.Field;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {

	public static Image spawn;
	public static Image wall;
	public static Image player;
	public static Image pistol;
	public static Image kalashnikov;
	public static Image schrot;
	public static Image sniper;
	

	public static void startUp() throws SlickException {
		Field[] list = Images.class.getFields();
		
		for (int i = 0; i < list.length; i++) {
			Field field = list[i];
			Image image = new Image("res/" + field.getName() + ".png");
			image.setFilter(Image.FILTER_NEAREST);
			
			try {
				field.set(null, image);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		pistol = pistol.getFlippedCopy(true, false);
		schrot = schrot.getScaledCopy(.5f);
		sniper = sniper.getScaledCopy(.35f);
	}

}
