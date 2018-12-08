import java.util.ArrayList;

public class Airport {
	private String name, id, city, country;
	private ArrayList<String> companies = new ArrayList<> ();
	
	
	public Airport(String aName, String anID, String aCity, String aCountry) {
		this.name = aName;
		this.id = anID;
		this.city = aCity;
		this.country = aCountry;
	}
	
	
	
	public void addCompany(Flight aFlight) {
		if(this.getName().equals(aFlight.getDepAirport().getName()) || this.getName().equals(aFlight.getArrAirport().getName())) {
			this.companies.add(aFlight.getCompany());
		}
	}
	
	 public boolean isDirectlyConnectedTo(Airport anAirport) {
		boolean flag = false; 
		for(Flight flight:CentralRegistry.flights) {
		 if(name.equals((flight.getDepAirport().getName()))) {
			 if(anAirport.name.equals((flight.getArrAirport().getName()))) {;
				 flag=true;
			 }
		 }
		 else if(name.equals((flight.getArrAirport().getName()))) {
			 if(anAirport.name.equals(flight.getDepAirport().getName())) {
				 flag=true; 
			 }
			 }
		   }
		return flag;
		 }
	 
	 public boolean isInDirectlyConnectedTo(Airport anAirport) {
		 boolean flag=false;
		 for(Flight flight:CentralRegistry.flights) {
			 if(this.isDirectlyConnectedTo(flight.getArrAirport())) {
				 if(flight.getArrAirport().isDirectlyConnectedTo(anAirport)) {
				 flag=true;
				 }
			 }
		 }
			return flag;
	 }
	 
	 public ArrayList<Airport> getCommonConnections(Airport anAirport){
		 ArrayList<Airport> commonAirports= new ArrayList<Airport>();
		 for(Airport airport:CentralRegistry.airports) {
			 if((this.isDirectlyConnectedTo(airport))&&(anAirport.isDirectlyConnectedTo(airport))) {
				 commonAirports.add(airport);
			 }
		 }
		 return commonAirports;
	 }
	 
	 public void printCompanies() {
		 for (int i = 0; i < companies.size(); i++) {
			 String value = companies.get(i);
	            System.out.println(value);
	        }	 
	 }
				 
		 
	 

	
	public String getName() {
		return name;
	}


	public String getId() {
		return id;
	}


	public String getCity() {
		return city;
	}


	public String getCountry() {
		return country;
	}


	public ArrayList<String> getCompanies() {
		return companies;
	}
	

}
