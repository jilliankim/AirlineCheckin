package sarahwest;

public class ServicedPassengers {

	private Passenger[] array;
	int numberOfFirstClassPassengers = 0;	
	int numberOfCoachClassPassengers = 0;				
	int totalNumberOfPassengers = 0;
	int arrayLength = 10000;

    
	public ServicedPassengers() {
		array = new Passenger[arrayLength];		// TODO arbitrary length, handle exception later
	}
	
	void addPassenger(Passenger passenger) {
		
		try {
			array[totalNumberOfPassengers] = passenger;
		} catch(Exception e) {
			// overflow array, increase the size of array2 and copy array's elements, 
			//	then, reassign array2 to original array.
			arrayLength =  2*arrayLength;
			Passenger[] array2 = new Passenger[arrayLength];
			for (int i = 0; i < array.length; i++) {
				array2[i] = array[i];
			}
			array = array2;
		}
		
		totalNumberOfPassengers ++; 				
		if (passenger.isFirstClass()) {
			numberOfFirstClassPassengers ++;
		} else { 
			numberOfCoachClassPassengers ++; 
		}
	}
	
	long maximumServiceTime() {
		
		long currentMax = 0;
		
		for (int i = 0; i < totalNumberOfPassengers - 1; i++) {
			
			if (array[i].getServiceTime() > currentMax) {
				currentMax = array[i].getServiceTime();
			}
		}
		
		return currentMax;
	}
	
	long averageServiceTime() {
		
		long sum = 0;
		
		for (int i = 0; i < totalNumberOfPassengers - 1; i++) {
			sum += array[i].getServiceTime();
		}
		
		return sum/totalNumberOfPassengers;	
	}
	
	int numberOfFirstClassPassengers() {
		return numberOfFirstClassPassengers;
	}
	
	int numberOfCoachClassPassengers() {
		return numberOfCoachClassPassengers;
	}
	
}
