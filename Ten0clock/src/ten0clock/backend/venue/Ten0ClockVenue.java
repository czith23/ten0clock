package ten0clock.backend.venue;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Ten0ClockVenue {

	
	Long venueId;
	String name;
	Double Longitude, latitude;
	String address;
	String city;
	String state;
	String zip;
	Long atmosphereId;
	Long volume;
	
	public Ten0ClockVenue() {
		
	}
	
	public Ten0ClockVenue(Long venueId, String name, Double Longitude, Double latitude, String address, String city, String state, String zip, Long atmosphereId, Long volume) {
		this.venueId = venueId;
		this.name = name;
		this.Longitude = Longitude;
		this.latitude = latitude;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.atmosphereId = atmosphereId;
		this.volume = volume;
	}
	
	
	public Long getVenueIdId() {
		return venueId;
	}




	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Double getLongitude() {
		return Longitude;
	}




	public void setLongitude(Double Longitude) {
		this.Longitude = Longitude;
	}




	public Double getLatitude() {
		return latitude;
	}




	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public String getZip() {
		return zip;
	}




	public void setZip(String zip) {
		this.zip = zip;
	}




	public Long getAtmosphereId() {
		return atmosphereId;
	}




	public void setAtmosphereId(Long atmosphereId) {
		this.atmosphereId = atmosphereId;
	}




	public Long getVolume() {
		return volume;
	}




	public void setVolume(Long volume) {
		this.volume = volume;
	}




	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
