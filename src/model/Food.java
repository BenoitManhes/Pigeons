package model;

public class Food implements Runnable{
	boolean isRunning = true;
	
	int expirationDate;
	int posX;
	int posY;
	
	public Food() {
		//Create a food at a random position
		posX =55;
		posY = 55;
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

	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
}
