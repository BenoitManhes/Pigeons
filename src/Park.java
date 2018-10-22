import java.util.Vector;

public class Park extends java.util.Observable {	
	
	private Vector<Pigeon> containerPigeon;
	private Vector<Food> containerFood;

	public Park() {
		this.containerPigeon = new Vector<Pigeon>();
		this.containerFood = new Vector<Food>();
		this.init();
	}

	
	public void init() {
		for (int i = 0; i < Parametre.NB_PIGEON; i++) {
			this.containerPigeon.add(new Pigeon());
		}
		setChanged();	
		notifyObservers(containerPigeon);
	}
	
	public Vector<Pigeon> getContainerPigeon() {
		return containerPigeon;
	}

}
