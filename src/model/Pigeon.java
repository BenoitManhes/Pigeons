package model;

import java.util.ArrayList;
import java.util.Random;

public class Pigeon implements Runnable{
	boolean isRunning = true;
	
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
		posX = 50;
		posY = 50;
	}
	
	@Override
	public void run() {
		while(isRunning) {			
			if(listFood.size() != 0) {
				//Search for the closest food
				Food closest = getClosestFood();
				
				//Go in direction of the closest food
				move(closest);
				
				System.out.println("position of pigeon " + this.hashCode() + " :  (" + posX + ";" + posY + ")");

				
				if(posX == closest.getX() && posY == closest.getY()) {
					//notify food that it's been eaten so it can disappear
					System.out.println("pigeon " + this.hashCode() + " eat the food");
					listFood.remove(closest);
				}
				
			}else {
				//Move in a random direction
				Random rdm = new Random();
				int direction = rdm.nextInt(3);
				switch(direction) {
				case 0 :
					posX++;
					break;
				case 1 :
					posX--;
					break;
				case 2 :
					posY++;
					break;
				default : 
					posY--;
					break;
				}
				System.out.println("position of pigeon " + this.hashCode() + " :  (" + posX + ";" + posY + ")");
			}
			

			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//move the pigeon in direction of the food specified in parameter
	private void move(Food food) {
		int distanceX = food.getX() - posX;
		int distanceY = food.getY() - posY;
		
		if(Math.abs(distanceX) > Math.abs(distanceY)) {
			//move on axis x
			if(distanceX > 0) {
				//move positive
				posX++;
			}else {
				//move negative
				posX--;
			}
			
		}else {
			//move on axis y
			if(distanceY > 0) {
				//move positive
				posY++;
			}else {
				//move negative
				posY--;
			}
		}
		
	}

	private double getDistanceFromFood(Food food) {
		//distance = racine( (x1 - x2)² + (y1 - y2)² )
		double distance = Math.sqrt((posX - food.getX())*(posX - food.getX()) + (posY - food.getY())*(posY - food.getY()));
		
		return distance;
	}
	
	private Food getClosestFood() {
		Food closest = listFood.get(0);
		double closestDistance = getDistanceFromFood(closest);
				
		for(Food food : listFood) {
			if(getDistanceFromFood(food) < closestDistance) {
				closest = food;
			}
		}
		
		return closest;
	}
	
	
	public void stop() {
		isRunning = false;
	}
	
}
