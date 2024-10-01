package util;

import java.util.Random;

public class Util {
	public static Random rand = new Random();
	
	public static void printGuide() {
		System.out.println("Hello this is the case maker writing, this game is inspired by my favorite game of all time that is DOTA the game is really simple where you move inside the");
		System.out.println("game using general controls, In the game you can collect coins as you move. You can also meet enemies while going through the grass in the game. If you");
		System.out.println("have killed an enemy you will be rewarded money that you can use again to buy the item");
		System.out.printf("\nCharacter Informations\n");
		System.out.printf("\tCoin : O\n");
		System.out.printf("\tGrass : v | V\n");
		System.out.printf("\tCharacter : X\n");
		System.out.printf("\tWall : #\n");
		System.out.println();
		System.out.println("Keybind Information");
		System.out.println("\tw          : Move character up");
		System.out.println("\ta          : Move character left");
		System.out.println("\ts          : Move character down");
		System.out.println("\td          : Move character right");
		System.out.println("\ti          : Show all character's item");
		System.out.println("\tz          : Shop Menu");
		System.out.println("\te          : Exit game and save your information");
	}
	
}
