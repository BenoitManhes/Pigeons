package model;

import java.util.ArrayList;

public class Grid {
	int SIZE = 600; //Size of the platform (pixels)
	int nbPigeons;
	ArrayList<Pigeon> listPigeons;
	ArrayList<Food> listFood;
	
	public Grid(int nbPigeons) {
		//Create a grid with a fixed number of pigeons
		
		this.nbPigeons = nbPigeons;
		listPigeons = new ArrayList<>();
		listFood = new ArrayList<>();
		
		for(int i = 0; i < nbPigeons; i++) {
			//Create a Pigeon object, and start his Thread
			Pigeon newPigeon = new Pigeon(listFood);
			System.out.println("Grid : creation of pigeon : id=" + newPigeon.hashCode() + " (and his thread launched)");
			listPigeons.add(newPigeon);
			Thread thread = new Thread(newPigeon);
			thread.start();
		}
		
		listFood.add(new Food());
		
	}
	
	public void stopGame() {
		for(Pigeon pigeon : listPigeons){
			pigeon.stop();
		}
		for(Food food : listFood) {
			food.stop();
		}
		System.out.println("Every threads have been stopped.");
	}
	
	
}
