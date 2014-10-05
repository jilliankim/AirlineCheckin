package sarahwest;

public class Passenger {
	long serviceTime = 0;
	boolean isFirstClass = false;
	
	public Passenger() {	
		this(false);
	}
	
	public Passenger(boolean isFirstClass) {	
		this.isFirstClass = isFirstClass;
	}
	
	void setServiceTime(long time) {
		serviceTime = time; 
	}
	
	long getServiceTime() {
		return serviceTime;
	}
	
	boolean isFirstClass() {
		return isFirstClass;
	}
	

}
