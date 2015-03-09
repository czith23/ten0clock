package ten0clock.backend.event;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Ten0ClockEvent {

	private Long eventID;
	private Long creatorInternalUserID;
	private Long venueID;
	private Timestamp dateTime;
	private String eventName;
	private String description;
	
	public Ten0ClockEvent() {
		
	}
	
	public Ten0ClockEvent(Long eventID, Long creatorInternalUserID, Long venueID, Timestamp dateTime, String eventName, String description) {
		this.eventID = eventID;
		this.creatorInternalUserID = creatorInternalUserID;
		this.venueID = venueID;
		this.dateTime = dateTime;
		this.eventName = eventName;
		this.description = description;
	}
	
	
	public Long getEventID() {
		return eventID;
	}
	public void setEventID(Long eventID) {
		this.eventID = eventID;
	}
	public Long getCreatorInternalUserID() {
		return creatorInternalUserID;
	}
	public void setCreatorInternalUserID(Long creatorInternalUserID) {
		this.creatorInternalUserID = creatorInternalUserID;
	}
	public Long getVenueID() {
		return venueID;
	}
	public void setVenueID(Long venueID) {
		this.venueID = venueID;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
