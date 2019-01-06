import java.util.ArrayList;

public class CentralRegistry {
	public static ArrayList<Airport> airports = new ArrayList<Airport>();
	public static ArrayList<Flight> flights = new ArrayList<Flight>();

	public static void addFlight(Flight aFlight) {
		flights.add(aFlight);
		aFlight.getDepAirport().addCompany(aFlight);
		aFlight.getArrAirport().addCompany(aFlight);
	}

	public static void addAirport(Airport anAirport) {
		airports.add(anAirport);
	}

	public static Flight getLongestFlight() {
		double maxduration = 0;
		Flight maxflight = null;
		for (Flight flight : flights) {
			if (maxduration <= flight.getDuration()) {
				maxduration = flight.getDuration();
				maxflight = flight;
			}
		}
		return maxflight;
	}

	public static Airport getLargestHub() {
		double maxhub = 0;
		Airport maxairport = null;
		int[] anArray;
		anArray = new int[airports.size()];
		for (int i = 0; i < airports.size(); i++) {
			anArray[i] = 0;
		}

		int i = 0;
		for (Airport airport1 : airports) {
			for (Airport airport2 : airports) {
				if (airport1.isDirectlyConnectedTo(airport2)) {
					anArray[i]++;
				}
			}
			i++;
		}
		for (int j = 0; j < airports.size(); j++) {
			if (maxhub < anArray[j]) {
				maxhub = anArray[j];
				maxairport = airports.get(j);
			}

		}
		return maxairport;

	}

	public Airport getAirport(String city) {
		for (Airport airport : airports) {
			if (airport.getCity().equals(city)) {
				return airport;
			}
		}
		return null;

	}

	/*
	 * public static void getDirectFlightsDetails(Airport a, Airport b) { int count
	 * = 0; for (Flight flight : flights) { if (a.isDirectlyConnectedTo(b) &&
	 * a.equals(flight.getDepAirport()) && b.equals(flight.getArrAirport())) { count
	 * += 1; System.out.println("[" + count + "]" + flight.toString()); } }
	 * 
	 * }
	 * 
	 * public static void getInDirectFlightsDetails(Airport a, Airport b) { int
	 * count = 0; for (Flight flight : flights) { if
	 * (a.equals(flight.getDepAirport()) && b.equals(flight.getArrAirport())) {
	 * for(Airport airport:airports) {
	 * if(a.isDirectlyConnectedTo(airport)&&(airport.isDirectlyConnectedTo(b))) {
	 * count+=1;
	 * System.out.println("["+count+"]"+airport.getCity()+", "+airport.getId()
	 * +" airport"); } } break;
	 * 
	 * } }
	 * 
	 * }
	 */
	public static String getDirectFlightsDetails(Airport a, Airport b) {
		int count = 0;
		StringBuffer result = new StringBuffer("DIRECT FLIGHTS DETAILS: ");
		result.append(System.getProperty("line.separator"));
		for (Flight flight : flights) {
			if (a.isDirectlyConnectedTo(b) && a.equals(flight.getDepAirport()) && b.equals(flight.getArrAirport())) {
				count += 1;
				result.append("[" + count + "]" + flight.toString());
				result.append(System.getProperty("line.separator"));
			}
		}
		return result.toString();
	}

	public static String getInDirectFlightsDetails(Airport a, Airport b) {
		int count = 0;
		StringBuffer result = new StringBuffer("INDIRECT FLIGHTS through...");
		result.append(System.getProperty("line.separator"));
		for (Flight flight : flights) {
			if (a.equals(flight.getDepAirport()) && b.equals(flight.getArrAirport())) {
				for (Airport airport : airports) {
					if (a.isDirectlyConnectedTo(airport) && (airport.isDirectlyConnectedTo(b))) {
						count += 1;
						result.append("[" + count + "]" + airport.getCity() + ", " + airport.getId() + " airport");
						result.append(System.getProperty("line.separator"));
					}
				}
				break;

			}
		}
		return result.toString();

	}

}
