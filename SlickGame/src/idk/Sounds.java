package idk;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	
	public static Sound backgroundMusick;
	
	public static Sound schrotShoot;
	public static Sound schrotReload;
	
	public static void startUp() throws SlickException {
		schrotShoot = new Sound("res/Shotgun_shoot.wav");
		schrotReload = new Sound("res/Shotgun_reload.wav");
//		backgroundMusick = new Sound("res/soviet-anthem.mp3");
//		
//		backgroundMusick.loop();
	}
}
