package BusResrv;

public class Bus {
	private int busNo; // Getter & Setter Create method to access the information GET & SET Method
	private boolean ac; // Getter & Setter
	private int capacity; // GET(ask the data) and SET(can change the data)

	Bus(int no, boolean ac, int cap) { // Constructor

		this.busNo = no;
		this.ac = ac;
		this.capacity = cap;

	}
	// Getter & Setter

	public int getBusNo() { // Set or Accessors
		return busNo;

	}

	public boolean isAc() {
		return ac;

	}

	public void setAc(boolean val) { // mutators
		ac = val;
	}

	public int getCapacity() { // (Accessor Method or called as) get method which does not have argument
		return capacity; // returns the capacity of the bus
	}

	public void setCapacity(int cap) { // () set Method which used to change the value it has argument
		capacity = cap;
	}

}