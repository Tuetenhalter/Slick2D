package idk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.Input;

public class Options {
	
	public static HashMap<String, String> lang;
	
	public static int forward = Input.KEY_W;
	public static int left = Input.KEY_A;
	public static int right = Input.KEY_D;
	public static int back = Input.KEY_S;
	
	public static int shoot = Input.MOUSE_LEFT_BUTTON;
	public static int reload = Input.KEY_R;
	
	
	
	static public void init() {
		try {
			FileReader reader = new FileReader("option.txt");

			BufferedReader bufferedReader = new BufferedReader(reader);

			String line;

			while ((line = bufferedReader.readLine()) != null) {

			}
			
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			createFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	static public void createFile() {
		try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.newLine();
            bufferedWriter.write("See You Again!");
 
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
