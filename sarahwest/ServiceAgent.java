package sarahwest;

public class ServiceAgent {
	
	Passenger currentPassenger = null;
	private long timePassengerArrived = 0;
	private long timePassengerWillComplete = 0;
	Clock clock = null;
	int maximumServiceTime = 0;
	

	public ServiceAgent(Clock clock) {
		this.clock = clock;
	}
	
	void getMaximumServiceTime(int s) {
		maximumServiceTime = s;
	}
	
	private int randomServiceTime() {
		float f = (float) Math.random()*maximumServiceTime;
    	return Math.round(f);
	}
	
	public void addPassenger(Passenger passenger){
		currentPassenger = passenger;
		
		timePassengerArrived = clock.getCurrentTime();
		int timeToService = randomServiceTime();
		timePassengerWillComplete = timePassengerArrived + timeToService;		
		currentPassenger.setServiceTime(timeToService);
	}
	
	public Passenger removePassenger(){
		Passenger p = currentPassenger;
		
		currentPassenger = null;
		
		return p;
	}

	
	Boolean isOccupied() {
		return (currentPassenger != null);
	}
	
	Boolean isComplete() {
		return isOccupied() && (clock.getCurrentTime() >= timePassengerWillComplete);
	}
	
	
}
