package ten0clock.backend.account;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	private String name;
	private Venue venue;
	private String category;
	private Date date;
	private ArrayList<Poll> polls = new ArrayList<Poll>();
	
	public Event() {
	
	}
	
	public Event(String _name, Venue v, String _category, Date _date) {
		name = _name;
		venue = v;
		category = _category;
		date = _date;
	}
	
	public String Name() {
		return name;
	}
	
	public String Location() {
		return venue.Name();
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
	
	public void addPoll(Poll p) {
		polls.add(p);
	}
	
	public ArrayList<Poll> Polls() {
		return polls;
	}
}