package idk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.SlickException;

import Weapon.Pistol;
import Weapon.Sniper;
import Weapon.Weapon;

public class Stats {

	public static int potatos = 0;
	public static boolean schrot = false;
	public static boolean kalashnikov = false;
	public static boolean sniper = false;
	
	public static Weapon weapon;

	public static void startUp() throws SlickException {
		load();
		
		weapon = new Sniper();
		System.out.println(potatos);
		System.out.println(schrot);
		System.out.println(kalashnikov);
		System.out.println(sniper);
	}

	public static void load() {
		try {
			FileReader reader = new FileReader("game.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			
			String[] list = line.split(",");
			
			potatos = Integer.valueOf(list[0]);
			schrot = Boolean.valueOf(list[1]);
			kalashnikov = Boolean.valueOf(list[2]);
			sniper = Boolean.valueOf(list[3]);

			bufferedReader.close();
		} catch (FileNotFoundException e) {
			potatos = 0;
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			File file = new File("game.txt");

			file.delete();
			FileWriter writer = new FileWriter("game.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(potatos + "," + schrot + "," + kalashnikov + "," + sniper);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
