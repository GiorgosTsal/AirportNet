import java.util.ArrayList;

public class CentralRegistry {
	public static ArrayList<Airport> airports = new ArrayList<Airport>();
	public  static ArrayList<Flight> flights = new ArrayList<Flight>();
	

	public static void addFlight(Flight aFlight) {
		flights.add(aFlight);
		aFlight.getDepAirport().addCompany(aFlight);
		aFlight.getArrAirport().addCompany(aFlight);
		}
	
	public static void addAirport(Airport anAirport) {
		airports.add(anAirport);
	}
	
	public static Flight getLongestFlight() {
		double maxduration=0;
		Flight maxflight=null;
		for(Flight flight:flights) {
			if(maxduration<=flight.getDuration()) {
				maxduration=flight.getDuration();
			    maxflight=flight;}
		}
		return maxflight;
	}
	
	public static Airport getLargestHub(){
		double maxhub=0;
		Airport maxairport=null;
		int[] anArray;
		anArray = new int[airports.size()];
		for(int i = 0;i<airports.size();i++) {
			anArray[i]=0;
		}
		
		int i=0;
		for(Airport airport1:airports) {
		  for(Airport airport2:airports) {
			if(airport1.isDirectlyConnectedTo(airport2)) {
				anArray[i]++;
				}
			}
		  i++;
		  } 
		for(int j=0;j<airports.size();j++) {
			if(maxhub<anArray[j]) {
				maxhub=anArray[j];
				maxairport=airports.get(j);
			}
			
		} 
		return maxairport;
		
	}
}
