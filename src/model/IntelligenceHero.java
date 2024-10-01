package model;

import java.util.Random;

import interfaces.Stronger;
import util.Util;

public class IntelligenceHero extends Hero implements Stronger{
	


	public IntelligenceHero(String type, String name) {
		super(type, name);
		// TODO Auto-generated constructor stub
	}
	


	public float attack() {
		// TODO Auto-generated method stub
		super.attack();
		float totalDamage = extraDamage();
		return totalDamage;
	}

	@Override
	public float extraDamage() {
		float bonusDamage = Util.rand.nextInt(20) + 30;
		float totalDamage = this.getDamage() + bonusDamage;
		System.out.printf("%s is an Intelligence hero, using skill gave bonus damage %.2f\n", 
				this.getName(), bonusDamage);
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
	}

	
	
	
	
	
}
