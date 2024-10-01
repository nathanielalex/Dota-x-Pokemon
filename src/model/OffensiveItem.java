package model;

import interfaces.Stronger;

public class OffensiveItem extends Item implements Stronger{
	private int damage;
	private int maxUse;
	private int useLeft;
	public OffensiveItem(String id, String name, String type, int price, int damage, int maxUse, int useLeft) {
		super(id, name, type, price);
		this.useLeft = useLeft;
		this.damage = damage;
		this.maxUse = maxUse;
	}
	
	
	
	public int getUseLeft() {
		return useLeft;
	}



	public void setUseLeft(int useLeft) {
		this.useLeft = useLeft;
	}



	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getMaxUse() {
		return maxUse;
	}

	public void setMaxUse(int maxUse) {
		this.maxUse = maxUse;
	}



	@Override
	public void printTable() {
		// TODO Auto-generated method stub

		
			
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-10s|\n", this.getId(), 
				this.getName(), this.getType(), this.getPrice(), 
				damage, maxUse);
		
	}



	@Override
	public float extraDamage() {
		// TODO Auto-generated method stub
		
		System.out.print(this.getName() + " was used.");
		useLeft--;
		System.out.println("There is " + useLeft + " times left to use this item");
		

		return damage;
	}



	@Override
	public void printPlayerTable() {
		// TODO Auto-generated method stub
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-12s|%-10s|\n", this.getId(), 
				this.getName(), this.getType(), this.getPrice(), 
				damage, maxUse, useLeft);
	}
	
	
	

}
