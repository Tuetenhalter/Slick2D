package idk;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	
	public static Sound shoot;
	
	public static Sound backgroundMusick;
	
	public static Sound schrotShoot;
	public static Sound schrotReload;
	
	public static void startUp() throws SlickException {
		shoot = new Sound("res/Shoot.wav");
		
		schrotShoot = new Sound("res/Shotgun_shoot.wav");
		schrotReload = new Sound("res/Shotgun_reload.wav");
		backgroundMusick = new Sound("res/soviet-anthem.wav");
		
		backgroundMusick.loop(1f, Options.volume);
	}
}
