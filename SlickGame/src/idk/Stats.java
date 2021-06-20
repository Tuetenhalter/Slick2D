package idk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.SlickException;
 
public class Stats {
	
	public static int coins = 0;
	public static int level = 0;
	public static int xp = 0;
	
	public static void startUp() throws SlickException {
	
		load();
	}

	public static void save() {
		try {
			File file = new File("game.txt");

			file.delete();
			FileWriter writer = new FileWriter("game.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(coins + "," + level + "," + xp);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load() {
		try {
			FileReader reader = new FileReader("game.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			
			String[] list = line.split(",");
			coins = Integer.parseInt(list[0]);
			level = Integer.parseInt(list[1]);
			xp = Integer.parseInt(list[2]);
			
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			coins = 0;
			level = 0;
			xp = 0;
			save();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
