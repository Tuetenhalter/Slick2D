package idk;

import java.util.HashMap;
import org.newdawn.slick.Input;

public class Options
{

	public static HashMap<String, String> lang;

	public static int forward = Input.KEY_W;
	public static int left = Input.KEY_A;
	public static int right = Input.KEY_D;
	public static int back = Input.KEY_S;

	public static int shoot = Input.MOUSE_LEFT_BUTTON;
	public static int reload = Input.KEY_R;

	public static float volume = .01f;

}
