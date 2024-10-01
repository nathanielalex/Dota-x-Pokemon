package model;

import interfaces.Stronger;

public class SpellItem extends Item implements Stronger{
	private int damage;
	private int manaUsed;
	public SpellItem(String id, String name, String type, int price, int damage, int manaUsed) {
		super(id, name, type, price);
		this.damage = damage;
		this.manaUsed = manaUsed;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getManaUsed() {
		return manaUsed;
	}
	public void setManaUsed(int manaUsed) {
		this.manaUsed = manaUsed;
	}
	@Override
	public void printTable() {
		// TODO Auto-generated method stub

		
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-10s|\n", this.getId(), 
				this.getName(), this.getType(), this.getPrice(), 
				damage, manaUsed);
		
	}
	@Override
	public float extraDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
	@Override
	public void printPlayerTable() {
		// TODO Auto-generated method stub
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-12s|%-10s|\n", this.getId(), 
				this.getName(), this.getType(), this.getPrice(), 
				damage, manaUsed, "-");
	}
	
	
	
	
}
