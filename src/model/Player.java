package model;

import java.util.ArrayList;

import interfaces.Attackable;
import interfaces.Attacker;
import interfaces.Deflectable;
import interfaces.Dieable;
import interfaces.Printable;
import interfaces.Stronger;

public class Player implements Attackable, Dieable, Printable{
	private float health;
	private int money;
	private float mana;
	private float baseDamage;
	private int x;
	private int y;
	private String password;
	private String email;
	private ArrayList<Item> Items;
	public Player(float health, int money, float mana, float baseDamage, int x, int y, String password, String email) {
		super();
		this.health = health;
		this.money = money;
		this.mana = mana;
		this.baseDamage = baseDamage;
		this.x = x;
		this.y = y;
		this.password = password;
		this.email = email;
		Items = new ArrayList<>();
	}
	
	public float attackWithItem(Hero enemy, Item item) {
		System.out.println("Attacking " + enemy.getName() + " with " + item.getName());
		float totalDamage = baseDamage;
		if(item instanceof OffensiveItem) {
			float bonusDamage = ((OffensiveItem)item).extraDamage();
			System.out.println();
			totalDamage +=  bonusDamage;
			if(((OffensiveItem)item).getUseLeft() <= 0) {
				Items.remove(item);
			}
			
		}
		if(item instanceof SpellItem) {
			if(mana - ((SpellItem)item).getManaUsed() <= 0) {
				System.out.println("You don't have enough mana");
			} else {
				
				System.out.printf("Used %d of mana", ((SpellItem)item).getManaUsed());
				mana -= ((SpellItem)item).getManaUsed();
				System.out.println();
				float bonusDamage = ((SpellItem)item).extraDamage();
				totalDamage += bonusDamage;
			}
			
		}
		System.out.printf("%s attacked with %.2f amount of damage\n", enemy.getName(), totalDamage);

		return totalDamage;

	}
	
	public float pureAttack(Hero enemy) {
		System.out.println("Attacking " + enemy.getName());
		System.out.printf("%s got damage %.2f\n", enemy.getName(), baseDamage);

		return baseDamage;

	}
	
	public void storeMana() {
		System.out.println("Storing mana......");
		System.out.println("Added 10.00 mana");
		System.out.println();
		mana += 10;
	}
	
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public float getMana() {
		return mana;
	}
	public void setMana(float mana) {
		this.mana = mana;
	}
	public float getBaseDamage() {
		return baseDamage;
	}
	public void setBaseDamage(float baseDamage) {
		this.baseDamage = baseDamage;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Item> getItems() {
		return Items;
	}
	
	@Override
	public void printDetail() {
		System.out.println("Player information " + email);
		System.out.println("Health: " + health);
		System.out.println("Money: " + money);
		System.out.println("Mana: " + mana);
		System.out.println("Base Damage: " + baseDamage);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		System.out.println("DIED");
		System.out.println("You have been killed");
		
		System.out.println("Resetting your player information");
		health = 300;
		Items.clear();
		mana = 30;
		money = 100;
	}

	@Override
	public void gotDamaged(float damage) {
		// TODO Auto-generated method stub
		System.out.printf("Receive Damage %.2f\n", damage);
		health -= damage;
		
	}
	
	public void foundCoin() {
		money += 50;
	}
	
	public void buyItem(Item item) {
		Items.add(item);
		money -= item.getPrice();
		
	}
	
	
	
	
	
	
	
	
}
