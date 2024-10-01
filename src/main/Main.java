package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.AgilityHero;
import model.DefensiveItem;
import model.Hero;
import model.IntelligenceHero;
import model.Item;
import model.OffensiveItem;
import model.Player;
import model.SpellItem;
import model.StrengthHero;
import util.Util;

public class Main {


	
	int width = 300;
    int height = 300;
	char[][] map = new char[height][width];
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	
	public void printMap(int x, int y) {
		for (int i = y; i < y+15; i++) {
			for (int j = x; j < x+35; j++) {
				if(i == y+7 && j == x+17) { 
					System.out.print("X"); 
				} else {
					
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}

		currentPlayer.printDetail(); 
	}
	
	public void generateCoin() {
		int x, y, count = 0;
		while(count < 200) {
			x = rand.nextInt(300);
			y = rand.nextInt(300);
			if(map[y][x] == '.') {				
				map[y][x] = 'O';
			}
			count++;
		}
	}
	
	public void generateMap() {
		int currX = 0, currY = 0, randomNum = 0;
	    char[] tileTypes = {'.', '#', 'v'};
	    do {
	    	if(currY - 1 >= 0 && currX - 1 < 0) { //most leftside
	    		
	    		if(map[currY - 1][currX] == 'v' || map[currY - 1][currX] == '#') {
	    			randomNum = 0;
	    		}
	    		else {
		    		randomNum = rand.nextInt(tileTypes.length);
		    	}
	    	}
	    	else if(currY - 1 < 0 && currX - 1 >= 0) { //most top
	    		
	    		if(map[currY][currX-1] == 'v' ||  map[currY][currX-1] == '#') {
	    			randomNum = 0;
	    		}
	    		else {
		    		randomNum = rand.nextInt(tileTypes.length);
		    	}
	    	}
	    	
	    	else if(currY - 1 >= 0 && currX - 1 >= 0) { //not edge
	    		
	    		if(currX+3 >= width) {
	    			if(map[currY - 1][currX] == 'v' || map[currY][currX-1] == 'v' ||
		    				map[currY - 1][currX] == '#' || map[currY][currX-1] == '#' ||
		    				map[currY - 1][currX-1] == '#' || map[currY - 1][currX-1] == 'v') { 
		    			randomNum = 0;
		    		}
		    		else {
			    		randomNum = rand.nextInt(tileTypes.length);
			    	}
	    		} else {
	    			if(map[currY - 1][currX] == 'v' || map[currY][currX-1] == 'v' ||
	    					map[currY - 1][currX] == '#' || map[currY][currX-1] == '#' ||
	    					map[currY - 1][currX-1] == '#' || map[currY - 1][currX-1] == 'v'
	    					|| map[currY - 1][currX+3] == '#' || map[currY - 1][currX+3] == 'v') { 
	    				randomNum = 0;
	    			}
	    			else {
	    				randomNum = rand.nextInt(tileTypes.length);
	    			}
	    		}
	    		
	    	}
	    	else {
	    		randomNum = rand.nextInt(tileTypes.length);
	    	}

	    		
    		for (int i = 0; i < 3; i++) {
    			for (int j = 0; j < 3; j++) {
    				
					map[currY + i][currX + j] = tileTypes[randomNum];
						
    			}
    		}
	    	

	        currX += 3;
	        if (currX >= width) { 
	            currX = 0;
	            currY += 3;
	        }
	    } while (currY < height);
		
	}

	
	ArrayList<Item> itemList = new ArrayList<>();
	ArrayList<Hero> heroList = new ArrayList<>();
	public void readItemFile() {
		File file = new File("item.txt");
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(fileReader.hasNextLine()) {
			String terbaca = fileReader.nextLine();
			String[] data = terbaca.split("#", 6);
			if(data[2].equals("spell")) {
				SpellItem item = new SpellItem(data[0], data[1], data[2], Integer.parseInt(data[3]), 
						Integer.parseInt(data[4]), Integer.parseInt(data[5]));
				itemList.add(item);
			}
			else if(data[2].equals("offensive")) {
				OffensiveItem item = new OffensiveItem(data[0], data[1], data[2], Integer.parseInt(data[3]), 
						Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[5]));
				itemList.add(item);
			}
			else if(data[2].equals("defensive")) {
				DefensiveItem item = new DefensiveItem(data[0], data[1], data[2], Integer.parseInt(data[3]), 
						Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[5]));
				itemList.add(item);
			}
		}
	}
	
	
	public void movePlayer() {
		char input;
		do {
			if(map[currentPlayer.getY()][currentPlayer.getX()] == 'v') {
				randomEncounter();
			}
			if(map[currentPlayer.getY()][currentPlayer.getX()] == 'O') {
				currentPlayer.foundCoin();
				map[currentPlayer.getY()][currentPlayer.getX()] = '.';
			}
			
			printMap(currentPlayer.getX()-17, currentPlayer.getY()-7);
			System.out.print(">> ");
			input = scan.nextLine().charAt(0);
			
			switch (input) {
		        case 'w':
		            if (currentPlayer.getY() > 0 && map[currentPlayer.getY()-1][currentPlayer.getX()] != '#') {
		                currentPlayer.setY(currentPlayer.getY()-1);
		            }
		            break;
		        case 'a':
		            if (currentPlayer.getX() > 0 && map[currentPlayer.getY()][currentPlayer.getX()-1] != '#') {
		                currentPlayer.setX(currentPlayer.getX()-1);
		            }
		            break;
		        case 's':
		            if (currentPlayer.getY() < map.length - 1 && map[currentPlayer.getY()+1][currentPlayer.getX()] != '#') {
		                currentPlayer.setY(currentPlayer.getY()+1);
		            }
		            break;
		        case 'd':
		            if (currentPlayer.getX() < map[0].length - 1 && map[currentPlayer.getY()][currentPlayer.getX()+1] != '#') {
		                currentPlayer.setX(currentPlayer.getX()+1);
		            }
		            break;
		        case 'z':
		        	shopMenuController();
		        	break;
		        case 'i':
		        	printPlayerItems();
		        	pause();
		        	break;
		    }
		}while(input != 'e'); 
	}

	
	
	
	public void viewItemShop(String itemType) {
		String d = null;
		String m = null;
		
		System.out.println("Your money is " + currentPlayer.getMoney()); 
		if(itemType.equals("offensive")) {
			d = "Damage";
			m = "Max Use";
		}
		else if(itemType.equals("defensive")) {
			d = "Deflect";
			m = "Max Use";
		}
		else if(itemType.equals("spell")) {
			d = "Damage";
			m = "Mana";
		}
		System.out.println("============================================================================================");
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-10s|\n", "ID", "Name", "Type", "Price",
				d, m);
		System.out.println("============================================================================================");
		for (Item item : itemList) {
			if(item.getType().equals(itemType)) {	
				item.printTable();
			}
		}
		System.out.println("============================================================================================");
	}
	
	public void printShopMenu() {
		System.out.println("Shop Menu");
		System.out.println("1. Buy offensive item");
		System.out.println("2. Buy defensive item");
		System.out.println("3. Buy spells item");
		System.out.println("4. Exit");
	}
	
	public Item searchItem(String id) {
		for (Item item : itemList) {
			if(item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	
	public void printPlayerItems() {
		
		if(currentPlayer.getItems().isEmpty()) {
			System.out.println("You have no items");
			
			return;
		}
		
		System.out.println("=========================================================================================================");
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-12s|%-10s|\n", "ID", "Name", "Type", "Price",
				"Damage", "Max Use/Mana", "Use Left");
		System.out.println("=========================================================================================================");
		for (Item item : currentPlayer.getItems()) {			
			item.printPlayerTable();
		}
		System.out.println("=========================================================================================================");
		
	}
	
	
	public void printPlayerOffItems() {
		System.out.println("=========================================================================================================");
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-12s|%-10s|\n", "ID", "Name", "Type", "Price",
				"Damage", "Max Use/Mana", "Use Left");
		System.out.println("=========================================================================================================");
		for (Item item : currentPlayer.getItems()) {			
			if(!(item instanceof DefensiveItem)) {
				
				item.printPlayerTable();
			}
		}
		System.out.println("=========================================================================================================");
		
	}
	
	
	public void printPlayerDefItem() {
		System.out.println("=========================================================================================================");
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-12s|%-10s|\n", "ID", "Name", "Type", "Price",
				"Damage", "Max Use", "Use Left");
		System.out.println("=========================================================================================================");
		for (Item item : currentPlayer.getItems()) {			
			
			if(item instanceof DefensiveItem) {
				
				item.printPlayerTable();
			}
			
		}
		System.out.println("=========================================================================================================");
		
	}
	

	public void shopMenuController() {
		int input = -1;
		String itemID;
		Item temp;
		do {
			printShopMenu();
			System.out.print(">> ");
			input = scan.nextInt(); scan.nextLine();
			switch (input) {
			case 1:
				
				viewItemShop("offensive");
				System.out.print("Input Item's ID ['Exit' to cancel] : ");
				itemID = scan.nextLine();
				if(itemID.equalsIgnoreCase("exit")) {
					break;
				}
				temp = searchItem(itemID);
				if(temp == null) {
					System.out.println("Item not found");
					pause();
				} else {
					
					if(currentPlayer.getMoney() < temp.getPrice()) {
						System.out.println("You don't have enough money");
						pause();
					} else {
						currentPlayer.buyItem(temp);
						
						printPlayerItems();
						System.out.println("Bought the item");
						pause();
					}
				}
				break;
			case 2:
				viewItemShop("defensive");
				System.out.print("Input Item's ID ['Exit' to cancel] : ");
				itemID = scan.nextLine();
				if(itemID.equalsIgnoreCase("exit")) {
					break;
				}
				temp = searchItem(itemID);
				if(temp == null) {
					System.out.println("Item not found");
					pause();
				} else {
					if(currentPlayer.getMoney() < temp.getPrice()) {
						System.out.println("You don't have enough money");
						pause();
					} else {
						currentPlayer.buyItem(temp);
						
						printPlayerItems();
						System.out.println("Bought the item");
						pause();
					}
				}
				break;
			case 3:
				viewItemShop("spell");
				System.out.print("Input Item's ID ['Exit' to cancel] : ");
				itemID = scan.nextLine();
				if(itemID.equalsIgnoreCase("exit")) {
					break;
				}
				temp = searchItem(itemID);
				if(temp == null) {
					System.out.println("Item not found");
					pause();
				} else {
					if(currentPlayer.getMoney() < temp.getPrice()) {
						System.out.println("You don't have enough money");
						pause();
					} else {
						currentPlayer.buyItem(temp);
						
						printPlayerItems();
						System.out.println("Bought the item");
						pause();
					}
				}
				break;
			default:
				break;
			}
		}while(input != 4);
	}
	
	public void readHeroFile() {
		File file = new File("Hero.txt");
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String terbaca = fileReader.nextLine();
		String[] data = terbaca.split("#", 10);
		
		for (String string : data) {
			StrengthHero strHero = new StrengthHero("Strength", string);
			heroList.add(strHero);
		}
		
		terbaca = fileReader.nextLine();
		data = terbaca.split("#", 10);
		
		for (String string : data) {
			IntelligenceHero intHero = new IntelligenceHero("Intelligence", string);
			heroList.add(intHero);
		}
		
		terbaca = fileReader.nextLine();
		data = terbaca.split("#", 10);
		
		for (String string : data) {
			AgilityHero agiHero = new AgilityHero("Agility", string);
			heroList.add(agiHero);
		}
		
		
	}
	
	
	Hero currentEnemy;
	public void randomEncounter() {
		int random; 
		float randomAttribute;
		random = rand.nextInt(3);
		if(random == 1) { //encounter
			random = rand.nextInt(heroList.size());
			currentEnemy = heroList.get(random);

			//can random same hero after beating it
			if(heroList.get(random) instanceof StrengthHero) {
				randomAttribute = rand.nextInt(11) + 20;
				currentEnemy.setDamage(randomAttribute);
				randomAttribute = rand.nextInt(11) + 200 - currentEnemy.getDamage();
				currentEnemy.setHealth(randomAttribute);
				randomAttribute = rand.nextInt(21) + 20;
				((StrengthHero) currentEnemy).setArmor(randomAttribute); 
				//StrengthHero strengthHero = (StrengthHero) randomHero; 
			}
			else if(heroList.get(random) instanceof AgilityHero) {
				
				randomAttribute = rand.nextInt(11) + 40;
				currentEnemy.setDamage(randomAttribute);
				randomAttribute = rand.nextInt(11) + 100 - currentEnemy.getDamage();
				currentEnemy.setHealth(randomAttribute);
				random = rand.nextInt(2) + 1;
				((AgilityHero) currentEnemy).setCritical(random);
			}
			else if(heroList.get(random) instanceof IntelligenceHero) {
				
				randomAttribute = rand.nextInt(11) + 10;
				currentEnemy.setDamage(randomAttribute);
				randomAttribute = rand.nextInt(11) + 100;
				currentEnemy.setHealth(randomAttribute);
			}
			fightScene();
		}
	}
	
	Player currentPlayer;
	
	
	public void loginMenu() {
		int input = -1;
		do {
			System.out.println("Welcome to DOTW");
			System.out.println("Login Menu");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.print(">> ");
			try {
				
				input = scan.nextInt(); 
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Input a number");
			}
			scan.nextLine();
			switch (input) {
			case 1:
				generateMap();
				generateCoin();
				login();
				break;
			case 2:
				register();
				break;
			
			default:
				break;
			}
		}while(input != 3);
	}
	
	public void login() {
		String email, password;
		System.out.println("Input email : ");
		email = scan.nextLine();
		System.out.println("Input password : ");
		password = scan.nextLine();

		String[] data = checkCredentials(email, password);
		if(data == null) {
			System.out.println("Incorrect credentials");
			return;
		}
		int x = 100, y = 100;
		while(map[y][x] != '.') {
			x += 3;
		}
		if(data.length < 3) {
			currentPlayer = new Player(300, 100, 30, 30, x, y, password, email);
		} else {
			
			currentPlayer = new Player(Float.parseFloat(data[3]), Integer.parseInt(data[2]), 
					Float.parseFloat(data[4]), 30, x, y, data[1], data[0]);
			
			String[] items = null;
			if(data.length == 6) {
				items = data[5].split("-");
			}
			for (int i = 0; i < items.length; i++) {
				String[] details = items[i].split("@");
				Item temp = searchItem(details[0]);
			
				currentPlayer.getItems().add(temp);
				if(temp instanceof OffensiveItem) {
					((OffensiveItem)temp).setUseLeft(
							Integer.parseInt(details[1]));
					
				}
				else if(temp instanceof DefensiveItem) {
					
					((DefensiveItem)temp).setUseLeft(
							Integer.parseInt(details[1]));
				}

			}
			
		}
		gameMenuController();
	}
	
	public void gameMenuController() {
		int input = -1;
		do {
			gameMenu();
			input = scan.nextInt(); scan.nextLine();
			switch (input) {
			case 1:
				movePlayer();
				break;
			case 2:
				Util.printGuide();
				pause();
				break;
			case 3:
				saveToFile();
			default:
				break;
			}
		}while(input != 3);
	}
	
	public void gameMenu() {
		System.out.println("Welcome to the game");
		System.out.println("1. Start game");
		System.out.println("2. Game guide");
		System.out.println("3. Exit game");
		System.out.print(">> ");
	}
	
	public void register() {
		System.out.println("Register menu");
		String email, password;
		do {
			System.out.println("Input email : ");
			email = scan.nextLine();
			System.out.println("Input password : ");
			password = scan.nextLine();
			if(!validateEmail(email)) {
				System.out.println("There must be a '@' in the Email");
				pause();
			}
			else if(!validatePassword(password)) {
				System.out.println("Password Length must be between 8 and 30");
				pause();
			}
		}while(!validateEmail(email) || !validatePassword(password));
		
		registerToFile(email, password);
		System.out.println("Registration success");
		pause();
	}
	
	public void registerToFile(String email, String password) {
		try {
			FileWriter fw = new FileWriter("credential.txt", true);
			fw.write(String.format("%s#%s\n", email, password));
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean validatePassword(String password) {
		if(password.length() < 8 || password.length() > 30) {
			return false;
		}
		return true;
	}
	
	public boolean validateEmail(String email) {
		if(!email.contains("@") || !email.contains(".")) {
			return false;
		}
		
		if(email.charAt(0) == '@' || email.charAt(email.length()-1) == '@') {
			return false;
		}
		
		if(email.indexOf("@") < email.lastIndexOf(".")) {
			return true;
		}
		
		return false;
		
	}
	
	public String[] checkCredentials(String username, String password) {
		File file = new File("credential.txt");
		try {
			Scanner fileReader = new Scanner(file);
			while(fileReader.hasNextLine()) {
				String terbaca = fileReader.nextLine();
				String[] data = terbaca.split("#");
				if(data[0].equals(username) && data[1].equals(password)) {
					
					fileReader.close();
					return data;
				}
			}
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public void cls() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	public void pause() {
		System.out.println("Enter to continue...");
		scan.nextLine();
	}
	
	public void monsterInfo() {
		currentEnemy.printDetail();
	}
	
	public void currentInfo() {
		monsterInfo();
		System.out.println();
		currentPlayer.printDetail();
		
		System.out.println();
		if(!currentPlayer.getItems().isEmpty()) {			
			System.out.println("Item Information");
			printPlayerItems();
			System.out.println();
		}
	}
	
	public Item searchPlayerItem(String id) {
		for (Item item : currentPlayer.getItems()) {
			if(item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	
	public void attackWithItemController() {
		String itemID;
		Item temp;
		
		if(currentPlayer.getItems().isEmpty()) {
			System.out.println("You have no items.");
			pause();
			return;
		}
		
		do {
			
			printPlayerOffItems();
			System.out.print("Input Item's ID ['Exit' to cancel] : ");
			itemID = scan.nextLine();
			temp = searchPlayerItem(itemID);
			if(itemID.equalsIgnoreCase("exit")) {
				return;
				
			}
		}while(temp == null);
		
		float damage = currentPlayer.attackWithItem(currentEnemy, temp);
		currentEnemy.gotDamaged(damage);
		System.out.println("Updated Monster Information");
		currentEnemy.printDetail(); 
		pause();
			
	}
	
	
	public float useDefensiveItem() {
		String itemID;
		Item temp;
		printPlayerDefItem();
		System.out.print("Input Item's ID ['Exit' to cancel] : ");
		itemID = scan.nextLine();
		temp = searchPlayerItem(itemID);
		
		if(temp != null) {
			if(((DefensiveItem)temp).getUseLeft() <= 0) {
				System.out.println("No use left");
				currentPlayer.getItems().remove(temp);
			}
			float deflect = ((DefensiveItem)temp).deflect();
			System.out.println();
			return deflect;
			
		}
		return 0;	
	}
	
	public boolean haveDefensiveItem() {
		for (Item item : currentPlayer.getItems()) {
			if(item.getType().equals("defensive")) {
				return true;
			}
		}
		return false;
	}
	
	public void fightScene() {
		cls();
		int turn = rand.nextInt(2);
		int input;
		do {
			System.out.println("Information");
			currentInfo();
			if(turn == 1) {
				System.out.println();
				System.out.println("PLAYER TURN");
				System.out.println();
				currentInfo();
				System.out.println("Your choice");
				System.out.println("1. Pure Attack");
				System.out.println("2. Attack with item");
				System.out.println("3. Store mana");
				do {
					
					System.out.print(">> ");
					input = scan.nextInt(); scan.nextLine();
				}while(input < 1 || input > 3);
				switch (input) {
				case 1:
					float damage = currentPlayer.pureAttack(currentEnemy);
					currentEnemy.gotDamaged(damage);
					pause();
					break;

				case 2:
					attackWithItemController();
					break;
				case 3:
					currentPlayer.storeMana();
					pause();
					break;
				}
				
				
			}
			else if(turn == 0) {
				System.out.println();
				System.out.println("MONSTER TURN");
				System.out.println();
				System.out.println("Monster is going to attack");
				String yesOrNo;
				
				float totalDamage = currentEnemy.attack();
				if(haveDefensiveItem()) {					
					System.out.println("Do you want to use your defensive item?");
					System.out.print("Yes | No [Case sensitive] : ");
					yesOrNo = scan.nextLine();
					if(yesOrNo.equals("Yes")) {
						totalDamage -= useDefensiveItem();
					}
				}
				currentPlayer.gotDamaged(totalDamage);
				
				pause();
			}
			turn = (turn+1)%2;
		}while(currentEnemy.getHealth() >0 && currentPlayer.getHealth() > 0);
		
		if(currentEnemy.getHealth() <= 0) {
			currentEnemy.die();
			pause();
		}
		
		else if(currentPlayer.getHealth() <= 0) {
			currentPlayer.die();
			
			pause();
		}
	}
	
	public ArrayList<String> readFile() {
		File file = new File("credential.txt");
		ArrayList<String> fileData = new ArrayList<>();
		try {
			Scanner fileReader = new Scanner(file);
			while(fileReader.hasNextLine()) {
				String terbaca = fileReader.nextLine();
				fileData.add(terbaca);
			}
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileData;
	}
	
	public void updateSave(ArrayList<String> fileData, String username) {
		String toDelete = null;
		for (String string : fileData) {
			String[] data = string.split("#");
			if(data[0].equals(username)) {
				toDelete = string;
				break;
			}
		}
		fileData.remove(toDelete);
	}
	
	public void rewriteFile() {
		ArrayList<String> fileData = readFile();
		updateSave(fileData, currentPlayer.getEmail());
		try {
			FileWriter fw = new FileWriter("credential.txt");
			for (String string : fileData) {
				fw.write(String.format("%s\n", string));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void saveToFile() {
		FileWriter fw;
		rewriteFile();
		try {

			fw = new FileWriter("credential.txt", true);
			fw.write(String.format("%s#%s#%d#%.2f#%.2f#", currentPlayer.getEmail(), currentPlayer.getPassword(),
					currentPlayer.getMoney(), currentPlayer.getHealth(), currentPlayer.getMana()));
			int count = 0;
			
			for (Item item : currentPlayer.getItems()) {
				if(count == 0) {

					if(item instanceof OffensiveItem) {
						
						fw.write(String.format("%s@%d", item.getId(), ((OffensiveItem)item).getUseLeft()));
					}
					else if(item instanceof DefensiveItem) {
						
						fw.write(String.format("%s@%d", item.getId(), ((DefensiveItem)item).getUseLeft()));
					}
					else if(item instanceof SpellItem) {
						
						fw.write(String.format("%s@%d", item.getId(), 0));
					}
					
				} else {
					if(item instanceof OffensiveItem) {
						
						fw.write(String.format("-%s@%d", item.getId(), ((OffensiveItem)item).getUseLeft()));
					}
					else if(item instanceof DefensiveItem) {
						
						fw.write(String.format("-%s@%d", item.getId(), ((DefensiveItem)item).getUseLeft()));
					}
					else if(item instanceof SpellItem) {
						
						fw.write(String.format("-%s@%d", item.getId(), 0));
					}
					
				}
				
				count++;
			}
			fw.write(String.format("\n"));
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Main() {
		// TODO Auto-generated constructor stub	
		readItemFile();
		readHeroFile();
		loginMenu();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
