package ten0clock.backend.account;

import java.util.HashMap;
import java.util.Map;

public class Poll {
	private String prompt;
	private User owner;
	private Event owningEvent;
	private Venue owningVenue;
	
	private Map<User, String> responses = new HashMap<User, String>();
	
	public Poll(String _prompt) {
		prompt = _prompt;
	}
	
	public Poll(String _prompt, User _owner){
		prompt = _prompt;
		owner = _owner;
	}
	
	public Poll(String _prompt, Event _owningEvent) {
		prompt = _prompt;
		owningEvent = _owningEvent;
	}
	
	public Poll(String _prompt, Venue _owningVenue) {
		prompt = _prompt;
		owningVenue = _owningVenue;
	}
	
	public void respond(User u, String r) {
		responses.put(u, r);
	}
	
	public void setOwner(User u) {
		owner = u;
	}
	
	public void setVenue(Venue v) {
		owningVenue = v;
	}
	
	public void setEvent(Event e) {
		owningEvent = e;
	}
	
	public String Question() {
		return prompt;
	}
}
