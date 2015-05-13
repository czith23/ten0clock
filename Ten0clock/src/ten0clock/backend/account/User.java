package ten0clock.backend.account;

import java.util.ArrayList;

public class User {
	private String name;
	private String userID;
	private boolean status;
	
	private ArrayList<User> friends = new ArrayList<User>();
	
	public User(String _name, String _userID, boolean _status) {
		name = _name;
		userID = _userID;
		status = _status;
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
}
