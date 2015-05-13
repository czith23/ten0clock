package ten0clock.backend.account;

import java.util.Date;

public class Event {
	private String name;
	private String location;
	private String category;
	private Date date;
	
	private Venue venue;
	
	public Event() {
	
	}
	
	public Event(String _name, String _location, String _category, Date _date) {
		name = _name;
		location = _location;
		category = _category;
		date = _date;
	}
	
	public String Name() {
		return name;
	}
	
	public String Location() {
		return location;
	}
	
	public String Category() {
		return category;
	}
	
	public Date Date() {
		return date;
	}
	
	public void setVenue(Venue v) {
		venue = v;
	}
	
	public Venue getVenue() {
		return venue;
	}
}