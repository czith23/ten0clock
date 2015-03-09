package ten0clock.backend.dao;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ten0clock.backend.event.Ten0ClockEvent;
import ten0clock.backend.venue.Ten0ClockVenue;

public class Ten0ClockVenueDAO {
	private final static String postAddress = "http://ten0clock.no-ip.org:9001/venue";
	
	public static Ten0ClockVenue createVenue(String venueName, Double latitude, Double Longitude, String address, String city, String state, String zip, Long atmosphereId, Long volume) {
		try {
			HttpClient c = new DefaultHttpClient();
			HttpPost p = new HttpPost(postAddress);
			
			StringEntity jsonString = new StringEntity("{\"name\":\"" + venueName + "\",\"latitude\":\"" + latitude + "\",\"Longitude\":\"" 
					+ Longitude + "\",\"address\":\"" + address + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zipcode\":\"" 
					+ zip + "\",\"atmosphere_id\":\"" + atmosphereId + "\",\"volume\":\"" + volume + "\"}");
			jsonString.setContentType("application/json");
			p.setEntity(jsonString);
			
			HttpResponse r = c.execute(p);
			BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
            String s = "", response = "";
            
            while ((s = rd.readLine()) != null) {
               response += s;
            }  

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            
            Long venueId = (Long) jsonObject.get("status");
            if (venueId == -1) {
            	return null;
            }
            return new Ten0ClockVenue(venueId, venueName, Longitude, latitude, address, city, state, zip, atmosphereId, volume);
            
            // need extra logic to add user to user_event table
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	

}