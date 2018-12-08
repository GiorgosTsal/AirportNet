
public class Flight {
	private  Airport depAirport;
	private   Airport arrAirport;
	private  double duration;
	private  String company;
	
	public Flight(Airport aDepAirport, Airport anArrAirport, double aDuration, String aCompany) {
		this.depAirport = aDepAirport;
		this.arrAirport = anArrAirport;
		this.duration = aDuration;
		this.company = aCompany;
	}

	public   Airport getDepAirport() {
		return depAirport;
	}

	
	public   Airport getArrAirport() {
		return arrAirport;
	}


	public double getDuration() {
		return duration;
	}


	public String getCompany() {
		return company;
	}

	
	
	
	

}
