package model;

import interfaces.Stronger;

public class AgilityHero extends Hero implements Stronger{
	private int critical;


	public AgilityHero(String type, String name) {
		super(type, name);
		// TODO Auto-generated constructor stub
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	@Override
	public float attack() {
		// TODO Auto-generated method stub
		float totalDamage = extraDamage();
		System.out.printf("%s is attacking with a critical damage of %.2f\n", 
				this.getName(), totalDamage);
		return totalDamage;
	}

	@Override
	public float extraDamage() {
		float totalDamage = this.getDamage()*critical;
		return totalDamage;
	}
	
	
	@Override
	public void gotDamaged(float damage) {
		// TODO Auto-generated method stub
		
		this.setHealth(this.getHealth()-damage);
		
	}

	@Override
	public void printDetail() {
		// TODO Auto-generated method stub
		super.printDetail();
		System.out.println("Critical : " + critical);
	}
	
	


	

	

	
	
}
