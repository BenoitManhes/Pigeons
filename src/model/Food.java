package model;

public class Food implements Runnable{
	int expirationDate;
	int posX;
	int posY;
	
	public Food() {
		//Create a food at a random position
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Check expirationDate to know if still good
		//Destroy if expired
	}
}
