package ten0clock.backend.account;

import java.util.ArrayList;
import java.util.Date;

public class User {
	private String name;
	private String userID;
	private boolean status;
	private Venue mostRecent;
	private String phoneNumber;
	private String university;
	private Date birthday;
	
	private ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<Venue> venues = new ArrayList<Venue>();
	private ArrayList<Poll> polls = new ArrayList<Poll>();
	
	private User fake;
	
	public User(String _name, String _userID, boolean _status) {
		name = _name;
		userID = _userID;
		status = _status;
	}
	
	
	public User(String n, String usi, String pn, String un, Date bd) {
		name = n;
		userID = usi;
		phoneNumber = pn;
		university = un;
		birthday = bd;
	}

	public String getID() {
		return userID;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void flipStatus() {
		status = !status;
	}
	
	public ArrayList<Venue> Venues() {
		return venues;
	}
	
	public ArrayList<Event> Events() {
		return events;
	}
	
	public void checkIn(Venue v) {
		mostRecent = v;
	}
	
	public Venue MostRecent() {
		return mostRecent;
	}
	
	public String University() {
		return university;
	}
	
	public String UserID() {
		return userID;
	}
	
	public Date Birthday() {
		return birthday;
	}
	
	public void addFriend(User u) {
		friends.add(u);
	}
	
	public ArrayList<User> Friends() {
		return friends;
	}
	
	public void setFake(User f) {
		fake = f;
	}
	
	public void fakeAdd() {
		friends.add(fake);
	}
	
	public String Name() {
		return name;
	}
}
