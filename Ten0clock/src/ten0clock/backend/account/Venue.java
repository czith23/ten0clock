package ten0clock.backend.account;

import java.util.ArrayList;


public class Venue {
	public enum Atmosphere {
		CLUBLIKE,
		EXOTIC,
		LIVEMUSIC,
		CASUAL,
		FORMAL,
		QUIET
	}
	
	public enum Volume {
		PACKED,
		BUSY,
		AVERAGE,
		LIGHT,
		EMPTY
	}
	
	private String name;
	private String location;
	private Atmosphere atmosphere;
	private Volume volume;
	
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Venue() {
		
	}
	
	public Venue(String _name, String _location, Atmosphere _atmosphere, Volume _volume) {
		name = _name;
		location = _location;
		atmosphere = _atmosphere;
		volume = _volume;
	}
	
	public String Name() {
		return name;
	}
	
	public String Location() {
		return location;
	}
	
	public String Atmosphere() {
		switch (this.atmosphere) {
		case CLUBLIKE:
			return "Clublike";
		case EXOTIC:
			return "Exotic";
		case LIVEMUSIC:
			return "Live Music";
		case CASUAL:
			return "Casual";
		case FORMAL:
			return "Formal";
		case QUIET:
			return "Quiet";
		}
		return "None";
	}
	
	public String Volume() {
		switch (this.volume) {
		case PACKED:
			return "Packed";
		case BUSY:
			return "Busy";
		case AVERAGE:
			return "Average";
		case LIGHT:
			return "Light";
		case EMPTY:
			return "Empty";
		}
		return "None";
	}
	
	public void addEvent(Event e) {
		this.events.add(e);
	}
	
	public void setEvents(ArrayList<Event> es) {
		events = es;
		
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
}
