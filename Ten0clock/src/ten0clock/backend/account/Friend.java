package ten0clock.backend.account;

public class Friend {
	private String name;
	private String userID;
	private boolean status;
	
	public Friend(String _name, String _userID, boolean _status) {
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
