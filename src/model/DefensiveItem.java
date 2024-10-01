package model;

import interfaces.Deflectable;

public class DefensiveItem extends Item implements Deflectable{
	private int deflect;
	private int maxUse;
	private int useLeft;
	public DefensiveItem(String id, String name, String type, int price, int deflect, int maxUse, int useLeft) {
		super(id, name, type, price);
		this.deflect = deflect;
		this.maxUse = maxUse;
		this.useLeft = useLeft;
	}
	
	
	
	public int getUseLeft() {
		return useLeft;
	}



	public void setUseLeft(int useLeft) {
		this.useLeft = useLeft;
	}



	public int getDeflect() {
		return deflect;
	}
	public void setDeflect(int deflect) {
		this.deflect = deflect;
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
				deflect, maxUse);
		
	}
	
	



	@Override
	public void printPlayerTable() {
		// TODO Auto-generated method stub
		
		System.out.printf("|%-10s|%-30s|%-15s|%-10s|%-10s|%-12s|%-10s|\n", this.getId(), 
				this.getName(), this.getType(), this.getPrice(), 
				deflect, maxUse, useLeft);
	}



	@Override
	public float deflect() {
		// TODO Auto-generated method stub
		System.out.println(this.getName() + " is equipped");
		System.out.print(this.getName() + " was used.");
		useLeft--;
		System.out.println("There is " + useLeft + " times left to use this item");
		System.out.printf("Deflect %d using %s\n", deflect, this.getName());
		
		return deflect;
	}



	
	
	
}
