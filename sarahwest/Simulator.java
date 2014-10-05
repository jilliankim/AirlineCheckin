package sarahwest;
import java.util.Scanner;


public class Simulator {
	
	static Clock clock = new Clock();
	
	// TODO arbitrary queue length, handle exception later
	static AirlineCheckinQueue firstClassQueue = new AirlineCheckinQueue(1000, clock) {};
	static AirlineCheckinQueue coachClassQueue = new AirlineCheckinQueue(1000, clock) {};
	
	
	static ServiceAgent firstClassSA1 = new ServiceAgent(clock) {};
	static ServiceAgent firstClassSA2 = new ServiceAgent(clock) {};
	
	static ServiceAgent coachClassSA1 = new ServiceAgent(clock) {};
	static ServiceAgent coachClassSA2 = new ServiceAgent(clock) {};
	static ServiceAgent coachClassSA3 = new ServiceAgent(clock) {};
	
	
	static ServicedPassengers servicedPassengers = new ServicedPassengers() {};
	
	
	static int experimentLength = 0;
	
	static int r1 = 0;						// first class arrival rate
	static int r2 = 0;						// coach class arrival rate
	
	static int s1 = 0;						// first class service rate
	static int s2 = 0;						// coach class service rate
	
	protected long minTime = 4;
	static String userInput= "";


	public Simulator() {
		
	}
	
	

    
	public static void main(String[] args) throws Exception {

		System.out.println("\t\t\tWelcome to the airline checkin simulation! \n\n1.) Enter the duration of the simulation in seconds.");
		Scanner in = new Scanner(System.in);
		userInput = in.nextLine();
		experimentLength = Integer.parseInt(userInput);			// will crash if not an integer

		System.out.println("2.) Enter the average arrival rate for first class passengers.");
		userInput = in.nextLine();
		r1 = Integer.parseInt(userInput);						// will crash if not an integer
		
		System.out.println("3.) Enter the average arrival rate for coach class passengers.");
		userInput = in.nextLine();
		r2 = Integer.parseInt(userInput);						// will crash if not an integer
		
		System.out.println("4.) Enter the maximum service time (in seconds) for first class passengers.");
		userInput = in.nextLine();
		s1 = Integer.parseInt(userInput);						// will crash if not an integer
		
		System.out.println("5.) Enter the maximum service time (in seconds) for coach class passengers.");
		userInput = in.nextLine();
		s2 = Integer.parseInt(userInput);						// will crash if not an integer
		
		
		firstClassQueue.setArrivalRate(r1);
		coachClassQueue.setArrivalRate(r2);
		
		firstClassSA1.getMaximumServiceTime(s1);
		firstClassSA2.getMaximumServiceTime(s1);
		coachClassSA1.getMaximumServiceTime(s2);
		coachClassSA2.getMaximumServiceTime(s2);
		coachClassSA3.getMaximumServiceTime(s2);
		
		simulate();

	}
	
	public static void simulate() {
		
		while (clock.getCurrentTime() < experimentLength) {

			addPassengersToQueues();
			movePassengersFromQueuesToServiceAgents();
			moveCompletedPassengersFromServiceAgentsToPlane();
			clock.advanceTime();
		}
		
		generateStatistics();
	}
	
	public static void addPassengersToQueues(){
		
		// check FirstClassQueue, next arrival time and add NEW passenger if appropriate
		if (clock.getCurrentTime() >= firstClassQueue.getNextArrivalTime()) {
			Passenger p = new Passenger(true);
			firstClassQueue.enqueue(p);
		}
		// check CoachQueue, next arrival time and add NEW passenger if appropriate
		if (clock.getCurrentTime() >= coachClassQueue.getNextArrivalTime()) {
			Passenger p = new Passenger(false);
			coachClassQueue.enqueue(p);
		}
		
	}

	public static void movePassengersFromQueuesToServiceAgents(){
		// for each agent:
			// if agent is available:
				// move passenger from appropriate queue to this agent
		
		if (firstClassSA1.isOccupied() == false) {
			if (! firstClassQueue.isEmpty()) {			// if queue contains a passenger
				Passenger p = (Passenger) firstClassQueue.dequeue();
				firstClassSA1.addPassenger(p);
			} else if (! coachClassQueue.isEmpty()) {	// if queue contains a passenger
				Passenger p = (Passenger) coachClassQueue.dequeue();
				firstClassSA1.addPassenger(p);
			}
		}
		
		if (firstClassSA2.isOccupied() == false) {
			if (! firstClassQueue.isEmpty()) {			// if queue contains a passenger
				Passenger p = (Passenger) firstClassQueue.dequeue();
				firstClassSA2.addPassenger(p);
			} else if (! coachClassQueue.isEmpty()) {	// if queue contains a passenger
				Passenger p = (Passenger) coachClassQueue.dequeue();
				firstClassSA2.addPassenger(p);
			}
		}
		
		if (coachClassSA1.isOccupied() == false) {
			if (! coachClassQueue.isEmpty()) {			// if queue contains a passenger
				Passenger p = (Passenger) coachClassQueue.dequeue();
				coachClassSA1.addPassenger(p);
			} else if (! firstClassQueue.isEmpty()) {	// if queue contains a passenger
				Passenger p = (Passenger) firstClassQueue.dequeue();
				coachClassSA1.addPassenger(p);
			}
		}
		
		if (coachClassSA2.isOccupied() == false) {
			if (! coachClassQueue.isEmpty()) {			// if queue contains a passenger
				Passenger p = (Passenger) coachClassQueue.dequeue();
				coachClassSA2.addPassenger(p);
			} else if (! firstClassQueue.isEmpty()) {	// if queue contains a passenger
				Passenger p = (Passenger) firstClassQueue.dequeue();
				coachClassSA2.addPassenger(p);
			}
		}
		
		if (coachClassSA3.isOccupied() == false) {
			if (! coachClassQueue.isEmpty()) {			// if queue contains a passenger
				Passenger p = (Passenger) coachClassQueue.dequeue();
				coachClassSA3.addPassenger(p);
			} else if (! firstClassQueue.isEmpty()) {	// if queue contains a passenger
				Passenger p = (Passenger) firstClassQueue.dequeue();
				coachClassSA3.addPassenger(p);
			}
		}
		
		
	}
	
	public static void moveCompletedPassengersFromServiceAgentsToPlane(){
		// for each agent:
			// if passenger at agent is completed:
				// move passenger to serviced passengers list
		
		if (firstClassSA1.isComplete()) {
			Passenger p = firstClassSA1.removePassenger();
			servicedPassengers.addPassenger(p);
		}
		if (firstClassSA2.isComplete()) {
			Passenger p = firstClassSA2.removePassenger();
			servicedPassengers.addPassenger(p);
		}
		if (coachClassSA1.isComplete()) {
			Passenger p = coachClassSA1.removePassenger();
			servicedPassengers.addPassenger(p);
		}
		if (coachClassSA2.isComplete()) {
			Passenger p = coachClassSA2.removePassenger();
			servicedPassengers.addPassenger(p);
		}
		if (coachClassSA3.isComplete()) {
			Passenger p = coachClassSA3.removePassenger();
			servicedPassengers.addPassenger(p);
		}
		
	}
	
	public static void generateStatistics(){
		// get averageServiceTime and maxServiceTime from ServicedPassengers
		System.out.println("\nStatistics:");
		System.out.println("The average service time for all passengers was about " + servicedPassengers.averageServiceTime() + " seconds.");
		System.out.println("The maximum service time for all passengers was about " + servicedPassengers.maximumServiceTime() + " seconds.");
		System.out.println(servicedPassengers.numberOfFirstClassPassengers() + " First class passengers were served.");
		System.out.println(servicedPassengers.numberOfCoachClassPassengers() + " Coach class passengers were served.");
		System.out.println("The maximum queue length for First class passengers was " + firstClassQueue.maxLength() + ".");
		System.out.println("The maximum queue length for Coach class passengers was " + coachClassQueue.maxLength() + ".");
	}
	
}
