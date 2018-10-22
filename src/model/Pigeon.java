package model;

import java.util.ArrayList;

public class Pigeon implements Runnable{
	int posX;
	int posY;
	ArrayList<Food> listFood;
	int eaten; //number of food eaten
	
	public Pigeon() {
		//Create a pigeon at a random position
	}
	
	public Pigeon(ArrayList<Food> listFood) {
		//Create a pigeon at a random position
		//The pigeon has access to the list of food, so he knows where to go
		this.listFood = listFood;
	}
	
	@Override
	public void run() {
		while(true) {			
			if(listFood.size() != 0) {
				//Search for the closest food
				//Go to the closest food
			}else {
				//Move in a random direction
			}
		}
	}
	
}
