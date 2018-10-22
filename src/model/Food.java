package model;

public class Food implements Runnable{
	boolean isRunning = true;
	
	int expirationDate;
	int posX;
	int posY;
	
	public Food() {
		//Create a food at a random position
	}

	@Override
	public void run() {
		while(isRunning) {
			//Check expirationDate to know if still good
			//Destroy if expired
		}
	}
	
	public void stop() {
		isRunning = false;
	}
}
