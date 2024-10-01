package model;

import interfaces.Attackable;
import interfaces.Attacker;
import interfaces.Dieable;
import interfaces.Printable;

public abstract class Hero implements Attackable, Printable, Dieable{
	private String name;
	private float damage;
	private float health;
	private String type;
	public Hero(String type, String name) {
		this.type = type;
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDamage() {
		return damage;
	}
	public void setDamage(float damage) {
		this.damage = damage;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	
	@Override
	public abstract void gotDamaged(float damage); 

	
	public float attack() {
		// TODO Auto-generated method stub
			System.out.printf("%s is attacking with a base damage of %.2f\n", name, damage);
		return damage;
	}

	@Override
	public void printDetail() {
		System.out.println("Monster Information");
		System.out.println("Name : " + name);
		System.out.println("Health : " + health);
		System.out.println("Damage : " + damage);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		System.out.println("MONSTER DIED");
		System.out.println(name + " has been killed");
	}
	
	

	
	
	
	
}
