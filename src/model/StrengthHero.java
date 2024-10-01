package model;

import interfaces.Deflectable;

public class StrengthHero extends Hero implements Deflectable{

	private float armor;


	public StrengthHero(String type, String name) {
		super(type, name);
		// TODO Auto-generated constructor stub
	}



	@Override
	public float attack() {
		// TODO Auto-generated method stub
		return super.attack();
	}

	public float getArmor() {
		return armor;
	}

	public void setArmor(float armor) {
		this.armor = armor;
	}


	@Override
	public void gotDamaged(float damage) {
		// TODO Auto-generated method stub
		
		float totalDamage = damage - deflect();
		
		this.setHealth(this.getHealth()-totalDamage);
		
	}



	@Override
	public float deflect() {
		// TODO Auto-generated method stub
		return armor;
	}



	@Override
	public void printDetail() {
		// TODO Auto-generated method stub
		super.printDetail();
	}
	
	
	
}
